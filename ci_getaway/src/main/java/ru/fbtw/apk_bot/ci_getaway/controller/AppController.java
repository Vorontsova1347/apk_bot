package ru.fbtw.apk_bot.ci_getaway.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fbtw.apk_bot.ci_getaway.dto.AppNamesDto;
import ru.fbtw.apk_bot.ci_getaway.dto.BuildStartedDto;
import ru.fbtw.apk_bot.ci_getaway.exception.WebHookException;
import ru.fbtw.apk_bot.ci_getaway.service.AppService;

@RestController
@AllArgsConstructor
public class AppController {

	private	final AppService appService;

	@GetMapping("/apps")
	AppNamesDto getApps() throws WebHookException {
		return appService.getApps();
	}
}
