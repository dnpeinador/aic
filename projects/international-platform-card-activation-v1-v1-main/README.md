<div align="center">
    <img alt="Spring Boot Logo" width="250" height="auto" src="https://d1dq91zatgtr9n.cloudfront.net/logos/logo-spring.svg" />
</div>

<div align="center">

# APIP International Platform Card Activation

</div>

<div align="center">
    <img alt="Java" src="https://img.shields.io/badge/Java-v21-red?style=for-the-badge&logo=java" />
    <img alt="Maven" src="https://img.shields.io/badge/Maven-v3.9.6-purple?style=for-the-badge" />
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3-green?style=for-the-badge" />
    <img alt="Release" src="https://img.shields.io/badge/Release-0.6.1-blue?style=for-the-badge" />
    <br/>
</div>

## Bienvenida


¡Te damos la bienvenida al arquetipo de desarrollo de microservicios **APIP** basado en Spring Boot, Java 21 y Maven! Este conjunto de herramientas está diseñado para proporcionarte una base sólida y eficiente para el desarrollo de microservicios, aprovechando las últimas innovaciones en el ecosistema Java. ¡Explora y disfruta del potencial que ofrece este arquetipo para acelerar tu desarrollo de software!

### Desarrollo Ágil y Productivo

Con Spring Boot, el desarrollo se vuelve ágil y productivo. La configuración predeterminada y la convención sobre configuración permiten centrarte en la lógica de negocio, acelerando el tiempo de entrega de tu aplicación.

### Innovación con Java 21

Java 21 ofrece las últimas características del lenguaje y mejoras de rendimiento. Mantente al día con la innovación en el mundo Java, aprovechando las capacidades más recientes para el desarrollo de tu microservicio.

### Gestión Efectiva de Dependencias

Maven simplifica la gestión de dependencias y la construcción del proyecto. Con una estructura de proyecto bien definida, puedes gestionar eficazmente las dependencias y mantener un entorno de desarrollo ordenado.

### Escalabilidad y Rendimiento

Spring Boot y Java 21 proporcionan una plataforma sólida para construir microservicios escalables y de alto rendimiento. Aprovecha la arquitectura basada en Spring Boot para gestionar fácilmente la escalabilidad de tu aplicación.

### Configuración Sencilla y Flexible

La configuración de la aplicación se vuelve sencilla y flexible con Spring Boot. Utiliza anotaciones y propiedades para configurar tu aplicación de manera intuitiva y fácil de entender.

### Comunidad Activa y Soporte

Al utilizar tecnologías respaldadas por comunidades activas, tendrás acceso a una gran cantidad de recursos, tutoriales y soporte en línea. Únete a la próspera comunidad de desarrolladores que utilizan estas tecnologías en todo el mundo.

### Empieza Rápidamente

Este arquetipo te permite comenzar rápidamente con el desarrollo de tu microservicio, proporcionándote una base sólida, las mejores prácticas y las herramientas más recientes en el ecosistema de desarrollo de Java.

## Para Aplicaciones Empresariales

Este stack tecnológico ha sido diseñado teniendo en cuenta las necesidades de las aplicaciones empresariales. Algunos de los beneficios clave para entornos empresariales incluyen:

- **Escalabilidad Empresarial:** La arquitectura de microservicios y la robustez de Spring Boot permiten escalar vertical y horizontalmente para manejar las crecientes demandas empresariales.

- **Seguridad y Fiabilidad:** Spring Boot proporciona características integradas de seguridad y gestión de errores, garantizando la confidencialidad, integridad y disponibilidad de los datos en entornos empresariales críticos.

- **Mantenimiento Simplificado:** La estructura clara del proyecto y la gestión de dependencias de Maven facilitan el mantenimiento y la evolución de aplicaciones empresariales a lo largo del tiempo.

- **Integración Sencilla:** Spring Boot facilita la integración con otras tecnologías y sistemas empresariales, permitiendo una colaboración armoniosa con el ecosistema de la empresa.

