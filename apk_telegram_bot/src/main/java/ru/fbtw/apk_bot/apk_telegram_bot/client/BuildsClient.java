package ru.fbtw.apk_bot.apk_telegram_bot.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.AppNamesDto;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.BuildStartedDto;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.BuildStatusDto;

import java.util.List;

public interface BuildsClient {
	@POST("/build/{app}")
	Call<BuildStartedDto> postBuild(@Path("app") String app);

	@GET("/build/{buildId}")
	Call<BuildStatusDto> getBuild(@Path("buildId") String buildId);

	@GET("/builds")
	Call<List<BuildStatusDto>> getBuilds();
}
