locals {
  config_vars = read_terragrunt_config(find_in_parent_folders("config.hcl"))
  aws_region  = local.config_vars.locals.aws_region
  project     = local.config_vars.locals.project
  env         = local.config_vars.locals.env
}

generate "provider" {
  path      = "provider.tf"
  if_exists = "overwrite_terragrunt"

  contents = <<EOF
provider "aws" {
  region = "${local.aws_region}"

  default_tags {
    tags = ${jsonencode(local.config_vars.locals.mandatory_tags)}
  }
}
EOF
}

# Guarda el tfstate de cada módulo en S3, con lock en DynamoDB.
# El bucket y la tabla deben existir de antemano: correr ./bootstrap una
# única vez (con terraform plano, no terragrunt) antes de usar este repo.
remote_state {
  backend = "s3"
  config = {
    encrypt        = true
    bucket         = "tf-state-${local.project}-${local.env}-${local.aws_region}"
    key            = "${path_relative_to_include()}/terraform.tfstate"
    region         = local.aws_region
    dynamodb_table = "tf-locks-${local.project}-${local.env}"
  }
  generate = {
    path      = "terragrunt-backend.tf"
    if_exists = "overwrite_terragrunt"
  }
}

inputs = merge(
  local.config_vars.locals
)
