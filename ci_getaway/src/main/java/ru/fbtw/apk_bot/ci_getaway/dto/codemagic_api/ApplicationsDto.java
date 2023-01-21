package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class ApplicationsDto {
	private List<ApplicationDto> applications;
}
