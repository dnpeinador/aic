resource "aws_api_gateway_authorizer" "this" {
  count                  = var.create_authorizer ? 1 : 0
  name                   = var.authorizer_name
  rest_api_id            = aws_api_gateway_rest_api.this.id
  authorizer_uri         = var.authorizer_lambda_arn
  authorizer_credentials = var.authorizer_invocation_role_arn
}
