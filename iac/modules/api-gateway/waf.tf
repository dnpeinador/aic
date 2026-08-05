resource "aws_wafv2_web_acl_association" "this" {
  count        = var.waf_web_acl_arn != "" ? 1 : 0
  resource_arn = aws_api_gateway_rest_api.this.arn
  web_acl_arn  = var.waf_web_acl_arn
}
