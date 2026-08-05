locals {
  config_vars         = read_terragrunt_config(find_in_parent_folders("config.hcl"))
  vertical_name       = local.config_vars.locals.vertical_name
  resources_base_name = local.config_vars.locals.resources_base_name
  env                 = local.config_vars.locals.mandatory_tags.environment
  aws_region          = local.config_vars.locals.aws_region
  cost_center         = local.config_vars.locals.mandatory_tags.cost_center
  owner               = local.config_vars.locals.mandatory_tags.owner
  service             = local.config_vars.locals.mandatory_tags.service
  technical_team      = local.config_vars.locals.mandatory_tags.technical_team
}

terraform {
  #source = "git::https://gitlab.com/arquitectura-prisma/arquitectura/global-modules-iac/api-gateway.git//?ref=v0.1.4"
}

include {
  path = find_in_parent_folders()
}

inputs = {
  aws_region            = local.aws_region
  app_service           = "international_platform_card_activation_v1_v1"
  api_description       = "API Program API Gateway for project international_platform_card_activation_v1_v1"
  create_authorizer     = true
  authorizer_name       = "api-program-authorizer"
  authorizer_lambda_arn = "arn:aws:apigateway:us-east-1:lambda:path/2015-03-31/functions/arn:aws:lambda:us-east-1:445176144121:function:authorizer-apip-sls-auth-cognito-homo-env-dev/invocations"
  invocation_role_arn   = ""

  mandatory_tags = {
    Name           = "international_platform_card_activation_v1_v1"
    owner          = local.owner
    environment    = local.env
    service        = local.service
    cost_center    = local.cost_center
    technical_team = local.technical_team
  }

  common_tags = {
    repository_id = "60448723"
    managed_by    = "api-program"
  }
}