data "aws_iam_policy_document" "ip_allow" {
  count = length(var.ip_allow_list) > 0 ? 1 : 0

  statement {
    effect  = "Allow"
    actions = ["execute-api:Invoke"]

    principals {
      type        = "AWS"
      identifiers = ["*"]
    }

    # /* alcanza todos los recursos y métodos del API, no solo la raíz.
    resources = ["${aws_api_gateway_rest_api.this.execution_arn}/*"]

    condition {
      test     = "IpAddress"
      variable = "aws:SourceIp"
      values   = var.ip_allow_list
    }
  }
}

resource "aws_api_gateway_rest_api_policy" "ip_allow" {
  count       = length(var.ip_allow_list) > 0 ? 1 : 0
  rest_api_id = aws_api_gateway_rest_api.this.id
  policy      = data.aws_iam_policy_document.ip_allow[0].json
}
