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
 * Container configuration that depends on the host we are running on
 */
case class HostConfig(
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
    iOMaximumBandwidth: Option[Long] = None,
    /* A list of volume bindings for this container. Each volume binding is a string in one of these forms:  - `host-src:container-dest[:options]` to bind-mount a host path   into the container. Both `host-src`, and `container-dest` must   be an _absolute_ path. - `volume-name:container-dest[:options]` to bind-mount a volume   managed by a volume driver into the container. `container-dest`   must be an _absolute_ path.  `options` is an optional, comma-delimited list of:  - `nocopy` disables automatic copying of data from the container   path to the volume. The `nocopy` flag only applies to named volumes. - `[ro|rw]` mounts a volume read-only or read-write, respectively.   If omitted or set to `rw`, volumes are mounted read-write. - `[z|Z]` applies SELinux labels to allow or deny multiple containers   to read and write to the same volume.     - `z`: a _shared_ content label is applied to the content. This       label indicates that multiple containers can share the volume       content, for both reading and writing.     - `Z`: a _private unshared_ label is applied to the content.       This label indicates that only the current container can use       a private volume. Labeling systems such as SELinux require       proper labels to be placed on volume content that is mounted       into a container. Without a label, the security system can       prevent a container's processes from using the content. By       default, the labels set by the host operating system are not       modified. - `[[r]shared|[r]slave|[r]private]` specifies mount   [propagation behavior](https://www.kernel.org/doc/Documentation/filesystems/sharedsubtree.txt).   This only applies to bind-mounted volumes, not internal volumes   or named volumes. Mount propagation requires the source mount   point (the location where the source directory is mounted in the   host operating system) to have the correct propagation properties.   For shared volumes, the source mount point must be set to `shared`.   For slave volumes, the mount must be set to either `shared` or   `slave`.  */
    binds: Option[Seq[String]] = None,
    /* Path to a file where the container ID is written */
    containerIDFile: Option[String] = None,
    logConfig: Option[HostConfigAllOfLogConfig] = None,
    /* Network mode to use for this container. Supported standard values are: `bridge`, `host`, `none`, and `container:<name|id>`. Any other value is taken as a custom network's name to which this container should connect to.  */
    networkMode: Option[String] = None,
    /* PortMap describes the mapping of container ports to host ports, using the container's port-number and protocol as key in the format `<port>/<protocol>`, for example, `80/udp`.  If a container's port is mapped for multiple protocols, separate entries are added to the mapping table.  */
    portBindings: Option[Map[String, Seq[PortBinding]]] = None,
    restartPolicy: Option[RestartPolicy] = None,
    /* Automatically remove the container when the container's process exits. This has no effect if `RestartPolicy` is set.  */
    autoRemove: Option[Boolean] = None,
    /* Driver that this container uses to mount volumes. */
    volumeDriver: Option[String] = None,
    /* A list of volumes to inherit from another container, specified in the form `<container name>[:<ro|rw>]`.  */
    volumesFrom: Option[Seq[String]] = None,
    /* Specification for mounts to be added to the container.  */
    mounts: Option[Seq[Mount]] = None,
    /* Initial console size, as an `[height, width]` array.  */
    consoleSize: Option[Seq[Int]] = None,
    /* Arbitrary non-identifying metadata attached to container and provided to the runtime when the container is started.  */
    annotations: Option[Map[String, String]] = None,
    /* A list of kernel capabilities to add to the container. Conflicts with option 'Capabilities'.  */
    capAdd: Option[Seq[String]] = None,
    /* A list of kernel capabilities to drop from the container. Conflicts with option 'Capabilities'.  */
    capDrop: Option[Seq[String]] = None,
    /* cgroup namespace mode for the container. Possible values are:  - `\"private\"`: the container runs in its own private cgroup namespace - `\"host\"`: use the host system's cgroup namespace  If not specified, the daemon default is used, which can either be `\"private\"` or `\"host\"`, depending on daemon version, kernel support and configuration.  */
    cgroupnsMode: Option[HostConfigEnums.CgroupnsMode] = None,
    /* A list of DNS servers for the container to use. */
    dns: Option[Seq[String]] = None,
    /* A list of DNS options. */
    dnsOptions: Option[Seq[String]] = None,
    /* A list of DNS search domains. */
    dnsSearch: Option[Seq[String]] = None,
    /* A list of hostnames/IP mappings to add to the container's `/etc/hosts` file. Specified in the form `[\"hostname:IP\"]`.  */
    extraHosts: Option[Seq[String]] = None,
    /* A list of additional groups that the container process will run as.  */
    groupAdd: Option[Seq[String]] = None,
    /* IPC sharing mode for the container. Possible values are:  - `\"none\"`: own private IPC namespace, with /dev/shm not mounted - `\"private\"`: own private IPC namespace - `\"shareable\"`: own private IPC namespace, with a possibility to share it with other containers - `\"container:<name|id>\"`: join another (shareable) container's IPC namespace - `\"host\"`: use the host system's IPC namespace  If not specified, daemon default is used, which can either be `\"private\"` or `\"shareable\"`, depending on daemon version and configuration.  */
    ipcMode: Option[String] = None,
    /* Cgroup to use for the container. */
    cgroup: Option[String] = None,
    /* A list of links for the container in the form `container_name:alias`.  */
    links: Option[Seq[String]] = None,
    /* An integer value containing the score given to the container in order to tune OOM killer preferences.  */
    oomScoreAdj: Option[Int] = None,
    /* Set the PID (Process) Namespace mode for the container. It can be either:  - `\"container:<name|id>\"`: joins another container's PID namespace - `\"host\"`: use the host's PID namespace inside the container  */
    pidMode: Option[String] = None,
    /* Gives the container full access to the host. */
    privileged: Option[Boolean] = None,
    /* Allocates an ephemeral host port for all of a container's exposed ports.  Ports are de-allocated when the container stops and allocated when the container starts. The allocated port might be changed when restarting the container.  The port is selected from the ephemeral port range that depends on the kernel. For example, on Linux the range is defined by `/proc/sys/net/ipv4/ip_local_port_range`.  */
    publishAllPorts: Option[Boolean] = None,
    /* Mount the container's root filesystem as read only. */
    readonlyRootfs: Option[Boolean] = None,
    /* A list of string values to customize labels for MLS systems, such as SELinux.  */
    securityOpt: Option[Seq[String]] = None,
    /* Storage driver options for this container, in the form `{\"size\": \"120G\"}`.  */
    storageOpt: Option[Map[String, String]] = None,
    /* A map of container directories which should be replaced by tmpfs mounts, and their corresponding mount options. For example:  ``` { \"/run\": \"rw,noexec,nosuid,size=65536k\" } ```  */
    tmpfs: Option[Map[String, String]] = None,
    /* UTS namespace to use for the container. */
    uTSMode: Option[String] = None,
    /* Sets the usernamespace mode for the container when usernamespace remapping option is enabled.  */
    usernsMode: Option[String] = None,
    /* Size of `/dev/shm` in bytes. If omitted, the system uses 64MB.  */
    shmSize: Option[Long] = None,
    /* A list of kernel parameters (sysctls) to set in the container. For example:  ``` {\"net.ipv4.ip_forward\": \"1\"} ```  */
    sysctls: Option[Map[String, String]] = None,
    /* Runtime to use with this container. */
    runtime: Option[String] = None,
    /* Isolation technology of the container. (Windows only)  */
    isolation: Option[HostConfigEnums.Isolation] = None,
    /* The list of paths to be masked inside the container (this overrides the default set of paths).  */
    maskedPaths: Option[Seq[String]] = None,
    /* The list of paths to be set as read-only inside the container (this overrides the default set of paths).  */
    readonlyPaths: Option[Seq[String]] = None)

object HostConfigEnums:

  type CgroupnsMode = CgroupnsMode.Value
  type Isolation    = Isolation.Value

  object CgroupnsMode extends Enumeration:
    val `Private` = Value("private")
    val Host      = Value("host")

  end CgroupnsMode

  object Isolation extends Enumeration:
    val Default = Value("default")
    val Process = Value("process")
    val Hyperv  = Value("hyperv")

  end Isolation

end HostConfigEnums
