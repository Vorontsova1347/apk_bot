package ru.fbtw.apk_bot.apk_telegram_bot.exception;

import retrofit2.Response;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.BuildStatusDto;

public class DownloadException extends Exception {
	public final Response<BuildStatusDto> buildStatusDto;

	public DownloadException(Response<BuildStatusDto> buildStatusDto) {
		this.buildStatusDto = buildStatusDto;
	}

	public DownloadException(String message, Response<BuildStatusDto> buildStatusDto) {
		super(message);
		this.buildStatusDto = buildStatusDto;
	}

	public DownloadException(String message, Throwable cause, Response<BuildStatusDto> buildStatusDto) {
		super(message, cause);
		this.buildStatusDto = buildStatusDto;
	}

	public DownloadException(Throwable cause, Response<BuildStatusDto> buildStatusDto) {
		super(cause);
		this.buildStatusDto = buildStatusDto;
	}

	public DownloadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Response<BuildStatusDto> buildStatusDto) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.buildStatusDto = buildStatusDto;
	}
}
