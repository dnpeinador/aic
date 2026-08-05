variable "aws_region_id" {
  type        = string
  description = "Identificador corto de región, solo para tagging (ej. use1)."
  default     = "use1"
}

variable "environment" {
  type        = string
  description = "dev, qa, prod, etc."
}

variable "app_service" {
  type        = string
  description = "Nombre corto del servicio/API. Se usa para nombrar los recursos (ej. \"cards\" -> \"cards-dev-rest-api\")."
}

variable "api_description" {
  type    = string
  default = "API created with Terraform"
}

variable "end_point_type" {
  type        = list(string)
  description = "EDGE, REGIONAL o PRIVATE."
  default     = ["REGIONAL"]
}

variable "vpc_endpoint_ids" {
  type        = list(string)
  description = "IDs de VPC Endpoint. Requerido solo si end_point_type = [\"PRIVATE\"]."
  default     = []
}

variable "binary_media_types" {
  type    = list(string)
  default = ["UTF-8-encoded"]
}

variable "minimum_compression_size" {
  type        = number
  description = "-1 deshabilita la compresión. 0-10485760 la habilita con ese umbral en bytes."
  default     = -1
}

variable "api_gateway_resource_body" {
  type        = string
  description = "Definición OpenAPI/Swagger (JSON) para crear rutas/métodos junto con el API. Vacío = API sin rutas."
  default     = ""
}

variable "policy_document" {
  type        = string
  description = "Resource policy del API Gateway, en JSON ya renderizado (ej. con jsonencode() o data.aws_iam_policy_document en el caller). null = sin policy explícita."
  default     = null
}

# --- Authorizer (Lambda/Cognito) ---

variable "create_authorizer" {
  type    = bool
  default = false
}

variable "authorizer_name" {
  type    = string
  default = ""
}

variable "authorizer_lambda_arn" {
  type    = string
  default = ""
}

variable "authorizer_invocation_role_arn" {
  type    = string
  default = ""
}

# --- VPC Link (para exponer un NLB privado, ej. de un EKS) ---

variable "create_vpc_link" {
  type    = bool
  default = false
}

variable "vpc_link_description" {
  type    = string
  default = ""
}

variable "vpc_link_target_lb_arns" {
  type        = list(string)
  description = "ARNs de Network Load Balancers a exponer. AWS soporta solo 1 por vpc link."
  default     = []
}

# --- Allowlist de IPs de origen ---

variable "ip_allow_list" {
  type        = list(string)
  description = "CIDRs permitidos para invocar el API. Vacío = sin restricción por IP."
  default     = []
}

# --- WAF ---

variable "waf_web_acl_arn" {
  type    = string
  default = ""
}

variable "tags" {
  type        = map(string)
  description = "Tags propias de este API, además de las que ya vienen por default_tags del provider (mandatory_tags de config.hcl)."
  default     = {}
}
