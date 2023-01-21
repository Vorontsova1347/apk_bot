package ru.fbtw.apk_bot.ci_getaway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AppNamesDto {
	private List<String> names;
}
