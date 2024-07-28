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
 * A container's resources (cgroups config, ulimits, etc)
 */
case class Resources(
    /* An integer value representing this container's relative CPU weight versus other containers.  */
    cpuShares: Option[Int] = None,
    /* Memory limit in bytes. */
    memory: Option[Long] = None,
    /* Path to `cgroups` under which the container's `cgroup` is created. If the path is not absolute, the path is considered to be relative to the `cgroups` path of the init process. Cgroups are created if they do not already exist.  */
    cgroupParent: Option[String] = None,
    /* Block IO weight (relative weight). */
    blkioWeight: Option[Int] = None,
    /* Block IO weight (relative device weight) in the form:  ``` [{\"Path\": \"device_path\", \"Weight\": weight}] ```  */
    blkioWeightDevice: Option[Seq[ResourcesBlkioWeightDeviceInner]] = None,
    /* Limit read rate (bytes per second) from a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    blkioDeviceReadBps: Option[Seq[ThrottleDevice]] = None,
    /* Limit write rate (bytes per second) to a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    blkioDeviceWriteBps: Option[Seq[ThrottleDevice]] = None,
    /* Limit read rate (IO per second) from a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    blkioDeviceReadIOps: Option[Seq[ThrottleDevice]] = None,
    /* Limit write rate (IO per second) to a device, in the form:  ``` [{\"Path\": \"device_path\", \"Rate\": rate}] ```  */
    blkioDeviceWriteIOps: Option[Seq[ThrottleDevice]] = None,
    /* The length of a CPU period in microseconds. */
    cpuPeriod: Option[Long] = None,
    /* Microseconds of CPU time that the container can get in a CPU period.  */
    cpuQuota: Option[Long] = None,
    /* The length of a CPU real-time period in microseconds. Set to 0 to allocate no time allocated to real-time tasks.  */
    cpuRealtimePeriod: Option[Long] = None,
    /* The length of a CPU real-time runtime in microseconds. Set to 0 to allocate no time allocated to real-time tasks.  */
    cpuRealtimeRuntime: Option[Long] = None,
    /* CPUs in which to allow execution (e.g., `0-3`, `0,1`).  */
    cpusetCpus: Option[String] = None,
    /* Memory nodes (MEMs) in which to allow execution (0-3, 0,1). Only effective on NUMA systems.  */
    cpusetMems: Option[String] = None,
    /* A list of devices to add to the container. */
    devices: Option[Seq[DeviceMapping]] = None,
    /* a list of cgroup rules to apply to the container */
    deviceCgroupRules: Option[Seq[String]] = None,
    /* A list of requests for devices to be sent to device drivers.  */
    deviceRequests: Option[Seq[DeviceRequest]] = None,
    /* Hard limit for kernel TCP buffer memory (in bytes). Depending on the OCI runtime in use, this option may be ignored. It is no longer supported by the default (runc) runtime.  This field is omitted when empty.  */
    kernelMemoryTCP: Option[Long] = None,
    /* Memory soft limit in bytes. */
    memoryReservation: Option[Long] = None,
    /* Total memory limit (memory + swap). Set as `-1` to enable unlimited swap.  */
    memorySwap: Option[Long] = None,
    /* Tune a container's memory swappiness behavior. Accepts an integer between 0 and 100.  */
    memorySwappiness: Option[Long] = None,
    /* CPU quota in units of 10<sup>-9</sup> CPUs. */
    nanoCpus: Option[Long] = None,
    /* Disable OOM Killer for the container. */
    oomKillDisable: Option[Boolean] = None,
    /* Run an init inside the container that forwards signals and reaps processes. This field is omitted if empty, and the default (as configured on the daemon) is used.  */
    init: Option[Boolean] = None,
    /* Tune a container's PIDs limit. Set `0` or `-1` for unlimited, or `null` to not change.  */
    pidsLimit: Option[Long] = None,
    /* A list of resource limits to set in the container. For example:  ``` {\"Name\": \"nofile\", \"Soft\": 1024, \"Hard\": 2048} ```  */
    ulimits: Option[Seq[ResourcesUlimitsInner]] = None,
    /* The number of usable CPUs (Windows only).  On Windows Server containers, the processor resource controls are mutually exclusive. The order of precedence is `CPUCount` first, then `CPUShares`, and `CPUPercent` last.  */
    cpuCount: Option[Long] = None,
    /* The usable percentage of the available CPUs (Windows only).  On Windows Server containers, the processor resource controls are mutually exclusive. The order of precedence is `CPUCount` first, then `CPUShares`, and `CPUPercent` last.  */
    cpuPercent: Option[Long] = None,
    /* Maximum IOps for the container system drive (Windows only) */
    iOMaximumIOps: Option[Long] = None,
    /* Maximum IO in bytes per second for the container system drive (Windows only).  */
    iOMaximumBandwidth: Option[Long] = None)
