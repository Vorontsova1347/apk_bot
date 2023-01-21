package ru.fbtw.apk_bot.apk_telegram_bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import ru.fbtw.apk_bot.apk_telegram_bot.client.AppsClient;
import ru.fbtw.apk_bot.apk_telegram_bot.client.BuildsClient;

@Configuration
public class ClientsConfiguration {

	@Bean
	protected AppsClient configureCodemagicAppsClient(Retrofit retrofit) {
		return retrofit.create(AppsClient.class);
	}

	@Bean
	protected BuildsClient configureCodemagicBuildsClient(Retrofit retrofit) {
		return retrofit.create(BuildsClient.class);
	}
}
