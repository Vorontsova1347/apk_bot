package ru.fbtw.apk_bot.apk_telegram_bot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ApkTelegramBotService extends TelegramWebhookBot {

	private final String botUsername;
	private final String botToken;
	private final String botPath;

	public ApkTelegramBotService(
			@Value("telegrambot.botUsername")
			String botUsername,
			@Value("telegrambot.botToken")
			String botToken,
			@Value("telegrambot.webPath")
			String botPath
	) {
		this.botUsername = botUsername;
		this.botToken = botToken;
		this.botPath = botPath;
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
		if (update.hasMessage() && update.getMessage().hasText()) {
			SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
			message.setChatId(update.getMessage().getChatId().toString());

			String text = update.getMessage().getText();
			String response = String.format("Hello, Stranger! You write: %s",text);
			message.setText(response);

			return message;
		}

		SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
		message.setText("Hello world");

		return message;
	}

	@Override
	public String getBotPath() {
		return botPath;
	}
}
