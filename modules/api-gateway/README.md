# api-gateway (módulo genérico)

Versión simplificada del módulo `api-gateway` de Prisma (visto en
`api-gateway-main`), sin las partes específicas de ese proyecto. Crea un
`aws_api_gateway_rest_api` con authorizer, VPC Link, allowlist de IPs y
asociación a WAF, todo opcional.

## Qué cambia respecto al original

- **Un solo recurso en vez de dos**: el original definía
  `rest_api_with_vpc_link` y `rest_api_without_vpc_link` con `count`
  excluyente y después preguntaba `var.is_create_vpc_link ? A[0].x : B[0].x`
  en cada output. Acá alcanza con dejar `vpc_endpoint_ids = []` cuando el
  endpoint no es `PRIVATE` — mismo resultado, sin la duplicación.
- **Sin provider `hashicorp/template`**: el original usaba
  `data.template_file` (deprecado) para renderizar el `policy_file_location`.
  Acá el caller pasa la policy ya resuelta en `policy_document` — si
  necesitás plantillas, usá la función nativa `templatefile()` antes de
  pasarla, o un `data.aws_iam_policy_document`.
- **Sin bloque `provider "aws"` propio**: el original tenía su propio
  `providers.tf`. Un módulo reusable no debería fijar su propio provider —
  hereda el que ya configura el `terragrunt.hcl` raíz de este repo (con
  `default_tags`). Esto evita conflictos de versión cuando combinás varios
  módulos en un mismo `run-all`.
- **`aws` provider con `>= 5.0`** en vez de pineado a `4.61.0` exacto.
- **Resource policy con `/*`**: el original apuntaba el `execute-api:Invoke`
  solo al recurso raíz del `execution_arn`; acá se agrega `/*` para que la
  allowlist de IPs aplique a todos los métodos/rutas del API, no solo a la
  raíz.
- **`mandatory_tags` eliminado como objeto estricto**: ahora los tags se
  pasan como `map(string)` libre (`tags`) y se combinan con los
  `default_tags` que ya vienen del provider (root `terragrunt.hcl`), en vez
  de forzar el esquema `{Name, owner, environment, service, cost_center,
  technical_team}` específico de Prisma.

## Uso

Ver [`../example-api-gateway/terragrunt.hcl`](../example-api-gateway/terragrunt.hcl).

## Inputs / Outputs

Ver [`variables.tf`](variables.tf) y [`outputs.tf`](outputs.tf) — cada
variable tiene su `description`.
