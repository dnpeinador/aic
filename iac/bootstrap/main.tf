## Bootstrap del backend remoto de Terraform.
##
## Esto se corre UNA sola vez, con terraform plano (no terragrunt), porque
## terragrunt.hcl (raíz) ya asume que el bucket S3 y la tabla DynamoDB
## existen. Su propio estado queda en local (terraform.tfstate en esta
## carpeta) — es intencional, no lo subas a un remoto que todavía no existe.
##
## Uso:
##   cd bootstrap
##   terraform init
##   terraform apply -var="project=mi-proyecto" -var="env=dev"

terraform {
  required_version = ">= 1.5"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

variable "project" {
  type        = string
  description = "Debe coincidir con locals.project en config.hcl"
}

variable "env" {
  type    = string
  default = "dev"
}

variable "aws_region" {
  type    = string
  default = "us-east-1"
}

provider "aws" {
  region = var.aws_region
}

resource "aws_s3_bucket" "tf_state" {
  bucket = "tf-state-${var.project}-${var.env}-${var.aws_region}"

  lifecycle {
    prevent_destroy = true
  }
}

resource "aws_s3_bucket_versioning" "tf_state" {
  bucket = aws_s3_bucket.tf_state.id
  versioning_configuration {
    status = "Enabled"
  }
}

resource "aws_s3_bucket_server_side_encryption_configuration" "tf_state" {
  bucket = aws_s3_bucket.tf_state.id
  rule {
    apply_server_side_encryption_by_default {
      sse_algorithm = "AES256"
    }
  }
}

resource "aws_s3_bucket_public_access_block" "tf_state" {
  bucket                  = aws_s3_bucket.tf_state.id
  block_public_acls       = true
  block_public_policy     = true
  ignore_public_acls      = true
  restrict_public_buckets = true
}

resource "aws_dynamodb_table" "tf_locks" {
  name         = "tf-locks-${var.project}-${var.env}"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "LockID"

  attribute {
    name = "LockID"
    type = "S"
  }

  lifecycle {
    prevent_destroy = true
  }
}

output "state_bucket" {
  value = aws_s3_bucket.tf_state.bucket
}

output "locks_table" {
  value = aws_dynamodb_table.tf_locks.name
}
