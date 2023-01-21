package ru.fbtw.apk_bot.apk_telegram_bot.exception;

import retrofit2.Response;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.AppNamesDto;

public class AppNamesException extends Exception {
	public final Response<AppNamesDto> namesDtoResponse;

	public AppNamesException(Response<AppNamesDto> namesDtoResponse) {
		this.namesDtoResponse = namesDtoResponse;
	}

	public AppNamesException(String message, Response<AppNamesDto> namesDtoResponse) {
		super(message);
		this.namesDtoResponse = namesDtoResponse;
	}

	public AppNamesException(String message, Throwable cause, Response<AppNamesDto> namesDtoResponse) {
		super(message, cause);
		this.namesDtoResponse = namesDtoResponse;
	}

	public AppNamesException(Throwable cause, Response<AppNamesDto> namesDtoResponse) {
		super(cause);
		this.namesDtoResponse = namesDtoResponse;
	}

	public AppNamesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Response<AppNamesDto> namesDtoResponse) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.namesDtoResponse = namesDtoResponse;
	}
}
