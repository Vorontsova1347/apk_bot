package ru.fbtw.apk_bot.ci_getaway.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fbtw.apk_bot.ci_getaway.dto.BuildStartedDto;
import ru.fbtw.apk_bot.ci_getaway.dto.BuildStatusDto;
import ru.fbtw.apk_bot.ci_getaway.exception.WebHookException;
import ru.fbtw.apk_bot.ci_getaway.service.BuildService;

import java.util.List;


@RestController
@AllArgsConstructor
public class BuildController {
	private final BuildService buildService;

	@PostMapping("/build/{app}")
	BuildStartedDto postBuild(@PathVariable(name = "app") String appName) throws WebHookException {
		return buildService.startBuild(appName);
	}

	@GetMapping("/build/{buildId}")
	BuildStatusDto getBuild(@PathVariable(name = "buildId") String buildId) throws WebHookException {
		return buildService.getBuild(buildId);
	}


	@GetMapping("/builds")
	List<BuildStatusDto> getBuilds() throws WebHookException {
		return buildService.getBuilds();
	}


	@PostMapping("/build/{buildId}/cancel")
	BuildStartedDto cancelBuild(@PathVariable(name = "buildId") String buildId) {
		return null;
	}

}
