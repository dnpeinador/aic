resource "aws_api_gateway_vpc_link" "this" {
  count       = var.create_vpc_link ? 1 : 0
  name        = "${var.app_service}-${var.environment}-vpc-link"
  description = var.vpc_link_description
  target_arns = var.vpc_link_target_lb_arns

  tags = merge(var.tags, {
    Name = "${var.app_service}-${var.environment}-vpc-link"
  })
}
