package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BuildListDto {
	private List<ApplicationDto> applications;
	private List<BuildDto> builds;
}
