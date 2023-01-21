package ru.fbtw.apk_bot.apk_telegram_bot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.fbtw.apk_bot.apk_telegram_bot.handlers.GenericMessageHandler;

import java.util.Comparator;
import java.util.List;


@Service

public class ApkTelegramBotService extends TelegramWebhookBot {

	private final String botUsername;
	private final String botToken;
	private final String botPath;
	private final List<GenericMessageHandler> handlers;


	public ApkTelegramBotService(
			@Value("${TELEGRAM_BOT_USERNAME}")
			String botUsername,
			@Value("${TELEGRAM_BOT_TOKEN}")
			String botToken,
			@Value("${TELEGRAM_BOT_PATH}")
			String botPath,
			List<GenericMessageHandler> handlers
	) {
		this.botUsername = botUsername;
		this.botToken = botToken;
		this.botPath = botPath;
		this.handlers = handlers;
		handlers.sort(Comparator.comparing(GenericMessageHandler::priority).reversed());
	}

	@Override
	public String getBotUsername() {
		return botUsername;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	@Override
	public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
		return handlers.stream()
				.reduce(
						null,
						(prev, handler) -> handler.handle(update, prev),
						(prev, cur) -> cur
				);
	}

	@Override
	public String getBotPath() {
		return botPath;
	}
}
