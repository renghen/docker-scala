/**
 * Docker Engine API The Engine API is an HTTP API served by Docker Engine. It is the API the Docker
 * client uses to communicate with the Engine, so everything the Docker client can do can be done
 * with the API. Most of the client's commands map directly to API endpoints (e.g. `docker ps` is
 * `GET /containers/json`). The notable exception is running containers, which consists of several
 * API calls. # Errors The API uses standard HTTP status codes to indicate the success or failure of
 * the API call. The body of the response will be JSON in the following format: ``` { \"message\":
 * \"page not found\" } ``` # Versioning The API is usually changed in each release, so API calls
 * are versioned to ensure that clients don't break. To lock to a specific version of the API, you
 * prefix the URL with its version, for example, call `/v1.30/info` to use the v1.30 version of the
 * `/info` endpoint. If the API version specified in the URL is not supported by the daemon, a HTTP
 * `400 Bad Request` error message is returned. If you omit the version-prefix, the current version
 * of the API (v1.43) is used. For example, calling `/info` is the same as calling `/v1.43/info`.
 * Using the API without a version-prefix is deprecated and will be removed in a future release.
 * Engine releases in the near future should support this version of the API, so your client will
 * continue to work even if it is talking to a newer Engine. The API uses an open schema model,
 * which means server may add extra properties to responses. Likewise, the server will ignore any
 * extra query parameters and request body properties. When you write clients, you need to ignore
 * additional properties in responses to ensure they do not break when talking to newer daemons. #
 * Authentication Authentication for registries is handled client side. The client has to send
 * authentication details to various endpoints that need to communicate with registries, such as
 * `POST /images/(name)/push`. These are sent as `X-Registry-Auth` header as a [base64url
 * encoded](https://tools.ietf.org/html/rfc4648#section-5) (JSON) string with the following
 * structure: ``` { \"username\": \"string\", \"password\": \"string\", \"email\": \"string\",
 * \"serveraddress\": \"string\" } ``` The `serveraddress` is a domain/IP without a protocol.
 * Throughout this structure, double quotes are required. If you have already got an identity token
 * from the [`/auth` endpoint](#operation/SystemAuth), you can just pass this instead of
 * credentials: ``` { \"identitytoken\": \"9cbaf023786cd7...\" } ```
 *
 * The version of the OpenAPI document: 1.43
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech Do not edit the class manually.
 */
package org.openapitools.client.model

/**
 * Configuration for a container that is portable between hosts. When used as `ContainerConfig`
 * field in an image, `ContainerConfig` is an optional field containing the configuration of the
 * container that was last committed when creating the image. Previous versions of Docker builder
 * used this field to store build cache, and it is not in active use anymore.
 */
case class ContainerConfig(
    /* The hostname to use for the container, as a valid RFC 1123 hostname.  */
    hostname: Option[String] = None,
    /* The domain name to use for the container.  */
    domainname: Option[String] = None,
    /* The user that commands are run as inside the container. */
    user: Option[String] = None,
    /* Whether to attach to `stdin`. */
    attachStdin: Option[Boolean] = None,
    /* Whether to attach to `stdout`. */
    attachStdout: Option[Boolean] = None,
    /* Whether to attach to `stderr`. */
    attachStderr: Option[Boolean] = None,
    /* An object mapping ports to an empty object in the form:  `{\"<port>/<tcp|udp|sctp>\": {}}`  */
    exposedPorts: Option[Map[String, String]] = None,
    /* Attach standard streams to a TTY, including `stdin` if it is not closed.  */
    tty: Option[Boolean] = None,
    /* Open `stdin` */
    openStdin: Option[Boolean] = None,
    /* Close `stdin` after one attached client disconnects */
    stdinOnce: Option[Boolean] = None,
    /* A list of environment variables to set inside the container in the form `[\"VAR=value\", ...]`. A variable without `=` is removed from the environment, rather than to have an empty value.  */
    env: Option[Seq[String]] = None,
    /* Command to run specified as a string or an array of strings.  */
    cmd: Option[Seq[String]] = None,
    healthcheck: Option[HealthConfig] = None,
    /* Command is already escaped (Windows only) */
    argsEscaped: Option[Boolean] = None,
    /* The name (or reference) of the image to use when creating the container, or which was used when the container was created.  */
    image: Option[String] = None,
    /* An object mapping mount point paths inside the container to empty objects.  */
    volumes: Option[Map[String, String]] = None,
    /* The working directory for commands to run in. */
    workingDir: Option[String] = None,
    /* The entry point for the container as a string or an array of strings.  If the array consists of exactly one empty string (`[\"\"]`) then the entry point is reset to system default (i.e., the entry point used by docker when there is no `ENTRYPOINT` instruction in the `Dockerfile`).  */
    entrypoint: Option[Seq[String]] = None,
    /* Disable networking for the container. */
    networkDisabled: Option[Boolean] = None,
    /* MAC address of the container. */
    macAddress: Option[String] = None,
    /* `ONBUILD` metadata that were defined in the image's `Dockerfile`.  */
    onBuild: Option[Seq[String]] = None,
    /* User-defined key/value metadata. */
    labels: Option[Map[String, String]] = None,
    /* Signal to stop a container as a string or unsigned integer.  */
    stopSignal: Option[String] = None,
    /* Timeout to stop a container in seconds. */
    stopTimeout: Option[Int] = None,
    /* Shell for when `RUN`, `CMD`, and `ENTRYPOINT` uses a shell.  */
    shell: Option[Seq[String]] = None)
