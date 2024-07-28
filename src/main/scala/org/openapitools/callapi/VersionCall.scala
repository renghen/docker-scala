package org.openapitools.callapi

import sttp.client4.okhttp.OkHttpSyncBackend
import okhttp3.*
import okhttp3.unixdomainsockets.UnixDomainSocketFactory
import org.openapitools.client.api.SystemApi
import org.openapitools.client.model.SystemVersion
import org.openapitools.client.core.JsonSupport.given

import com.github.plokhotnyuk.jsoniter_scala.macros.*
import com.github.plokhotnyuk.jsoniter_scala.core.*
import java.io.File

object VersionCall extends App:

  val unixSocketFactory = UnixDomainSocketFactory(File("/var/run/docker.sock"))
  val builder           = OkHttpClient.Builder()
  val okHttpClient      = new OkHttpClient.Builder().socketFactory(unixSocketFactory).build()
  val backend           = OkHttpSyncBackend.usingClient(okHttpClient)

  val versionCall = SystemApi().systemVersion()
  val result      = versionCall.send(backend)
  println(result.body)

end VersionCall
