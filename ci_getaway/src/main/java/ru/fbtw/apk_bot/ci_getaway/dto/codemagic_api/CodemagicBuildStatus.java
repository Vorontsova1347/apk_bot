package ru.fbtw.apk_bot.ci_getaway.dto.codemagic_api;

public enum CodemagicBuildStatus {
	building,
	canceled,
	finishing,
	finished,
	failed,
	fetching,
	preparing,
	publishing,
	queued,
	skipped,
	testing,
	timeout,
	warning
}
