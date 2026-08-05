# mi-infra-base

Esqueleto mínimo de Terragrunt para IaC en AWS, basado en el patrón de
`tf-dev-main` pero sin nada específico de ese proyecto (sin EKS, sin
módulos privados, sin tags/ARNs hardcodeados).

## Estructura

```
.
├── terragrunt.hcl          # raíz: backend remoto (S3+DynamoDB) + provider AWS. Todo módulo lo hereda.
├── config.hcl               # variables comunes: project, env, region, tags
├── bootstrap/                # terraform plano (no terragrunt) para crear el bucket y la tabla de locks
│   └── main.tf
├── modules/
│   └── api-gateway/           # módulo Terraform reusable (ver su propio README)
└── example-api-gateway/       # config "viva" que instancia el módulo — copiar esta carpeta por cada recurso nuevo
    └── terragrunt.hcl
```

## Primer uso

1. **Editar `config.hcl`**: poné el nombre real de tu proyecto (`project`),
   el `env` y la región. Estos valores definen el nombre del bucket de
   state, así que definilos antes de bootstrapear.

2. **Bootstrap del backend remoto** (una sola vez, crea el bucket S3 y la
   tabla DynamoDB que `terragrunt.hcl` espera que ya existan):

   ```bash
   cd bootstrap
   terraform init
   terraform apply -var="project=mi-proyecto" -var="env=dev"
   ```

   Usá el mismo `project`/`env` que pusiste en `config.hcl`. El estado de
   este paso queda local (`bootstrap/terraform.tfstate`) a propósito: es
   el único módulo que no puede vivir en el backend remoto que él mismo crea.

3. **Agregar un módulo real**: hay dos piezas separadas —
   - `modules/<nombre>/`: el código Terraform reusable (recursos, variables,
     outputs). `modules/api-gateway/` es el ejemplo.
   - `<nombre-del-recurso>/terragrunt.hcl`: la config "viva" que instancia
     ese módulo con inputs concretos. `example-api-gateway/` es el ejemplo,
     y apunta al módulo local con
     `source = "${get_terragrunt_dir()}/../modules/api-gateway"`.

   Para un recurso nuevo: copiá `example-api-gateway/`, renombrá la carpeta
   (ej. `cards-api/`, `movements-api/`) y ajustá los `inputs`. Si el módulo
   no existe todavía, creá su carpeta en `modules/` o apuntá
   `terraform.source` a uno externo — público del Registry
   (`tfr:///namespace/name/aws?version=x`) o propio en git
   (`git::https://.../mi-modulo.git//?ref=v1.0.0`).
   El bloque `include { path = find_in_parent_folders() }` es lo que le da
   el backend remoto y el provider automáticamente; no hay que repetirlo.

4. **Comandos** (parados en la carpeta del módulo, o en la raíz para
   correr todos con `run-all`):

   ```bash
   terragrunt init
   terragrunt plan
   terragrunt apply
   ```

## Convenciones

- Un módulo = una carpeta con un solo `terragrunt.hcl`.
- Las variables compartidas van en `config.hcl`, nunca repetidas en cada módulo.
- Los archivos que Terragrunt genera automáticamente (`provider.tf`,
  `terragrunt-backend.tf`) no se commitean — ya están en `.gitignore`.
- Si un módulo depende de otro (ej. necesita un output), usá un bloque
  `dependency` apuntando a `../nombre-modulo`, como se ve en `tf-dev-main/infra/vpc-link`.
- Los módulos en `modules/` **no** definen su propio bloque `provider "aws"`
  — heredan el que genera el `terragrunt.hcl` raíz (con `default_tags`).
  Definir un provider dentro del módulo solo genera conflictos de versión
  cuando combinás varios módulos en un `run-all`.

## Qué NO incluye este esqueleto (agregalo cuando lo necesites)

- EKS / Kubernetes — solo hace falta si vas a correr contenedores.
- Pipeline de CI (`.gitlab-ci.yml`) — armalo una vez que sepas en qué
  plataforma de CI vas a correr esto.
- Módulos de red (VPC, subnets) — si no tenés una VPC existente para
  apuntar, es probablemente lo primero que vas a querer crear.
