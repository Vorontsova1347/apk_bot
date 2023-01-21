package ru.fbtw.apk_bot.ci_getaway.exception;

public class WebHookException extends Exception{
	private final ErrorType type;

	public WebHookException(ErrorType type) {
		this.type = type;
	}

	public WebHookException(String message, ErrorType type) {
		super(message);
		this.type = type;
	}

	public WebHookException(String message, Throwable cause, ErrorType type) {
		super(message, cause);
		this.type = type;
	}

	public WebHookException(Throwable cause, ErrorType type) {
		super(cause);
		this.type = type;
	}

	public WebHookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorType type) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.type = type;
	}

	public ErrorType getType() {
		return type;
	}
}
