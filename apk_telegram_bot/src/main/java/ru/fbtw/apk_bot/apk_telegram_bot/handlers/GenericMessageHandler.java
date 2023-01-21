package ru.fbtw.apk_bot.apk_telegram_bot.handlers;


import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface GenericMessageHandler {
	BotApiMethod<?> handle(Update update, BotApiMethod<?> previousHandle);


	default int priority(){
		return 1;
	}
}
