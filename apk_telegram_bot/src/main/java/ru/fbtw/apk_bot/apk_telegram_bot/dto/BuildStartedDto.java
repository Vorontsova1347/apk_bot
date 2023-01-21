package ru.fbtw.apk_bot.apk_telegram_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BuildStartedDto {
	private String buildId;
}
