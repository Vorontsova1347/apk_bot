package ru.fbtw.apk_bot.apk_telegram_bot.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {
	@Bean
	protected Gson configureGson() {
		return new GsonBuilder()
				.setLenient()
				.create();
	}

	@Bean
	protected Retrofit configureRetrofit(
			@Value("${CI_GATEWAY_URL}") String baseUrl,
			Gson gson
	) {
		return new Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
	}


}
