locals {
  # Nombre corto de tu proyecto/vertical. Se usa para nombrar el bucket de
  # state, la tabla de locks y como prefijo de tags. Sin espacios ni mayúsculas.
  project = "mi-proyecto"

  env        = "dev"
  aws_region = "us-east-1"

  # Tags que se aplican a TODOS los recursos (via default_tags del provider
  # generado en el terragrunt.hcl raíz).
  mandatory_tags = {
    project     = local.project
    environment = local.env
    managed_by  = "terragrunt"
    owner       = "tu-email@dominio.com"
  }
}
