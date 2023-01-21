package ru.fbtw.apk_bot.ci_getaway.client;

import retrofit2.Call;
import retrofit2.http.*;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.BuildCreateDto;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.BuildCreateRequestDto;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.BuildInfoDto;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.BuildListDto;

public interface CodemagicBuildsClient {
	@POST("/builds")
	Call<BuildCreateDto> createBuild(@Body BuildCreateRequestDto requestDto);

	@GET("/builds")
	Call<BuildListDto> getBuildList(
			@Query("appId") String appId,
			@Query("workflowId") String workflowId,
			@Query("branch") String branch,
			@Query("tag") String tag
	);

	@GET("/builds/{id}")
	Call<BuildInfoDto> getBuildStatus(@Path("id") String id);

	@POST("/builds/{id}/cancel")
	Call<String> cancelBuild(@Path("id") String id);
}
