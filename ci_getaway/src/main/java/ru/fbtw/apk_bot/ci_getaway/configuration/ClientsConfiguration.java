package ru.fbtw.apk_bot.ci_getaway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import ru.fbtw.apk_bot.ci_getaway.client.CodemagicAppsClient;
import ru.fbtw.apk_bot.ci_getaway.client.CodemagicBuildsClient;

@Configuration
public class ClientsConfiguration {

	@Bean
	protected CodemagicAppsClient configureCodemagicAppsClient(Retrofit retrofit) {
		return retrofit.create(CodemagicAppsClient.class);
	}

	@Bean
	protected CodemagicBuildsClient configureCodemagicBuildsClient(Retrofit retrofit) {
		return retrofit.create(CodemagicBuildsClient.class);
	}
}
