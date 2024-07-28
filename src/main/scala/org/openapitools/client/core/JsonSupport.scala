package org.openapitools.client.core

import sttp.client4.jsoniter.SttpJsoniterJsonApi
import com.github.plokhotnyuk.jsoniter_scala.core.*
import com.github.plokhotnyuk.jsoniter_scala.macros.*

import org.openapitools.client.model.*
// import java.io.File

object JsonSupport extends SttpJsoniterJsonApi:
  given taskCodec: JsonValueCodec[Task] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given tasksCodec: JsonValueCodec[Seq[Task]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }
  // given fileCodec: JsonValueCodec[File]             = JsonCodecMaker.make{
  //   CodecMakerConfig.withFieldNameMapper(
  //     JsonCodecMaker.EnforcePascalCase
  //   )
  // }

  given seqStringCodec: JsonValueCodec[Seq[String]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given volumeCreateOptionsCodec: JsonValueCodec[VolumeCreateOptions] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given volumeCodec: JsonValueCodec[Volume] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given volumeListResponseCodec: JsonValueCodec[VolumeListResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given volumePruneResponseCodec: JsonValueCodec[VolumePruneResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given volumeUpdateRequestCodec: JsonValueCodec[VolumeUpdateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given authConfigCodec: JsonValueCodec[AuthConfig] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given systemAuthResponseCodec: JsonValueCodec[SystemAuthResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given systemDataUsageResponseCodec: JsonValueCodec[SystemDataUsageResponse] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given eventMessageCodec: JsonValueCodec[EventMessage] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given systemInfoCodec: JsonValueCodec[SystemInfo] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given stringCodec: JsonValueCodec[String] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given systemVersionCodec: JsonValueCodec[SystemVersion] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given configCodec: JsonValueCodec[Config] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given configSeqCodec: JsonValueCodec[Seq[Config]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given configCreateRequestCodec: JsonValueCodec[ConfigCreateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given configSpecCodec: JsonValueCodec[ConfigSpec] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given idResponseCodec: JsonValueCodec[IdResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqFilesystemChangeCodec: JsonValueCodec[Seq[FilesystemChange]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerCreateRequestCodec: JsonValueCodec[ContainerCreateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerCreateResponseCodec: JsonValueCodec[ContainerCreateResponse] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given containerSummaryCodec: JsonValueCodec[ContainerSummary] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqContainerSummaryCodec: JsonValueCodec[Seq[ContainerSummary]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerPruneResponseCodec: JsonValueCodec[ContainerPruneResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerTopResponseCodec: JsonValueCodec[ContainerTopResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerUpdateResponseCodec: JsonValueCodec[ContainerUpdateResponse] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given containerUpdateRequestCodec: JsonValueCodec[ContainerUpdateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerWaitResponseCodec: JsonValueCodec[ContainerWaitResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerInspectResponseCodec: JsonValueCodec[ContainerInspectResponse] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given distributionInspectCodec: JsonValueCodec[DistributionInspect] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given execConfigCodec: JsonValueCodec[ExecConfig] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given execInspectResponseCodec: JsonValueCodec[ExecInspectResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given execStartConfigCodec: JsonValueCodec[ExecStartConfig] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given buildPruneResponseCodec: JsonValueCodec[BuildPruneResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given containerConfigCodec: JsonValueCodec[ContainerConfig] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given imageDeleteResponseItemCodec: JsonValueCodec[ImageDeleteResponseItem] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given seqImageDeleteResponseItemCodec: JsonValueCodec[Seq[ImageDeleteResponseItem]] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given historyResponseItemCodec: JsonValueCodec[HistoryResponseItem] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqHistoryResponseItemCodec: JsonValueCodec[Seq[HistoryResponseItem]] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given imageInspectCodec: JsonValueCodec[ImageInspect] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given imageSummaryCodec: JsonValueCodec[ImageSummary] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqImageSummaryCodec: JsonValueCodec[Seq[ImageSummary]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given imagePruneResponseCodec: JsonValueCodec[ImagePruneResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given imageSearchResponseItemCodec: JsonValueCodec[ImageSearchResponseItem] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given seqImageSearchResponseItemCodec: JsonValueCodec[Seq[ImageSearchResponseItem]] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given networkConnectRequestCodec: JsonValueCodec[NetworkConnectRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given networkCreateRequestCodec: JsonValueCodec[NetworkCreateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given networkCreateResponseCodec: JsonValueCodec[NetworkCreateResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given networkDisconnectRequestCodec: JsonValueCodec[NetworkDisconnectRequest] =
    JsonCodecMaker.make {
      CodecMakerConfig.withFieldNameMapper(
        JsonCodecMaker.EnforcePascalCase,
      )
    }

  given networkCodec: JsonValueCodec[Network] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqNetworkCodec: JsonValueCodec[Seq[Network]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given networkPruneResponseCodec: JsonValueCodec[NetworkPruneResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given nodeCodec: JsonValueCodec[Node] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqNodeCodec: JsonValueCodec[Seq[Node]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given nodeSpecCodec: JsonValueCodec[NodeSpec] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqPluginPrivilegeCodec: JsonValueCodec[Seq[PluginPrivilege]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given pluginCodec: JsonValueCodec[Plugin] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqPluginCodec: JsonValueCodec[Seq[Plugin]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given secretCreateRequestCodec: JsonValueCodec[SecretCreateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given secretCodec: JsonValueCodec[Secret] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqSecretCodec: JsonValueCodec[Seq[Secret]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given secretSpecCodec: JsonValueCodec[SecretSpec] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given serviceCreateRequestCodec: JsonValueCodec[ServiceCreateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given serviceCreateResponseCodec: JsonValueCodec[ServiceCreateResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given serviceCodec: JsonValueCodec[Service] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given seqServiceCodec: JsonValueCodec[Seq[Service]] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given serviceUpdateResponseCodec: JsonValueCodec[ServiceUpdateResponse] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

  given serviceUpdateRequestCodec: JsonValueCodec[ServiceUpdateRequest] = JsonCodecMaker.make {
    CodecMakerConfig.withFieldNameMapper(
      JsonCodecMaker.EnforcePascalCase,
    )
  }

end JsonSupport
