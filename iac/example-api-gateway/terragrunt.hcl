## Ejemplo de uso del módulo modules/api-gateway.
## Copiá esta carpeta por cada API nuevo (uno por microservicio, como en
## tf-dev-main/infra/api-gtw-*), cambiando app_service y las opciones que
## necesites.

locals {
  config_vars = read_terragrunt_config(find_in_parent_folders("config.hcl"))
  project     = local.config_vars.locals.project
  env         = local.config_vars.locals.env
}

terraform {
  # Módulo local, versionado en este mismo repo. Cuando lo separes a su
  # propio repo git, reemplazá esto por algo como:
  # source = "git::https://github.com/tu-org/api-gateway.git//?ref=v1.0.0"
  source = "${get_terragrunt_dir()}/../modules/api-gateway"
}

# Hereda backend remoto + provider (con default_tags) del terragrunt.hcl raíz.
include {
  path = find_in_parent_folders()
}

inputs = {
  app_service     = "example-service"
  environment     = local.env
  api_description = "API Gateway de ejemplo para ${local.project}"

  end_point_type = ["REGIONAL"] # usá ["PRIVATE"] + vpc_endpoint_ids si va detrás de un VPC endpoint

  create_authorizer = false
  create_vpc_link    = false

  tags = {
    Name = "${local.project}-example-api"
  }
}
