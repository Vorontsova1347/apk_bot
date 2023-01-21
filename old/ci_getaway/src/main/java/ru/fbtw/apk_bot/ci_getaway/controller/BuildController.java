package ru.fbtw.apk_bot.ci_getaway.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fbtw.apk_bot.ci_getaway.dto.BuildStartedDto;
import ru.fbtw.apk_bot.ci_getaway.service.BuildService;


@RestController
@AllArgsConstructor
public class BuildController {
	private final BuildService buildService;

	@PostMapping("/build/{app}")
	BuildStartedDto postBuild(@PathVariable(name = "app") String appName){
		return buildService.startBuild(appName);
	}

}
