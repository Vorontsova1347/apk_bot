package ru.fbtw.apk_bot.apk_telegram_bot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.fbtw.apk_bot.apk_telegram_bot.handlers.GenericMessageHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class ApkTelegramBotServiceTest {

	@Test
	void onWebhookUpdateReceived() {

		List<GenericMessageHandler> handlers = new ArrayList<>();
		handlers.add((u, p)-> new BotApiMethod<Serializable>() {
			@Override
			public String getMethod() {
				return "first";
			}

			@Override
			public Serializable deserializeResponse(String s) throws TelegramApiRequestException {
				return null;
			}
		});
		handlers.add((u,p)-> new BotApiMethod<Serializable>() {
			@Override
			public String getMethod() {
				return "second";
			}

			@Override
			public Serializable deserializeResponse(String s) throws TelegramApiRequestException {
				return null;
			}
		});

		ApkTelegramBotService service = new ApkTelegramBotService("", "", "", handlers);

		Assertions.assertEquals("second", service.onWebhookUpdateReceived(null).getMethod());
	}

	@Test
	void onWebhookUpdateReceivedSingle() {

		List<GenericMessageHandler> handlers = new ArrayList<>();

		handlers.add((u, p)-> new BotApiMethod<Serializable>() {
			@Override
			public String getMethod() {
				return "single";
			}

			@Override
			public Serializable deserializeResponse(String s) throws TelegramApiRequestException {
				return null;
			}
		});

		ApkTelegramBotService service = new ApkTelegramBotService("", "", "", handlers);

		Assertions.assertEquals("single", service.onWebhookUpdateReceived(null).getMethod());
	}

	@Test
	void onWebhookUpdateReceivedEmpty() {

		List<GenericMessageHandler> handlers = new ArrayList<>();

		ApkTelegramBotService service = new ApkTelegramBotService("", "", "", handlers);

		Assertions.assertNull(service.onWebhookUpdateReceived(null));
	}
}