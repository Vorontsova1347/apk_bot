package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ArtifactDto {
	private String name;
	private String size;
	private String url;
	private String version;
	private String versionName;
	private String type;
	private String version_code;
}
