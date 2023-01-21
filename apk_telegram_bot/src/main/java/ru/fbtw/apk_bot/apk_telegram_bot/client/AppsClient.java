package ru.fbtw.apk_bot.apk_telegram_bot.client;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.AppNamesDto;

public interface AppsClient {
	@GET("/apps")
	Call<AppNamesDto> getApps();
}
