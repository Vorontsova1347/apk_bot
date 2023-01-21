package ru.fbtw.apk_bot.apk_telegram_bot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.fbtw.apk_bot.apk_telegram_bot.service.MessageLocalizationService;

@Service
@AllArgsConstructor
public class DefaultMessageHandler implements GenericMessageHandler {

	private final MessageLocalizationService messageLocalizationService;

	@Override
	public BotApiMethod<?> handle(Update update, BotApiMethod<?> previousHandle) {
		BotApiMethod<?> reply = previousHandle;

		if (reply == null && update.hasMessage()) {
			Message message = update.getMessage();
			String text = message.getText();

			if (text.startsWith("/start")) {
				String startText = messageLocalizationService.getMessage("common.start");

				SendMessage startMessage = new SendMessage();
				startMessage.setText(startText);
				startMessage.setChatId(message.getChatId());
				reply = startMessage;
			}


			if (reply == null && text.startsWith("/help")) {
				String helpText = messageLocalizationService.getMessage("common.help");

				SendMessage helpMessage = new SendMessage();
				helpMessage.setText(helpText);
				helpMessage.setChatId(message.getChatId());
				reply = helpMessage;
			}

			if (reply == null) {
				String replyText = messageLocalizationService.getMessage("error.unknown");

				SendMessage errorMessage = new SendMessage();
				errorMessage.setText(replyText);
				errorMessage.setChatId(message.getChatId());
				reply = errorMessage;
			}
		}


		if (reply == null) {
			String replyText = messageLocalizationService.getMessage("error.unknown");

			SendMessage errorMessage = new SendMessage();
			errorMessage.setText(replyText);
			reply = errorMessage;
		}

		return reply;
	}


	@Override
	public int priority() {
		return 0;
	}
}
