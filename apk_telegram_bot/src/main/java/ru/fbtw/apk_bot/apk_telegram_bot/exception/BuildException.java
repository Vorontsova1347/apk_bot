package ru.fbtw.apk_bot.apk_telegram_bot.exception;

import retrofit2.Response;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.BuildStartedDto;

public class BuildException extends Exception{
	public final Response<BuildStartedDto>  response;

	public BuildException(Response<BuildStartedDto> response) {
		this.response = response;
	}

	public BuildException(String message, Response<BuildStartedDto> response) {
		super(message);
		this.response = response;
	}

	public BuildException(String message, Throwable cause, Response<BuildStartedDto> response) {
		super(message, cause);
		this.response = response;
	}

	public BuildException(Throwable cause, Response<BuildStartedDto> response) {
		super(cause);
		this.response = response;
	}

	public BuildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Response<BuildStartedDto> response) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.response = response;
	}
}
