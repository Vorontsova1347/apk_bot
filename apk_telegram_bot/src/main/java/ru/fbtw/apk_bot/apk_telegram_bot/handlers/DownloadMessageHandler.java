package ru.fbtw.apk_bot.apk_telegram_bot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import retrofit2.Response;
import ru.fbtw.apk_bot.apk_telegram_bot.client.BuildsClient;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.BuildStatusDto;
import ru.fbtw.apk_bot.apk_telegram_bot.exception.DownloadException;
import ru.fbtw.apk_bot.apk_telegram_bot.service.MessageLocalizationService;


@Service
@AllArgsConstructor
public class DownloadMessageHandler implements GenericMessageHandler {

	private final BuildsClient buildsClient;
	private final MessageExceptionHandler messageExceptionHandler;
	private final MessageLocalizationService messageLocalizationService;

	@Override
	public BotApiMethod<?> handle(Update update, BotApiMethod<?> previousHandle) {
		if (!update.hasMessage()) {
			return previousHandle;
		}

		Message message = update.getMessage();

		if (!message.getText().startsWith("/download")) {
			return previousHandle;
		}

		String[] textSegments = message.getText().split(" ");

		if (textSegments.length != 2) {
			String replyText = messageLocalizationService.getMessage("build.status.help");

			SendMessage errorMessage = new SendMessage();
			errorMessage.setText(replyText);
			errorMessage.setChatId(message.getChatId());
			return errorMessage;
		}

		String buildId = textSegments[1];

		try {
			Response<BuildStatusDto> response = buildsClient.getBuild(buildId).execute();

			if (!response.isSuccessful()) {
				throw new DownloadException(response);
			}

			BuildStatusDto body = response.body();
			if (body == null) {
				throw new DownloadException(response);
			}

			String replyText = switch (body.getStatus()) {
				case fail -> messageLocalizationService.getMessage(
						"build.status.fail",
						new Object[]{buildId, body.getAppName()}
				);
				case success -> messageLocalizationService.getMessage(
						"build.status.success",
						new Object[]{buildId, body.getAppName(), body.getResultUrl()}
				);
				case inProgress -> messageLocalizationService.getMessage(
						"build.status.loading",
						new Object[]{buildId, body.getAppName()}
				);
			};


			SendMessage replyMessage = new SendMessage();
			replyMessage.setText(replyText);
			replyMessage.setChatId(message.getChatId());
			return replyMessage;
		} catch (Exception e) {
			return messageExceptionHandler.handle(e);
		}

	}
}