Este arquetipo proporciona una base sólida para el desarrollo y mantenimiento de aplicaciones empresariales modernas, combinando la innovación tecnológica con la estabilidad y la eficiencia requeridas en entornos empresariales críticos.

## Glosario

- [📚 Que es APIP?](#apip)
- [📝 Cuando usar el arquetipo APIP?](#use-cases)
- [🧩 Requerimientos básicos](#basic-requirements)
- [🛠️ Instalar dependencias](#install-dependencies)
- [⚙️ Configuración](#configurations)
- [💻 Scripts](#scripts)
- [🐳 Docker](#docker)
- [📤 Commits](#commits)
- [📄 Changelog](#changelog)
- [✏️ Codestyle](#codestyle)

---

<a name="apip"></a>

## 📚 Que es APIP?

APIP (API Program) es una plataforma diseñada para facilitar la creación y disponibilidad de APIs que pueden ser integradas por terceras partes. Se enfoca en una filosofía de descentralización, permitiendo que cada sector de negocio mantenga el control sobre su infraestructura y productos, reduciendo al mínimo los componentes centralizados para favorecer una independencia operativa más ágil. Es una solución Cloud Native, optimizada para el uso de recursos en la nube y es agnóstica tanto en términos de proveedores de servicios en la nube como de gateways, comenzando con AWS pero con la flexibilidad para adaptarse a otros entornos cloud. APIP prioriza la eficiencia en costos a través de un modelo de pago por uso, lo que significa que los costos se escalan según la demanda. Además, se destaca por su fuerte enfoque en la automatización, con pipelines de aprovisionamiento de infraestructura y de despliegue automatizados, facilitando una gestión eficiente y ágil de las APIs.

<a name="use-cases"></a>

## 📝 Cuando usar el arquetipo APIP?

El arquetipo APIP está específicamente diseñado para situaciones en las que se busca integrar una API Core dentro de la plataforma APIP. Es esencial comprender que, para hacer accesible dicha API Core, APIP requiere la implementación de un proxy. Este mecanismo facilita el acceso seguro y eficiente a la API, asegurando su correcta funcionalidad dentro del ecosistema de la plataforma. Por lo tanto, la elección de este arquetipo debe estar guiada por la necesidad de establecer una conexión fluida y segura para la exposición y manejo de APIs Core a través de APIP.

<a name="basic-requirements"></a>

## 🧩 Requerimientos básicos

Antes de comenzar con el desarrollo en este proyecto, asegúrate de tener los siguientes requisitos básicos instalados y configurados en tu entorno de desarrollo:

- **Java:** Asegúrate de tener Java 21 instalado en tu máquina. Puedes descargar la versión 21 de Java desde [java.com](https://www.java.com/).

- **Docker:** Este proyecto utiliza Docker para la gestión de contenedores. Asegúrate de tener Docker instalado y en ejecución. Puedes descargar Docker desde [docker.com](https://www.docker.com/get-started).

- **Git:** Para clonar y gestionar el repositorio del proyecto, necesitarás tener Git instalado. Puedes descargar Git desde [git-scm.com](https://git-scm.com/downloads).

- **Entorno de Desarrollo Integrado (IDE):** Se recomienda utilizar un IDE compatible con Java y Spring Boot, como IntelliJ IDEA o Eclipse. Asegúrate de tener el plugin de Spring Boot instalado si estás utilizando IntelliJ IDEA o Eclipse.

Es importante verificar que todos estos requisitos estén configurados correctamente antes de comenzar con el desarrollo. Si encuentras algún problema durante la configuración, consulta la documentación correspondiente de cada herramienta o busca ayuda en las comunidades en línea.

<a name="install-dependencies"></a>

## 🛠️ Instalar dependencias

> ⚠️ Antes de instalar las dependencias, es requerido realizar la configuración de la registry de **AWS CodeArtifacts**
> de manera local siguiendo los pasos que se encuentra en la [Documentación](https://doc-arq.dev.prismamp.com/docs/devops/code_artifact/local_usage/package_manager/mvn).

Para instalar las dependencias, ejecuta el siguiente comando en la raíz del proyecto:

```bash
./mvnw clean install
```

<a name="configurations"></a>

## ⚙️ Configuración

Este arquetipo está fundamentado en el uso de **Spring Cloud Gateway**, una solución robusta y flexible para la creación de API gateways. Spring Cloud Gateway proporciona un marco de trabajo eficiente para el enrutamiento de solicitudes, la aplicación de filtros de seguridad, y la gestión de cross-cutting concerns como la autenticación, la supervisión y la limitación de tasa de peticiones. Su diseño se centra en la simplicidad y la eficacia, permitiendo una integración ágil y segura de servicios dentro de la arquitectura de microservicios. Al basarse en este framework, el arquetipo ofrece una configuración inicial optimizada para el desarrollo rápido y la implementación eficiente de gateways personalizados, asegurando una compatibilidad y rendimiento excelentes dentro del ecosistema APIP.

Esta sección detalla el contenido del archivo `application.yml`, que es fundamental para la configuración del microservicio. El archivo `application.yml` contiene configuraciones clave que definen el comportamiento del servicio, desde la configuración del servidor hasta la conexión con APIs externas y la gestión de logs.

A continuación se presenta el contenido del archivo `application.yml` con comentarios explicativos para cada sección:

```yml
# Configuraciones del servidor, incluyendo ruta de contexto, puerto y opciones de compresión
server:
  servlet:
    context-path: /v1/proxy  # Ruta de contexto para el servidor
  port: 9090  # Puerto en el que se ejecutará el servidor
  compression:
    enabled: true  # Habilitar compresión de respuesta
    min-response-size: 2048  # Tamaño mínimo de respuesta para comprimir
    mime-types: application/json,application/xml,text/html,text/xml,text/plain  # Tipos MIME para comprimir

# Información de la aplicación, útil para despliegue y seguimiento operacional
info:
  current_commit: ${CURRENT_COMMIT:}  # Commit actual del código fuente

# Configuraciones globales de Spring Framework, incluyendo salida ANSI, metadatos de la aplicación y configuraciones de Jackson
spring:
  output:
    ansi.enabled: ALWAYS  # Habilitar salida ANSI
  application:
    name: proxy-sample  # Nombre de la aplicación
    version: @project.version@  # Versión de la aplicación
  jackson:
    property-naming-strategy: SNAKE_CASE  # Estrategia de nombramiento de propiedades de Jackson

# Configuración de Springdoc para generación y gestión de documentación de API
springdoc:
  show-actuator: false
  api-docs:
    path: /api-docs  # Ruta para la documentación de la API
  default-produces-media-type: application/json

# Especificaciones de documentación OpenAPI, proporcionando información detallada sobre la API para consumidores
documentation:
  openapi:
    info:
      prefix: ${API_PREFIX:PROXY-API-}  # Prefijo para la API
      title: Proxy API  # Título de la API
      description: Documentación de la API de Proxy  # Descripción de la API
      version: 1.0  # Versión de la API
      contact:
        name: Prisma Medios de Pago  # Nombre del contacto
        email: support@prismamp.com  # Correo electrónico del contacto

# Configuraciones de endpoints de gestión y monitoreo, incluyendo verificación de salud y disponibilidad
management:
  endpoint:
    health:
      group:
        readiness:
          include: readinessState,requestApi  # Incluir verificación de estado de preparación y solicitud API
  endpoints:
    web:
      base-path: ${server.servlet.context-path}  # Ruta base para los endpoints web
      exposure:
        include: "health,readiness,info"  # Endpoints expuestos
        exclude: env  # Endpoints excluidos

# Configuración del servicio proxy, incluyendo nombre y versión
proxy:
  name: ${PROXY_API_NAME:proxy-name}  # Nombre del proxy
  version: ${PROXY_API_VERSION:v1}  # Versión del proxy

# Configuraciones de la API Core, definiendo host, ruta y configuraciones de verificación de salud
api-core:
  host: ${API_CORE_HOST:http://localhost:8080}  # Host de la API Core
  path: ${API_CORE_PATH:/v1/seed}  # Ruta de la API Core
  liveness-path: ${API_CORE_LIVENESS_PATH:/v1/seed/health/liveness}  # Ruta de verificación de salud de la API Core

# Configuraciones de propagación de headers para un reenvío consistente de headers en arquitectura de microservicios
header-propagator:
  name: apip-api-core  # Nombre del propagador de headers
  headers: ${HEADER_PROPAGATOR_HEADERS:x-apip-cuit,x-apip-partner-id,x-apip-client-id}  # Headers a propagar

# Configuraciones de validación de acceso para rutas de validación de servicios externos
access-validation:
  host-url: ${ACCESS_VALIDATION_API_URL:http://acc-val-dev.api-homo.prismamediosdepago.com}  # URL del host de validación de acceso
  validation-path: ${ACCESS_VALIDATION_API_URL_VALIDATION:/v2/core/access-validation/validation}  # Ruta de validación de acceso
  liveness-path: ${ACCESS_VALIDATION_API_URL_LIVENESS:/v2/core/access-validation/health/liveness}  # Ruta de verificación de salud de validación de acceso
  exclude:
    paths: ${ACCESS_VALIDATION_EXCLUDE_PATHS:/samples}  # Rutas excluidas de validación de acceso

# Configuración para manejo de errores y compatibilidad hacia atrás
errors:
  allow-retro-compatibility: ${ERROR_ALLOW_RETRO_COMPATIBILITY:false}  # Permitir compatibilidad hacia atrás de errores

# Configuración de Logbook para registro detallado, incluyendo exclusiones y filtros de seguridad
logbook:
  exclude: ${LOGGER_EXCLUDE_ENDPOINTS:**/health/**,**/todos/**}  # Exclusiones de endpoints
  filter.enabled: true  # Habilitar filtro de logbook
  secure-filter.enabled: true  # Habilitar filtro de seguridad de logbook
  format.style: ${LOGGER_REQUEST_FORMAT_STYLE:http}  # Estilo de formato de solicitud
  strategy: body-only-if-status-at-least  # Estrategia de registro
  minimum-status: 200  # Estado mínimo para loggear
  obfuscate:
    headers: ${LOGGER_OBFUSCATE_HEADERS:Authorization,X-Secret}  # Headers a obfuscar
    parameters: ${LOGGER_OBFUSCATE_PARAMETERS:access_token,password}  # Parámetros a obfuscar
    json-body-fields: ${LOGGER_OBFUSCATE_BODY:sample}  # Campos JSON a obfuscar
  write:
    chunk-size: 100000  # Tamaño de chunk de escritura

# Configuración de OpenSearch para observabilidad e insights operacionales
opensearch:
  enabled: ${OBSERVABILITY_ENABLED:false}  # Habilitar OpenSearch
  index: ${OBSERVABILITY_INDEX_NAME:proxy-sample}  # Índice de OpenSearch
  user: ${OBSERVABILITY_USERNAME:admin}  # Usuario de OpenSearch
  password: ${OBSERVABILITY_PASSWORD:admin}  # Contraseña de OpenSearch
  host: ${OBSERVABILITY_HOST:localhost}  # Host de OpenSearch
  port: ${OBSERVABILITY_PORT:9200}  # Puerto de OpenSearch
  schema: ${OBSERVABILITY_SCHEMA:http}  # Esquema de OpenSearch
  connect_timeout: 360000  # Tiempo de conexión máximo
  socket_timeout: 360000  # Tiempo de socket máximo
```

En la mayoría de los casos, no es necesario realizar tareas extensas de desarrollo para establecer la conexión entre el proxy y la API Core. Mediante la configuración adecuada de ciertos parámetros de la aplicación a través de variables de entorno, es posible lograr una integración efectiva y sin complicaciones.

A continuación, se presenta una lista detallada de las variables de entorno utilizadas en la configuración del proxy, junto con su descripción correspondiente. Estas variables abarcan desde la definición de rutas y hosts hasta la configuración de la documentación de la API y la integración con servicios externos. Al comprender y manipular estas variables de manera efectiva, los equipos de desarrollo y operaciones pueden optimizar la configuración del proxy para garantizar un funcionamiento suave y eficiente en diversos entornos.

| Variable de Entorno                  | Descripción                                                                                                                                                                                                     |
|--------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| CURRENT_COMMIT                       | Commit actual del código fuente. Usado por el pipeline de APIP.                                                                                                                                                 |
| API_PREFIX                           | Prefijo para la API.                                                                                                                                                                                            |
| PROXY_API_NAME                       | Nombre del proxy. Utilizado en la funcionalidad de validacion de request (Access Validation)                                                                                                                    |
| PROXY_API_VERSION                    | Versión del proxy. Utilizado en la funcionalidad de validacion de request (Access Validation)                                                                                                                   |
| API_CORE_HOST                        | Host de la API Core. Host donde se reenvian las peticiones proxy                                                                                                                                                |
| API_CORE_PATH                        | Ruta de la API Core. Path donde se reenvian las peticiones que llegan al proxy.                                                                                                                                 |
| API_CORE_LIVENESS_PATH               | Ruta de verificación de salud de la API Core. Path del health de la API Core.                                                                                                                                   |
| HEADER_PROPAGATOR_HEADERS            | Lista de headers a propagar en la respuesta de cada peticion enviada al proxy. Lista separada por comas.                                                                                                        |
| ACCESS_VALIDATION_API_URL            | URL del host de validación de acceso (Access Validation).                                                                                                                                                       |
| ACCESS_VALIDATION_API_URL_VALIDATION | Ruta de validación de acceso (Access Validation).                                                                                                                                                               |
| ACCESS_VALIDATION_API_URL_LIVENESS   | Ruta de verificación de salud de validación de acceso (Access Validation).                                                                                                                                      |
| ACCESS_VALIDATION_EXCLUDE_PATHS      | Rutas excluidas de validación de acceso (Access Validation).                                                                                                                                                    |
| ERROR_ALLOW_RETRO_COMPATIBILITY      | Permitir retro compatibilidad de errores. Utilizado en APIs ya existentes que manejan otra estructura de error hacia los clientes.                                                                              |
| LOGGER_EXCLUDE_ENDPOINTS             | Exclusiones de endpoints para log de peticiones y respuestas.                                                                                                                                                   |
| LOGGER_REQUEST_FORMAT_STYLE          | Estilo de formato de solicitud log de peticiones y respuestas. El default para pruebas locales es **http** para producción es obligatorio **json**.                                                             |
| LOGGER_OBFUSCATE_HEADERS             | Headers a obfuscar para log de peticiones y respuestas.                                                                                                                                                         |
| LOGGER_OBFUSCATE_PARAMETERS          | Parámetros a obfuscar para log de peticiones y respuestas.                                                                                                                                                      |
| LOGGER_OBFUSCATE_BODY                | Campos JSON a obfuscar para log de peticiones y respuestas.                                                                                                                                                     |
| OBSERVABILITY_ENABLED                | Habilitar OpenSearch para observabilidad e insights operacionales.                                                                                                                                              |
| OBSERVABILITY_INDEX_NAME             | Índice de OpenSearch para observabilidad e insights operacionales.                                                                                                                                              |
| OBSERVABILITY_USERNAME               | Usuario de OpenSearch para observabilidad e insights operacionales.                                                                                                                                             |
| OBSERVABILITY_PASSWORD               | Contraseña de OpenSearch para observabilidad e insights operacionales.                                                                                                                                          |
| OBSERVABILITY_HOST                   | Host de OpenSearch para observabilidad e insights operacionales.                                                                                                                                                |
| OBSERVABILITY_PORT                   | Puerto de OpenSearch para observabilidad e insights operacionales.                                                                                                                                              |
| OBSERVABILITY_SCHEMA                 | Esquema de OpenSearch para observabilidad e insights operacionales.                                                                                                                                             |
| OTEL_TRACES_EXPORTER                 | Exportador de trazas que se utilizará. El valor por default es **otlp**, para pruebas locales puede utilizarse **logging**.                                                                                     |
| OTEL_METRICS_EXPORTER                | Exportador de métricas que se utilizará. Para producción es obligatorio **none**.                                                                                                                               | 
| OTEL_LOGS_EXPORTER                   | Exportador de registros que se utilizará. Para producción es obligatorio **none**.                                                                                                                              |
| OTEL_EXPORTER_OTLP_ENDPOINT          | Host del colector de Opentelemetry a donde se enviarán las trazas. El valor por default es **http://localhost:4317**                                                                                            |
| OTEL_EXPORTER_OTLP_PROTOCOL          | Protocolo de transporte OTLP que se utilizará para todos los datos de telemetría. El valor pot default es **grpc**                                                                                              |
| OTEL_EXCLUDE_PATHS                   | Listado de paths separados por coma que desan excluise de la traza. Por default se excluye el path **'/health'**.                                                                                               |
| OTEL_EXCLUDE_ATTRIBUTES              | Listado de atributos separados por coma que se utilizarán para comparar la coincidencia con los paths declarados a excluir. Por default se evalúa el valor de los atributos **'http.target'** y **'http.url'**. |
| LOG_FORMAT                  | Formato de salida de los logs. El valor por default es **console**. Para producción debe configurarse como **console_json**                                                                                    |
| LOG_LEVEL                   | Nivel de log para la aplicación. El valor por default es **INFO**.                                                                                                                                             |
| REQUEST_LOG_LEVEL           | Nivel de log para las peticiones. El valor por default es **INFO**.  Para loguear request y response de las peticiones configurar como **TRACE**                                                               |

<a name="-scripts"></a>

## 💻 Scripts

### Iniciar la aplicación Spring Boot:

- Ejecuta la aplicación Spring Boot localmente con Maven. Este comando también compila y empaqueta la aplicación antes de iniciarla.

```sh
./mvnw spring-boot:run
```

<a name="docker"></a>

## 🐳 Docker

El proyecto cuenta con un `dockerfile` y un `docker-compose.yml` de base, listo para utilizar y expandir sus
capacidades.

### Docker Build

Schema: `docker build . -t <user-docker>/<app-name>`

### Docker Compose

Schema: `docker run -d -p 9090:9090 --env-file .env <user-docker>/<app-name>`

### Ejemplo

```sh
docker build -t starter-template .
docker run -d -p 9090:9090 starter-template
```

```sh
docker build -t starter-template .
docker run -it -p 9090:9090 starter-template
```

### Archivo Docker-compose

El archivo `docker-compose.yml` define un entorno de contenedores para ejecutar diferentes servicios lo que facilita probar el desarrollo localmente.

#### Servicios de Opensearch

- Servicio `opensearch-node`: Ejecuta en el puerto **9200** un servidor de Opensearch a donde se enviarán los datos configurados. En caso de querer consultar los documentos insertados se puede ejecutar la siguiente solicitud:

    ``
    curl --location 'http://localhost:9200/proxy-sample/_search' \
    --header 'Authorization: Basic YWRtaW46YWRtaW4='
    ``

- Servicio `opensearch-dashboards`: Expone en el puerto **5601** una interfaz de usuario que permite visualizar los datos de OpenSearch.

#### Servicios de Opentelemetry

- Servicio `otel-collector`: Ejecuta en el puerto **4317** un colector de Opensearch. El colector tiene configurado un receptor `otlp` para aceptar datos de telemetría OpenTelemetry sobre el protocolo gRPC.

- Servicio `zipkin`: Expone el el puerto 9411 un servidor de Zipkin donde el colector de Otel envia los datos de telemetría. Además brinda una interfaz gráfica donde visualizar los datos.

Para ejecutar la aplicación localmente y poder enviar datos al colector se deben configurar las siguientes variables de entorno, además de las detalladas previamente:

| Variable de Entorno                  | Descripción                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|--------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| JAVA_TOOL_OPTIONS                    | Se debe configurar en el argumento `-javaagent` el path donde se localiza el `.jar` del colector a utilizar, para realizar en desarrollo la exportación de datos a Zipkin debe utilizarse el colector de Opentelemetry. En segundo lugar se debe definir en el argumento `-Dotel.javaagent.extensions` el `.jar` de la extensión del colector utilizada para filtrar los paths que necesite, este puede localizarse en el directorio `target/dependency` que se crea en la raíz del proyecto  al ejecutar el comando `./mvnw clean install`. Ejemplo: '-javaagent:opentelemetry-javaagent.jar -Dotel.javaagent.extensions=otel-extension-filter-path.jar' |
| OTEL_TRACES_SAMPLER                  | Nombre del sampler custom definido en la extensión utilizado para el filtrado de trazas. Valor **'filter-sampler'**.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |

- Para ejecutar el script que inicia los contenedores:

```sh
./assets/local/docker-compose/scripts/up.sh
```

### Detener la aplicación

- Para detener la aplicación:

```sh
^C (Ctrl + C) en la terminal en ejecución
./assets/local/docker-compose/scripts/down.sh
```

- Ejecución manual del archivo docker-compose:

```sh
docker-compose -f assets/local/docker-compose/docker-compose.yaml down
docker-compose -f assets/local/docker-compose/docker-compose.yaml up
```
Luego puedes iniciar la aplicación desde tu IDE favorito; cuando termines, puedes dejar los contenedores en ejecución o detenerlos con:

```sh
docker-compose -f assets/local/docker-compose/docker-compose.yaml down
```

<a name="commits"></a>

## 📤 Commits

Para los mensajes de commits se toma como
referencia [`conventional commits`](https://www.conventionalcommits.org/es/v1.0.0/#resumen).

```sh
<type>[optional scope]: <description>

[optional body]

[optional footer]
```

- **type:** chore, docs, feat, fix, refactor (más comunes)
- **scope:** indica la página, componente, funcionalidad
- **description:** comienza en minúsculas y no debe superar los 72 caracteres.

### Ejemplo

```sh
git commit -m "docs(readme): add documentantion to readme"
```

### Breaking change

```sh
git commit -am 'feat!: changes in application'
```

<a name="changelog"></a>

## 📄 Changelog

Todos los cambios notables de este proyecto se documentarán en el archivo [Changelog](./CHANGELOG.md).


<a name="codestyle"></a>

## ✏️ Codestyle

Este arquetipo incorpora una herramienta de desarrollo para ayudar a los programadores a escribir código Java que cumpla con un estándar de codificación. Para el estilo de codificación se toma como base el estándar de Google [`Google Style guide`](https://google.github.io/styleguide/javaguide.html).  
Para facilitar el desarrollo y mejorar la calidad del código se realizaron algunas modificaciones sobre estas reglas  las cuales se detalla a continuación:

#### Supresiones

* Se suprime la validación de [`Javadoc`](https://google.github.io/styleguide/javaguide.html#s7-javadoc) que obliga a documentar todas las clases y miembros
* Se suprime la validación de [`AbbreviationAsWordInName`](https://checkstyle.sourceforge.io/checks/naming/abbreviationaswordinname.html) que valida el ingreso de mayúsculas consecutivas en nombres de identificación

#### Adiciones

* Se añade validación de __imports__ no utilizados que corresponde al estándar de [`Oracle`](https://checkstyle.org/styleguides/sun-code-conventions-19990420/CodeConvTOC.doc.html)
* Se añade validación para comprobar si los archivos terminan con un separador de línea que corresponde al estándar de [`Oracle`](https://checkstyle.sourceforge.io/checks/misc/newlineatendoffile.html)
* Se añade validación para comprobar la visibilidad de los miembros de la clase que corresponde al estándar de [`Oracle`](https://checkstyle.sourceforge.io/checks/design/visibilitymodifier.html)

---


