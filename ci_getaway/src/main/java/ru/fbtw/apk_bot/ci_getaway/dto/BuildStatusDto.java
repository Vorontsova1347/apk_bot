package ru.fbtw.apk_bot.ci_getaway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BuildStatusDto {

	private BuildStatus status;
	private String buildId;
	private String appName;
	private String startedAt;

	private String resultUrl;
}
