package ru.fbtw.apk_bot.apk_telegram_bot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import retrofit2.Response;
import ru.fbtw.apk_bot.apk_telegram_bot.client.AppsClient;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.AppNamesDto;
import ru.fbtw.apk_bot.apk_telegram_bot.exception.AppNamesException;
import ru.fbtw.apk_bot.apk_telegram_bot.service.MessageLocalizationService;

@Service
@AllArgsConstructor
public class AppsMessageHandler implements GenericMessageHandler {

	private final AppsClient appsClient;
	private final MessageExceptionHandler messageExceptionHandler;
	private final MessageLocalizationService messageLocalizationService;

	@Override
	public BotApiMethod<?> handle(Update update, BotApiMethod<?> previousHandle) {
		if (!update.hasMessage()) {
			return previousHandle;
		}

		Message message = update.getMessage();

		if (!message.getText().startsWith("/apps")) {
			return previousHandle;
		}

		try {
			Response<AppNamesDto> response = appsClient.getApps().execute();

			if (!response.isSuccessful()) {
				throw new AppNamesException(response);
			}

			String names = String.join("\n", response.body().getNames());

			String text = messageLocalizationService.getMessage("app.names", new Object[]{names});

			SendMessage reply = new SendMessage();
			reply.setChatId(message.getChatId());
			reply.setText(text);

			return reply;

		} catch (Exception e) {
			return messageExceptionHandler.handle(e);
		}
	}
}
