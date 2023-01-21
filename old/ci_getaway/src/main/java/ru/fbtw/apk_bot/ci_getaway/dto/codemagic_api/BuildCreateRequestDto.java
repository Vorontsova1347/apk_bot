package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuildCreateRequestDto {
	public String appId;
	public String workflowId;
	public String branch;
	public String tag;
	public List<String> labels;
}
