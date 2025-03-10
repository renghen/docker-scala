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

import org.openapitools.client.model.ErrorResponse
import java.io.File
import org.openapitools.client.model.Plugin
import org.openapitools.client.model.PluginPrivilege
import org.openapitools.client.core.JsonSupport.{*, given}
import sttp.client4.*
import sttp.model.Method

object PluginApi:
  def apply(baseUrl: String = "http://localhost/v1.43") = new PluginApi(baseUrl)

end PluginApi

class PluginApi(baseUrl: String):

  /**
   * Expected answers: code 200 : Seq[PluginPrivilege] (no error) code 500 : ErrorResponse (server
   * error)
   *
   * @param remote
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   */
  def getPluginPrivileges(remote: String)
      : Request[Either[ResponseException[String, Exception], Seq[PluginPrivilege]]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/plugins/privileges?remote=${remote}")
      .contentType("application/json")
      .response(asJson[Seq[PluginPrivilege]])

  /**
   * Expected answers: code 204 : (no error) code 500 : ErrorResponse (server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   * @param tarContext
   *   Path to tar containing plugin rootfs and manifest
   */
  def pluginCreate(name: String, tarContext: Option[String] = None)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/plugins/create?name=${name}")
      .contentType("application/x-tar")
      .body(tarContext)
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 200 : Plugin (no error) code 404 : ErrorResponse (plugin is not
   * installed) code 500 : ErrorResponse (server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   * @param force
   *   Disable the plugin before removing. This may result in issues if the plugin is in use by a
   *   container.
   */
  def pluginDelete(name: String, force: Option[Boolean] = None)
      : Request[Either[ResponseException[String, Exception], Plugin]] =
    basicRequest
      .method(Method.DELETE, uri"$baseUrl/plugins/${name}?force=${force}")
      .contentType("application/json")
      .response(asJson[Plugin])

  /**
   * Expected answers: code 200 : (no error) code 404 : ErrorResponse (plugin is not installed) code
   * 500 : ErrorResponse (server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   * @param force
   *   Force disable a plugin even if still in use.
   */
  def pluginDisable(name: String, force: Option[Boolean] = None)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/plugins/${name}/disable?force=${force}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 200 : (no error) code 404 : ErrorResponse (plugin is not installed) code
   * 500 : ErrorResponse (server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   * @param timeout
   *   Set the HTTP client timeout (in seconds)
   */
  def pluginEnable(name: String, timeout: Option[Int] = None)
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/plugins/${name}/enable?timeout=${timeout}")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 200 : Plugin (no error) code 404 : ErrorResponse (plugin is not
   * installed) code 500 : ErrorResponse (server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   */
  def pluginInspect(name: String): Request[Either[ResponseException[String, Exception], Plugin]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/plugins/${name}/json")
      .contentType("application/json")
      .response(asJson[Plugin])

  /**
   * Returns information about installed plugins.
   *
   * Expected answers: code 200 : Seq[Plugin] (No error) code 500 : ErrorResponse (Server error)
   *
   * @param filters
   *   A JSON encoded value of the filters (a `map[string][]string`) to process on the plugin list.
   *   Available filters: - `capability=<capability name>` - `enable=<true>|<false>`
   */
  def pluginList(filters: Option[String] = None)
      : Request[Either[ResponseException[String, Exception], Seq[Plugin]]] =
    basicRequest
      .method(Method.GET, uri"$baseUrl/plugins?filters=${filters}")
      .contentType("application/json")
      .response(asJson[Seq[Plugin]])

  /**
   * Pulls and installs a plugin. After the plugin is installed, it can be enabled using the [`POST
   * /plugins/{name}/enable` endpoint](#operation/PostPluginsEnable).
   *
   * Expected answers: code 204 : (no error) code 500 : ErrorResponse (server error)
   *
   * @param remote
   *   Remote reference for plugin to install. The `:latest` tag is optional, and is used as the
   *   default if omitted.
   * @param name
   *   Local name for the pulled plugin. The `:latest` tag is optional, and is used as the default
   *   if omitted.
   * @param xRegistryAuth
   *   A base64url-encoded auth configuration to use when pulling a plugin from a registry. Refer to
   *   the [authentication section](#section/Authentication) for details.
   * @param body
   */
  def pluginPull(
      remote: String,
      name: Option[String] = None,
      xRegistryAuth: Option[String] = None,
      body: Seq[PluginPrivilege],
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/plugins/pull?remote=${remote}&name=${name}")
      .contentType("application/json")
      .header("X-Registry-Auth", xRegistryAuth.toString)
      .body(body)
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Push a plugin to the registry.
   *
   * Expected answers: code 200 : (no error) code 404 : ErrorResponse (plugin not installed) code
   * 500 : ErrorResponse (server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   */
  def pluginPush(name: String): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/plugins/${name}/push")
      .contentType("application/json")
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 204 : (No error) code 404 : ErrorResponse (Plugin not installed) code
   * 500 : ErrorResponse (Server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   * @param body
   */
  def pluginSet(name: String, body: Seq[String])
      : Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/plugins/${name}/set")
      .contentType("application/json")
      .body(body)
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

  /**
   * Expected answers: code 204 : (no error) code 404 : ErrorResponse (plugin not installed) code
   * 500 : ErrorResponse (server error)
   *
   * @param name
   *   The name of the plugin. The `:latest` tag is optional, and is the default if omitted.
   * @param remote
   *   Remote reference to upgrade to. The `:latest` tag is optional, and is used as the default if
   *   omitted.
   * @param xRegistryAuth
   *   A base64url-encoded auth configuration to use when pulling a plugin from a registry. Refer to
   *   the [authentication section](#section/Authentication) for details.
   * @param body
   */
  def pluginUpgrade(
      name: String,
      remote: String,
      xRegistryAuth: Option[String] = None,
      body: Seq[PluginPrivilege],
    ): Request[Either[ResponseException[String, Exception], Unit]] =
    basicRequest
      .method(Method.POST, uri"$baseUrl/plugins/${name}/upgrade?remote=${remote}")
      .contentType("application/json")
      .header("X-Registry-Auth", xRegistryAuth.toString)
      .body(body)
      .response(asString.mapWithMetadata(ResponseAs.deserializeRightWithError(_ => Right(()))))

end PluginApi
