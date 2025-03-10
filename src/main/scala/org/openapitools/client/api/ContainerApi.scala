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
package org.openapitools.client.api

import org.openapitools.client.model.ContainerCreateRequest
import org.openapitools.client.model.ContainerCreateResponse
import org.openapitools.client.model.ContainerInspectResponse
import org.openapitools.client.model.ContainerPruneResponse
import org.openapitools.client.model.ContainerSummary
import org.openapitools.client.model.ContainerTopResponse
import org.openapitools.client.model.ContainerUpdateRequest
import org.openapitools.client.model.ContainerUpdateResponse
import org.openapitools.client.model.ContainerWaitResponse
import org.openapitools.client.model.ErrorResponse
import org.openapitools.client.model.FilesystemChange
import org.openapitools.client.core.JsonSupport.{*, given}
import sttp.client4.*
import sttp.model.Method

object ContainerApi:
  def apply(baseUrl: String = "http://localhost/v1.43") = new ContainerApi(baseUrl)

end ContainerApi

class ContainerApi(baseUrl: String):

  /**
   * Get a tar archive of a resource in the filesystem of container id.
   *
   * Expected answers: code 200 : (no error) code 400 : ErrorResponse (Bad parameter) code 404 :
   * ErrorResponse (Container or path does not exist) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param path
   *   Resource in the container’s filesystem to archive.
   */
  def containerArchive(id: String, path: String)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/containers/${id}/archive?path=${path}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * A response header `X-Docker-Container-Path-Stat` is returned, containing a base64 - encoded
   * JSON object with some filesystem header information about the path.
   *
   * Expected answers: code 200 : (no error) Headers : X-Docker-Container-Path-Stat - A base64 -
   * encoded JSON object with some filesystem header information about the path code 400 :
   * ErrorResponse (Bad parameter) code 404 : ErrorResponse (Container or path does not exist) code
   * 500 : ErrorResponse (Server error)
   *
   * @param id
   *   ID or name of the container
   * @param path
   *   Resource in the container’s filesystem to archive.
   */
  def containerArchiveInfo(id: String, path: String)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.HEAD, uri"$baseUrl/containers/${id}/archive?path=${path}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Attach to a container to read its output or send it input. You can attach to the same container
   * multiple times and you can reattach to containers that have been detached. Either the `stream`
   * or `logs` parameter must be `true` for this endpoint to do anything. See the [documentation for
   * the `docker attach` command](https://docs.docker.com/engine/reference/commandline/attach/) for
   * more details. ### Hijacking This endpoint hijacks the HTTP connection to transport `stdin`,
   * `stdout`, and `stderr` on the same socket. This is the response from the daemon for an attach
   * request: ``` HTTP/1.1 200 OK Content-Type: application/vnd.docker.raw-stream [STREAM] ``` After
   * the headers and two new lines, the TCP connection can now be used for raw, bidirectional
   * communication between the client and server. To hint potential proxies about connection
   * hijacking, the Docker client can also optionally send connection upgrade headers. For example,
   * the client sends this request to upgrade the connection: ``` POST
   * /containers/16253994b7c4/attach?stream=1&stdout=1 HTTP/1.1 Upgrade: tcp Connection: Upgrade ```
   * The Docker daemon will respond with a `101 UPGRADED` response, and will similarly follow with
   * the raw stream: ``` HTTP/1.1 101 UPGRADED Content-Type: application/vnd.docker.raw-stream
   * Connection: Upgrade Upgrade: tcp [STREAM] ``` ### Stream format When the TTY setting is
   * disabled in [`POST /containers/create`](#operation/ContainerCreate), the HTTP Content-Type
   * header is set to application/vnd.docker.multiplexed-stream and the stream over the hijacked
   * connected is multiplexed to separate out `stdout` and `stderr`. The stream consists of a series
   * of frames, each containing a header and a payload. The header contains the information which
   * the stream writes (`stdout` or `stderr`). It also contains the size of the associated frame
   * encoded in the last four bytes (`uint32`). It is encoded on the first eight bytes like this:
   * \```go header := [8]byte{STREAM_TYPE, 0, 0, 0, SIZE1, SIZE2, SIZE3, SIZE4} ``` `STREAM_TYPE`
   * can be: - 0: `stdin` (is written on `stdout`) - 1: `stdout` - 2: `stderr` `SIZE1, SIZE2, SIZE3,
   * SIZE4` are the four bytes of the `uint32` size encoded as big endian. Following the header is
   * the payload, which is the specified number of bytes of `STREAM_TYPE`. The simplest way to
   * implement this protocol is the following: 1. Read 8 bytes. 2. Choose `stdout` or `stderr`
   * depending on the first byte. 3. Extract the frame size from the last four bytes. 4. Read the
   * extracted size and output it on the correct output. 5. Goto 1. ### Stream format when using a
   * TTY When the TTY setting is enabled in [`POST /containers/create`](#operation/ContainerCreate),
   * the stream is not multiplexed. The data exchanged over the hijacked connection is simply the
   * raw data from the process PTY and client's `stdin`.
   *
   * Expected answers: code 101 : (no error, hints proxy about hijacking) code 200 : (no error, no
   * upgrade header found) code 400 : ErrorResponse (bad parameter) code 404 : ErrorResponse (no
   * such container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param detachKeys
   *   Override the key sequence for detaching a container.Format is a single character `[a-Z]` or
   *   `ctrl-<value>` where `<value>` is one of: `a-z`, `@`, `^`, `[`, `,` or `_`.
   * @param logs
   *   Replay previous logs from the container. This is useful for attaching to a container that has
   *   started and you want to output everything since the container started. If `stream` is also
   *   enabled, once all the previous output has been returned, it will seamlessly transition into
   *   streaming current output.
   * @param stream
   *   Stream attached streams from the time the request was made onwards.
   * @param stdin
   *   Attach to `stdin`
   * @param stdout
   *   Attach to `stdout`
   * @param stderr
   *   Attach to `stderr`
   */
  def containerAttach(
      id: String,
      detachKeys: Option[String] = None,
      logs: Option[Boolean] = None,
      stream: Option[Boolean] = None,
      stdin: Option[Boolean] = None,
      stdout: Option[Boolean] = None,
      stderr: Option[Boolean] = None,
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(
        Method.POST,
        uri"$baseUrl/containers/${id}/attach?detachKeys=${detachKeys}&logs=${logs}&stream=${stream}&stdin=${stdin}&stdout=${stdout}&stderr=${stderr}",
      )
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 101 : (no error, hints proxy about hijacking) code 200 : (no error, no
   * upgrade header found) code 400 : ErrorResponse (bad parameter) code 404 : ErrorResponse (no
   * such container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param detachKeys
   *   Override the key sequence for detaching a container.Format is a single character `[a-Z]` or
   *   `ctrl-<value>` where `<value>` is one of: `a-z`, `@`, `^`, `[`, `,`, or `_`.
   * @param logs
   *   Return logs
   * @param stream
   *   Return stream
   * @param stdin
   *   Attach to `stdin`
   * @param stdout
   *   Attach to `stdout`
   * @param stderr
   *   Attach to `stderr`
   */
  def containerAttachWebsocket(
      id: String,
      detachKeys: Option[String] = None,
      logs: Option[Boolean] = None,
      stream: Option[Boolean] = None,
      stdin: Option[Boolean] = None,
      stdout: Option[Boolean] = None,
      stderr: Option[Boolean] = None,
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(
        Method.GET,
        uri"$baseUrl/containers/${id}/attach/ws?detachKeys=${detachKeys}&logs=${logs}&stream=${stream}&stdin=${stdin}&stdout=${stdout}&stderr=${stderr}",
      )
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Returns which files in a container's filesystem have been added, deleted, or modified. The
   * `Kind` of modification can be one of: - `0`: Modified (\"C\") - `1`: Added (\"A\") - `2`:
   * Deleted (\"D\")
   *
   * Expected answers: code 200 : Seq[FilesystemChange] (The list of changes) code 404 :
   * ErrorResponse (no such container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   */
  def containerChanges(id: String)
      : Request[Either[ResponseException[String, Exception], Seq[FilesystemChange]]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/containers/${id}/changes")
      .contentType("application/json")
      .response(asJson[Seq[FilesystemChange]])

  /**
   * Expected answers: code 201 : ContainerCreateResponse (Container created successfully) code 400
   * : ErrorResponse (bad parameter) code 404 : ErrorResponse (no such image) code 409 :
   * ErrorResponse (conflict) code 500 : ErrorResponse (server error)
   *
   * @param body
   *   Container to create
   * @param name
   *   Assign the specified name to the container. Must match `/?[a-zA-Z0-9][a-zA-Z0-9_.-]+`.
   * @param platform
   *   Platform in the format `os[/arch[/variant]]` used for image lookup. When specified, the
   *   daemon checks if the requested image is present in the local image cache with the given OS
   *   and Architecture, and otherwise returns a `404` status. If the option is not set, the host's
   *   native OS and Architecture are used to look up the image in the image cache. However, if no
   *   platform is passed and the given image does exist in the local image cache, but its OS or
   *   architecture does not match, the container is created with the available image, and a warning
   *   is added to the `Warnings` field in the response, for example; WARNING: The requested image's
   *   platform (linux/arm64/v8) does not match the detected host platform (linux/amd64) and no
   *   specific platform was requested
   */
  def containerCreate(
      body: ContainerCreateRequest,
      name: Option[String] = None,
      platform: Option[String] = None,
    ): Request[Either[ResponseException[String, Exception], ContainerCreateResponse]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/create?name=${name}&platform=${platform}")
      .contentType("application/json")
      .body(body)
      .response(asJson[ContainerCreateResponse])

  /**
   * Expected answers: code 204 : (no error) code 400 : ErrorResponse (bad parameter) code 404 :
   * ErrorResponse (no such container) code 409 : ErrorResponse (conflict) code 500 : ErrorResponse
   * (server error)
   *
   * @param id
   *   ID or name of the container
   * @param `v`
   *   Remove anonymous volumes associated with the container.
   * @param force
   *   If the container is running, kill it before removing it.
   * @param link
   *   Remove the specified link associated with the container.
   */
  def containerDelete(
      id: String,
      `v`: Option[Boolean] = None,
      force: Option[Boolean] = None,
      link: Option[Boolean] = None,
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.DELETE, uri"$baseUrl/containers/${id}?v=${`v`}&force=${force}&link=${link}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Export the contents of a container as a tarball.
   *
   * Expected answers: code 200 : (no error) code 404 : ErrorResponse (no such container) code 500 :
   * ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   */
  def containerExport(id: String): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/containers/${id}/export")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Return low-level information about a container.
   *
   * Expected answers: code 200 : ContainerInspectResponse (no error) code 404 : ErrorResponse (no
   * such container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param size
   *   Return the size of container as fields `SizeRw` and `SizeRootFs`
   */
  def containerInspect(id: String, size: Option[Boolean] = None)
      : Request[Either[ResponseException[String, Exception], ContainerInspectResponse]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/containers/${id}/json?size=${size}")
      .contentType("application/json")
      .response(asJson[ContainerInspectResponse])

  /**
   * Send a POSIX signal to a container, defaulting to killing to the container.
   *
   * Expected answers: code 204 : (no error) code 404 : ErrorResponse (no such container) code 409 :
   * ErrorResponse (container is not running) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param signal
   *   Signal to send to the container as an integer or string (e.g. `SIGINT`).
   */
  def containerKill(id: String, signal: Option[String] = None)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/kill?signal=${signal}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Returns a list of containers. For details on the format, see the [inspect
   * endpoint](#operation/ContainerInspect). Note that it uses a different, smaller representation
   * of a container than inspecting a single container. For example, the list of linked containers
   * is not propagated .
   *
   * Expected answers: code 200 : Seq[ContainerSummary] (no error) code 400 : ErrorResponse (bad
   * parameter) code 500 : ErrorResponse (server error)
   *
   * @param all
   *   Return all containers. By default, only running containers are shown.
   * @param limit
   *   Return this number of most recently created containers, including non-running ones.
   * @param size
   *   Return the size of container as fields `SizeRw` and `SizeRootFs`.
   * @param filters
   *   Filters to process on the container list, encoded as JSON (a `map[string][]string`). For
   *   example, `{\"status\": [\"paused\"]}` will only return paused containers. Available filters:
   *   \- `ancestor`=(`<image-name>[:<tag>]`, `<image id>`, or `<image@digest>`) -
   *   `before`=(`<container id>` or `<container name>`) -
   *   `expose`=(`<port>[/<proto>]`|`<startport-endport>/[<proto>]`) - `exited=<int>` containers
   *   with exit code of `<int>` - `health`=(`starting`|`healthy`|`unhealthy`|`none`) - `id=<ID>` a
   *   container's ID - `isolation=`(`default`|`process`|`hyperv`) (Windows daemon only) -
   *   `is-task=`(`true`|`false`) - `label=key` or `label=\"key=value\"` of a container label -
   *   `name=<name>` a container's name - `network`=(`<network id>` or `<network name>`) -
   *   `publish`=(`<port>[/<proto>]`|`<startport-endport>/[<proto>]`) - `since`=(`<container id>` or
   *   `<container name>`) -
   *   `status=`(`created`|`restarting`|`running`|`removing`|`paused`|`exited`|`dead`) -
   *   `volume`=(`<volume name>` or `<mount point destination>`)
   */
  def containerList(
      all: Option[Boolean] = None,
      limit: Option[Int] = None,
      size: Option[Boolean] = None,
      filters: Option[String] = None,
    ): Request[Either[ResponseException[String, Exception], Seq[ContainerSummary]]] =
    basicRequest
      .method(
        Method.GET,
        uri"$baseUrl/containers/json?all=${all}&limit=${limit}&size=${size}&filters=${filters}",
      )
      .contentType("application/json")
      .response(asJson[Seq[ContainerSummary]])

  /**
   * Get `stdout` and `stderr` logs from a container. Note: This endpoint works only for containers
   * with the `json-file` or `journald` logging driver.
   *
   * Expected answers: code 200 : File (logs returned as a stream in response body. For the stream
   * format, [see the documentation for the attach endpoint](#operation/ContainerAttach). Note that
   * unlike the attach endpoint, the logs endpoint does not upgrade the connection and does not set
   * Content-Type. ) code 404 : ErrorResponse (no such container) code 500 : ErrorResponse (server
   * error)
   *
   * @param id
   *   ID or name of the container
   * @param follow
   *   Keep connection after returning logs.
   * @param stdout
   *   Return logs from `stdout`
   * @param stderr
   *   Return logs from `stderr`
   * @param since
   *   Only return logs since this time, as a UNIX timestamp
   * @param until
   *   Only return logs before this time, as a UNIX timestamp
   * @param timestamps
   *   Add timestamps to every log line
   * @param tail
   *   Only return this number of log lines from the end of the logs. Specify as an integer or `all`
   *   to output all log lines.
   */
  def containerLogs(
      id: String,
      follow: Option[Boolean] = None,
      stdout: Option[Boolean] = None,
      stderr: Option[Boolean] = None,
      since: Option[Int] = None,
      until: Option[Int] = None,
      timestamps: Option[Boolean] = None,
      tail: Option[String] = None,
    ): Request[Either[ResponseException[String, Exception], String]] =
    basicRequest
      .method(
        Method.GET,
        uri"$baseUrl/containers/${id}/logs?follow=${follow}&stdout=${stdout}&stderr=${stderr}&since=${since}&until=${until}&timestamps=${timestamps}&tail=${tail}",
      )
      .contentType("application/json")
      .response(asJson[String])

  /**
   * Use the freezer cgroup to suspend all processes in a container. Traditionally, when suspending
   * a process the `SIGSTOP` signal is used, which is observable by the process being suspended.
   * With the freezer cgroup the process is unaware, and unable to capture, that it is being
   * suspended, and subsequently resumed.
   *
   * Expected answers: code 204 : (no error) code 404 : ErrorResponse (no such container) code 500 :
   * ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   */
  def containerPause(id: String): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/pause")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 200 : ContainerPruneResponse (No error) code 500 : ErrorResponse (Server
   * error)
   *
   * @param filters
   *   Filters to process on the prune list, encoded as JSON (a `map[string][]string`). Available
   *   filters: - `until=<timestamp>` Prune containers created before this timestamp. The
   *   `<timestamp>` can be Unix timestamps, date formatted timestamps, or Go duration strings (e.g.
   *   `10m`, `1h30m`) computed relative to the daemon machine’s time. - `label` (`label=<key>`,
   *   `label=<key>=<value>`, `label!=<key>`, or `label!=<key>=<value>`) Prune containers with (or
   *   without, in case `label!=...` is used) the specified labels.
   */
  def containerPrune(filters: Option[String] = None)
      : Request[Either[ResponseException[String, Exception], ContainerPruneResponse]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/prune?filters=${filters}")
      .contentType("application/json")
      .response(asJson[ContainerPruneResponse])

  /**
   * Expected answers: code 204 : (no error) code 404 : ErrorResponse (no such container) code 409 :
   * ErrorResponse (name already in use) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param name
   *   New name for the container
   */
  def containerRename(id: String, name: String)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/rename?name=${name}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Resize the TTY for a container.
   *
   * Expected answers: code 200 : (no error) code 404 : ErrorResponse (no such container) code 500 :
   * ErrorResponse (cannot resize container)
   *
   * @param id
   *   ID or name of the container
   * @param `h`
   *   Height of the TTY session in characters
   * @param `w`
   *   Width of the TTY session in characters
   */
  def containerResize(
      id: String,
      `h`: Option[Int] = None,
      `w`: Option[Int] = None,
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/resize?h=${`h`}&w=${`w`}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 204 : (no error) code 404 : ErrorResponse (no such container) code 500 :
   * ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param signal
   *   Signal to send to the container as an integer or string (e.g. `SIGINT`).
   * @param `t`
   *   Number of seconds to wait before killing the container
   */
  def containerRestart(
      id: String,
      signal: Option[String] = None,
      `t`: Option[Int] = None,
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/restart?signal=${signal}&t=${`t`}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 204 : (no error) code 304 : (container already started) code 404 :
   * ErrorResponse (no such container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param detachKeys
   *   Override the key sequence for detaching a container. Format is a single character `[a-Z]` or
   *   `ctrl-<value>` where `<value>` is one of: `a-z`, `@`, `^`, `[`, `,` or `_`.
   */
  def containerStart(id: String, detachKeys: Option[String] = None)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/start?detachKeys=${detachKeys}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * This endpoint returns a live stream of a container’s resource usage statistics. The
   * `precpu_stats` is the CPU statistic of the *previous* read, and is used to calculate the CPU
   * usage percentage. It is not an exact copy of the `cpu_stats` field. If either
   * `precpu_stats.online_cpus` or `cpu_stats.online_cpus` is nil then for compatibility with older
   * daemons the length of the corresponding `cpu_usage.percpu_usage` array should be used. On a
   * cgroup v2 host, the following fields are not set * `blkio_stats`: all fields other than
   * `io_service_bytes_recursive` * `cpu_stats`: `cpu_usage.percpu_usage` * `memory_stats`:
   * `max_usage` and `failcnt` Also, `memory_stats.stats` fields are incompatible with cgroup v1. To
   * calculate the values shown by the `stats` command of the docker cli tool the following formulas
   * can be used: * used_memory = `memory_stats.usage - memory_stats.stats.cache` * available_memory
   * \= `memory_stats.limit` * Memory usage % = `(used_memory / available_memory) * 100.0` *
   * cpu_delta = `cpu_stats.cpu_usage.total_usage - precpu_stats.cpu_usage.total_usage` *
   * system_cpu_delta = `cpu_stats.system_cpu_usage - precpu_stats.system_cpu_usage` * number_cpus =
   * `lenght(cpu_stats.cpu_usage.percpu_usage)` or `cpu_stats.online_cpus` * CPU usage % =
   * `(cpu_delta / system_cpu_delta) * number_cpus * 100.0`
   *
   * Expected answers: code 200 : Any (no error) code 404 : ErrorResponse (no such container) code
   * 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param stream
   *   Stream the output. If false, the stats will be output once and then it will disconnect.
   * @param oneShot
   *   Only get a single stat instead of waiting for 2 cycles. Must be used with `stream=false`.
   */
  def containerStats(
      id: String,
      stream: Option[Boolean] = None,
      oneShot: Option[Boolean] = None,
    ): Request[Either[ResponseException[String, Exception], Any]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/containers/${id}/stats?stream=${stream}&one-shot=${oneShot}")
      .contentType("application/json")
      .response(asJson[String])

  /**
   * Expected answers: code 204 : (no error) code 304 : (container already stopped) code 404 :
   * ErrorResponse (no such container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param signal
   *   Signal to send to the container as an integer or string (e.g. `SIGINT`).
   * @param `t`
   *   Number of seconds to wait before killing the container
   */
  def containerStop(
      id: String,
      signal: Option[String] = None,
      `t`: Option[Int] = None,
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/stop?signal=${signal}&t=${`t`}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * On Unix systems, this is done by running the `ps` command. This endpoint is not supported on
   * Windows.
   *
   * Expected answers: code 200 : ContainerTopResponse (no error) code 404 : ErrorResponse (no such
   * container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param psArgs
   *   The arguments to pass to `ps`. For example, `aux`
   */
  def containerTop(id: String, psArgs: Option[String] = None)
      : Request[Either[ResponseException[String, Exception], ContainerTopResponse]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/containers/${id}/top?ps_args=${psArgs}")
      .contentType("application/json")
      .response(asJson[ContainerTopResponse])

  /**
   * Resume a container which has been paused.
   *
   * Expected answers: code 204 : (no error) code 404 : ErrorResponse (no such container) code 500 :
   * ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   */
  def containerUnpause(id: String): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/unpause")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Change various configuration options of a container without having to recreate it.
   *
   * Expected answers: code 200 : ContainerUpdateResponse (The container has been updated.) code 404
   * : ErrorResponse (no such container) code 500 : ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param update
   */
  def containerUpdate(id: String, update: ContainerUpdateRequest)
      : Request[Either[ResponseException[String, Exception], ContainerUpdateResponse]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/update")
      .contentType("application/json")
      .body(update)
      .response(asJson[ContainerUpdateResponse])

  /**
   * Block until a container stops, then returns the exit code.
   *
   * Expected answers: code 200 : ContainerWaitResponse (The container has exit.) code 400 :
   * ErrorResponse (bad parameter) code 404 : ErrorResponse (no such container) code 500 :
   * ErrorResponse (server error)
   *
   * @param id
   *   ID or name of the container
   * @param condition
   *   Wait until a container state reaches the given condition. Defaults to `not-running` if
   *   omitted or empty.
   */
  def containerWait(id: String, condition: Option[String] = None)
      : Request[Either[ResponseException[String, Exception], ContainerWaitResponse]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/containers/${id}/wait?condition=${condition}")
      .contentType("application/json")
      .response(asJson[ContainerWaitResponse])

  /**
   * Upload a tar archive to be extracted to a path in the filesystem of container id. `path`
   * parameter is asserted to be a directory. If it exists as a file, 400 error will be returned
   * with message \"not a directory\".
   *
   * Expected answers: code 200 : (The content was extracted successfully) code 400 : ErrorResponse
   * (Bad parameter) code 403 : ErrorResponse (Permission denied, the volume or container rootfs is
   * marked as read-only.) code 404 : ErrorResponse (No such container or path does not exist inside
   * the container) code 500 : ErrorResponse (Server error)
   *
   * @param id
   *   ID or name of the container
   * @param path
   *   Path to a directory in the container to extract the archive’s contents into.
   * @param inputStream
   *   The input stream must be a tar archive compressed with one of the following algorithms:
   *   `identity` (no compression), `gzip`, `bzip2`, or `xz`.
   * @param noOverwriteDirNonDir
   *   If `1`, `true`, or `True` then it will be an error if unpacking the given content would cause
   *   an existing directory to be replaced with a non-directory and vice versa.
   * @param copyUIDGID
   *   If `1`, `true`, then it will copy UID/GID maps to the dest file or dir
   */
  def putContainerArchive(
      id: String,
      path: String,
      inputStream: String,
      noOverwriteDirNonDir: Option[String] = None,
      copyUIDGID: Option[String] = None,
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(
        Method.PUT,
        uri"$baseUrl/containers/${id}/archive?path=${path}&noOverwriteDirNonDir=${noOverwriteDirNonDir}&copyUIDGID=${copyUIDGID}",
      )
      .contentType("application/x-tar")
      .body(inputStream)
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

end ContainerApi
