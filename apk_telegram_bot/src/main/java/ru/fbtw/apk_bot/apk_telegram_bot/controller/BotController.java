package ru.fbtw.apk_bot.apk_telegram_bot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.fbtw.apk_bot.apk_telegram_bot.service.ApkTelegramBotService;

@AllArgsConstructor
@RestController
public class BotController {
	private final ApkTelegramBotService apkTelegramBotService;


	@RequestMapping(value = "/",method = RequestMethod.POST)
	public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
		return apkTelegramBotService.onWebhookUpdateReceived(update);
	}
}
