package ru.fbtw.apk_bot.ci_getaway.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import ru.fbtw.apk_bot.ci_getaway.client.CodemagicAppsClient;
import ru.fbtw.apk_bot.ci_getaway.domain.CodemagicApp;
import ru.fbtw.apk_bot.ci_getaway.dto.AppNamesDto;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.ApplicationDto;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.ApplicationsDto;
import ru.fbtw.apk_bot.ci_getaway.exception.ErrorType;
import ru.fbtw.apk_bot.ci_getaway.exception.WebHookException;
import ru.fbtw.apk_bot.ci_getaway.repository.CodemagicAppRepository;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AppService {

	private final CodemagicAppsClient codemagicAppsClient;
	private final CodemagicAppRepository repository;

	private static CodemagicApp mapCodemagicApp(ApplicationDto app) {
		return new CodemagicApp(
				app.getId(),
				app.getAppName(),
				app.getWorkflowIds()
						.stream()
						.findAny()
						.orElse(null)
		);
	}

	public AppNamesDto getApps() throws WebHookException {
		try {
			Response<ApplicationsDto> response = codemagicAppsClient.getApps()
					.execute();

			if (!response.isSuccessful()) {
				log.error("error, get apps");
				throw new WebHookException(ErrorType.APPLICATIONS);
			}

			ApplicationsDto body = response.body();
			if (body == null) {
				log.error("error, get apps");
				throw new WebHookException(ErrorType.APPLICATIONS);
			}

			Iterable<CodemagicApp> codemagicAppStream = body.getApplications()
					.stream()
					.map(AppService::mapCodemagicApp)
					.toList();

			repository.saveAll(codemagicAppStream);

			return new AppNamesDto(
					body
							.getApplications()
							.stream()
							.map((ApplicationDto::getAppName))
							.collect(Collectors.toList())
			);

		} catch (Exception e) {
			log.error("error, get apps", e);
			throw new WebHookException(e, ErrorType.APPLICATIONS);
		}
	}
}
