package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BuildCreateDto {
	public  String buildId;
}
