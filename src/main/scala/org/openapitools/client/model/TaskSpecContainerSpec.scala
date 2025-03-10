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
 * Container spec for the service. <p><br /></p> > **Note**: ContainerSpec, NetworkAttachmentSpec,
 * and PluginSpec are > mutually exclusive. PluginSpec is only used when the Runtime field > is set
 * to `plugin`. NetworkAttachmentSpec is used when the Runtime > field is set to `attachment`.
 */
case class TaskSpecContainerSpec(
    /* The image name to use for the container */
    image: Option[String] = None,
    /* User-defined key/value data. */
    labels: Option[Map[String, String]] = None,
    /* The command to be run in the image. */
    command: Option[Seq[String]] = None,
    /* Arguments to the command. */
    args: Option[Seq[String]] = None,
    /* The hostname to use for the container, as a valid [RFC 1123](https://tools.ietf.org/html/rfc1123) hostname.  */
    hostname: Option[String] = None,
    /* A list of environment variables in the form `VAR=value`.  */
    env: Option[Seq[String]] = None,
    /* The working directory for commands to run in. */
    dir: Option[String] = None,
    /* The user inside the container. */
    user: Option[String] = None,
    /* A list of additional groups that the container process will run as.  */
    groups: Option[Seq[String]] = None,
    privileges: Option[TaskSpecContainerSpecPrivileges] = None,
    /* Whether a pseudo-TTY should be allocated. */
    TTY: Option[Boolean] = None,
    /* Open `stdin` */
    openStdin: Option[Boolean] = None,
    /* Mount the container's root filesystem as read only. */
    readOnly: Option[Boolean] = None,
    /* Specification for mounts to be added to containers created as part of the service.  */
    mounts: Option[Seq[Mount]] = None,
    /* Signal to stop the container. */
    stopSignal: Option[String] = None,
    /* Amount of time to wait for the container to terminate before forcefully killing it.  */
    stopGracePeriod: Option[Long] = None,
    healthCheck: Option[HealthConfig] = None,
    /* A list of hostname/IP mappings to add to the container's `hosts` file. The format of extra hosts is specified in the [hosts(5)](http://man7.org/linux/man-pages/man5/hosts.5.html) man page:      IP_address canonical_hostname [aliases...]  */
    hosts: Option[Seq[String]] = None,
    dNSConfig: Option[TaskSpecContainerSpecDNSConfig] = None,
    /* Secrets contains references to zero or more secrets that will be exposed to the service.  */
    secrets: Option[Seq[TaskSpecContainerSpecSecretsInner]] = None,
    /* Configs contains references to zero or more configs that will be exposed to the service.  */
    configs: Option[Seq[TaskSpecContainerSpecConfigsInner]] = None,
    /* Isolation technology of the containers running the service. (Windows only)  */
    isolation: Option[TaskSpecContainerSpecEnums.Isolation] = None,
    /* Run an init inside the container that forwards signals and reaps processes. This field is omitted if empty, and the default (as configured on the daemon) is used.  */
    init: Option[Boolean] = None,
    /* Set kernel namedspaced parameters (sysctls) in the container. The Sysctls option on services accepts the same sysctls as the are supported on containers. Note that while the same sysctls are supported, no guarantees or checks are made about their suitability for a clustered environment, and it's up to the user to determine whether a given sysctl will work properly in a Service.  */
    sysctls: Option[Map[String, String]] = None,
    /* A list of kernel capabilities to add to the default set for the container.  */
    capabilityAdd: Option[Seq[String]] = None,
    /* A list of kernel capabilities to drop from the default set for the container.  */
    capabilityDrop: Option[Seq[String]] = None,
    /* A list of resource limits to set in the container. For example: `{\"Name\": \"nofile\", \"Soft\": 1024, \"Hard\": 2048}`\"  */
    ulimits: Option[Seq[ResourcesUlimitsInner]] = None)

object TaskSpecContainerSpecEnums:

  type Isolation = Isolation.Value

  object Isolation extends Enumeration:
    val Default = Value("default")
    val Process = Value("process")
    val Hyperv  = Value("hyperv")

  end Isolation

end TaskSpecContainerSpecEnums
