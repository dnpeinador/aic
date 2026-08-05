output "id" {
  value = aws_api_gateway_rest_api.this.id
}

output "arn" {
  value = aws_api_gateway_rest_api.this.arn
}

output "execution_arn" {
  value = aws_api_gateway_rest_api.this.execution_arn
}

output "root_resource_id" {
  value = aws_api_gateway_rest_api.this.root_resource_id
}

output "vpc_link_id" {
  value = try(aws_api_gateway_vpc_link.this[0].id, null)
}

output "authorizer_id" {
  value = try(aws_api_gateway_authorizer.this[0].id, null)
}
