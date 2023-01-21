package ru.fbtw.apk_bot.apk_telegram_bot.exception;

import retrofit2.Response;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.BuildStatusDto;

import java.util.List;

public class BuildsException  extends Exception{
	public final Response<List<BuildStatusDto>> listBuilds;

	public BuildsException(Response<List<BuildStatusDto>> listBuilds) {
		this.listBuilds = listBuilds;
	}

	public BuildsException(String message, Response<List<BuildStatusDto>> listBuilds) {
		super(message);
		this.listBuilds = listBuilds;
	}

	public BuildsException(String message, Throwable cause, Response<List<BuildStatusDto>> listBuilds) {
		super(message, cause);
		this.listBuilds = listBuilds;
	}

	public BuildsException(Throwable cause, Response<List<BuildStatusDto>> listBuilds) {
		super(cause);
		this.listBuilds = listBuilds;
	}

	public BuildsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Response<List<BuildStatusDto>> listBuilds) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.listBuilds = listBuilds;
	}
}
