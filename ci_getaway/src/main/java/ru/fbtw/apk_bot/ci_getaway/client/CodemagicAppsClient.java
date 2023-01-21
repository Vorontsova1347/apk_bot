package ru.fbtw.apk_bot.ci_getaway.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.ApplicationsDto;
import ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api.BuildListDto;

public interface CodemagicAppsClient {
	@GET("/apps")
	Call<ApplicationsDto> getApps();
}
