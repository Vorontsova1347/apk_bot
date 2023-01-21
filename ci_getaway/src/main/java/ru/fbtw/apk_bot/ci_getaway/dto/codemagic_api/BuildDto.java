package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuildDto {
	@SerializedName("_id")
	private String id;
	private String appId;
	private String workflowId;
	private String branch;
	private String tag;
	private CodemagicBuildStatus status;
	private String startedAt;
	private String finishedAt;
	private String version;
	private List<ArtifactDto> artefacts;

}
