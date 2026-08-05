locals {
  name = "${var.app_service}-${var.environment}-rest-api"

  tags = merge(var.tags, {
    Name        = local.name
    AppService  = var.app_service
    Environment = var.environment
    RegionId    = var.aws_region_id
  })
}

# Un solo recurso: a diferencia del módulo original (que duplicaba el
# rest_api en dos resources -con/sin vpc link- por count), acá alcanza con
# dejar vpc_endpoint_ids en [] cuando el endpoint no es PRIVATE.
resource "aws_api_gateway_rest_api" "this" {
  name                      = local.name
  description               = var.api_description
  binary_media_types        = var.binary_media_types
  minimum_compression_size  = var.minimum_compression_size
  body                      = var.api_gateway_resource_body != "" ? var.api_gateway_resource_body : null
  policy                    = var.policy_document

  endpoint_configuration {
    types            = var.end_point_type
    vpc_endpoint_ids = var.vpc_endpoint_ids
  }

  tags = local.tags
}
