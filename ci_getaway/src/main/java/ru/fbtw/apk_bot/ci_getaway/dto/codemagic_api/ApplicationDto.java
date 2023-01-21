package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class ApplicationDto {
	@SerializedName("_id")
	private String id;
	private String appName;
	private List<String> workflowIds;
}
