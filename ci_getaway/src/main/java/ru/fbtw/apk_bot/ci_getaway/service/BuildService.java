package ru.fbtw.apk_bot.ci_getaway.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import ru.fbtw.apk_bot.ci_getaway.client.CodemagicBuildsClient;
import ru.fbtw.apk_bot.ci_getaway.domain.CodemagicApp;
import ru.fbtw.apk_bot.ci_getaway.dto.BuildStartedDto;
import ru.fbtw.apk_bot.ci_getaway.dto.BuildStatus;
import ru.fbtw.apk_bot.ci_getaway.dto.BuildStatusDto;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.*;
import ru.fbtw.apk_bot.ci_getaway.exception.ErrorType;
import ru.fbtw.apk_bot.ci_getaway.exception.WebHookException;
import ru.fbtw.apk_bot.ci_getaway.repository.CodemagicAppRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildService {

	private final AppService appService;
	private final CodemagicAppRepository appRepository;
	private final CodemagicBuildsClient buildsClient;

	private final String branch;

	public BuildService(
			AppService appService,
			CodemagicAppRepository appRepository,
			CodemagicBuildsClient buildsClient,
			@Value("${DEFAULT_BRANCH}") String branch
	) {
		this.appService = appService;
		this.appRepository = appRepository;
		this.buildsClient = buildsClient;
		this.branch = branch;
	}


	public BuildStartedDto startBuild(String appName) throws WebHookException {
		Optional<CodemagicApp> optionalApp = appRepository.findByAppName(appName);

		if (optionalApp.isEmpty()) {
			appService.getApps();
		}

		optionalApp = appRepository.findByAppName(appName);

		if (optionalApp.isEmpty()) {
			throw new WebHookException(ErrorType.MISSING_APPLICATION);
		}

		CodemagicApp codemagicApp = optionalApp.get();

		BuildCreateRequestDto requestDto = BuildCreateRequestDto.builder()
				.appId(codemagicApp.getAppId())
				.workflowId(codemagicApp.getWorkflowId())
				.branch(branch)
				.build();
		try {
			Response<BuildCreateDto> dtoResponse = buildsClient.createBuild(requestDto).execute();
			if (!dtoResponse.isSuccessful()) {
				throw new WebHookException(ErrorType.BUILD_FAILED);
			}

			BuildCreateDto body = dtoResponse.body();
			if (body == null) {
				throw new WebHookException(ErrorType.BUILD_FAILED);
			}

			return new BuildStartedDto(body.buildId);

		} catch (Exception e) {
			throw new WebHookException(ErrorType.BUILD_FAILED);
		}
	}

	public BuildStatusDto getBuild(String buildId) throws WebHookException {
		try {
			Response<BuildInfoDto> dtoResponse = buildsClient.getBuildStatus(buildId).execute();
			if (!dtoResponse.isSuccessful()) {
				throw new WebHookException(ErrorType.BUILD_FAILED);
			}

			BuildInfoDto body = dtoResponse.body();

			if (body == null) {
				throw new WebHookException(ErrorType.BUILD_FAILED);
			}

			return getBuildStatusDto(body.getApplication(), body.getBuild());

		} catch (Exception e) {
			throw new WebHookException(ErrorType.BUILD_FAILED);
		}
	}


	public List<BuildStatusDto> getBuilds() throws WebHookException {
		try {
			Response<BuildListDto> dtoResponse = buildsClient.getBuildList(null, null, null, null).execute();
			if (!dtoResponse.isSuccessful()) {
				throw new WebHookException(ErrorType.BUILD_FAILED);
			}

			BuildListDto body = dtoResponse.body();

			if (body == null) {
				throw new WebHookException(ErrorType.BUILD_FAILED);
			}

			Map<String, ApplicationDto> collect = body.getApplications()
					.stream()
					.collect(Collectors.toMap(ApplicationDto::getId, app -> app));

			return body.getBuilds()
					.stream()
					.map(b -> getBuildStatusDto(collect.get(b.getAppId()), b))
					.collect(Collectors.toList());

		} catch (Exception e) {
			throw new WebHookException(ErrorType.BUILD_FAILED);
		}
	}

	private BuildStatusDto getBuildStatusDto(ApplicationDto application, BuildDto build) {


		Optional<String> apk = build
				.getArtefacts()
				.stream()
				.filter(a -> a.getType().equals("apk"))
				.map(ArtifactDto::getUrl)
				.findFirst();

		BuildStatus status = resolveStatus(build.getStatus());

		return BuildStatusDto.builder()
				.startedAt(build.getStartedAt())
				.appName(application.getAppName())
				.buildId(build.getId())
				.status(status)
				.resultUrl(apk.orElse(null))
				.build();
	}

	@NotNull
	private BuildStatus resolveStatus(CodemagicBuildStatus codemagicBuildStatus) {
		BuildStatus status = BuildStatus.inProgress;
		Set<CodemagicBuildStatus> failed = new HashSet<>();
		failed.add(CodemagicBuildStatus.canceled);
		failed.add(CodemagicBuildStatus.failed);
		failed.add(CodemagicBuildStatus.skipped);
		failed.add(CodemagicBuildStatus.timeout);

		if (failed.contains(codemagicBuildStatus)) {
			status = BuildStatus.fail;
		}

		if (codemagicBuildStatus == CodemagicBuildStatus.finished) {
			status = BuildStatus.success;
		}
		return status;
	}
}
