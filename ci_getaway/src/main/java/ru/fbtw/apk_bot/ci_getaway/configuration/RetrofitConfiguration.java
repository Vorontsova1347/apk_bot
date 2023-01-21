package ru.fbtw.apk_bot.ci_getaway.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.fbtw.apk_bot.ci_getaway.interceptor.HeaderAuthInterceptor;

@Configuration
public class RetrofitConfiguration {
	@Bean
	protected Gson configureGson() {
		return new GsonBuilder()
				.setLenient()
				.create();
	}

	@Bean
	protected OkHttpClient configureOkhttp(@Value("${CODEMAGIC_API_KEY}") String apiKey) {
		return new OkHttpClient().newBuilder()
				.addInterceptor(new HeaderAuthInterceptor(apiKey))
				.build();
	}

	@Bean
	protected Retrofit configureRetrofit(
			@Value("${CODEMAGIC_URL}") String baseUrl,
			Gson gson,
			OkHttpClient client
	) {
		return new Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
	}


}
