package ru.fbtw.apk_bot.apk_telegram_bot.handlers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.io.IOException;

@Service
public class MessageExceptionHandler {

	public BotApiMethod<?> handle(Exception e) {
		return null;
	}
}
