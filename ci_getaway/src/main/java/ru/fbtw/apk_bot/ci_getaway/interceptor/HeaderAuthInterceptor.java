package ru.fbtw.apk_bot.ci_getaway.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HeaderAuthInterceptor implements Interceptor {
	private final String apiToken;

	public HeaderAuthInterceptor(String apiToken) {
		this.apiToken = apiToken;
	}

	@NotNull
	@Override
	public Response intercept(@NotNull Chain chain) throws IOException {
		Request originalRequest = chain.request();

		Request newRequest = originalRequest
				.newBuilder()
				.header("x-auth-token", apiToken)
				.build();

		return chain.proceed(newRequest);
	}
}
