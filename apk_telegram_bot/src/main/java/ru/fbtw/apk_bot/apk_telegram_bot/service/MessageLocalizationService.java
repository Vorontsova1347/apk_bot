package ru.fbtw.apk_bot.apk_telegram_bot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageLocalizationService {

	private final MessageSource messageSource;
	private final Locale locale;

	public MessageLocalizationService(@Value("${LOCALE_TAG}") String localeTag, MessageSource messageSource) {
		this.messageSource = messageSource;
		this.locale = Locale.forLanguageTag(localeTag);
	}

	public String getMessage(String tag) {
		return getMessage(tag, null);
	}

	public String getMessage(String tag, Object[] args) {
		return getMessage(tag, args, locale);
	}


	public String getMessage(String tag, Object[] args, Locale locale) {
		return messageSource.getMessage(tag, args, locale);
	}

}
