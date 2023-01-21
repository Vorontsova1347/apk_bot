package ru.fbtw.apk_bot.apk_telegram_bot.handlers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import retrofit2.Response;
import ru.fbtw.apk_bot.apk_telegram_bot.client.BuildsClient;
import ru.fbtw.apk_bot.apk_telegram_bot.dto.BuildStartedDto;
import ru.fbtw.apk_bot.apk_telegram_bot.exception.BuildException;
import ru.fbtw.apk_bot.apk_telegram_bot.service.MessageLocalizationService;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildMessageHandler implements GenericMessageHandler {

	private final BuildsClient buildsClient;
	private final MessageExceptionHandler messageExceptionHandler;
	private final MessageLocalizationService messageLocalizationService;

	@Override
	public BotApiMethod<?> handle(Update update, BotApiMethod<?> previousHandle) {

		if (previousHandle != null || !update.hasMessage()) {
			return previousHandle;
		}

		Message message = update.getMessage();

		if (!message.getText().startsWith("/build ")) {
			return previousHandle;
		}

		String[] textSegments = message.getText().split(" ");

		if (textSegments.length == 1) {
			String replyText = messageLocalizationService.getMessage("build.help");

			SendMessage errorMessage = new SendMessage();
			errorMessage.setText(replyText);
			errorMessage.setChatId(message.getChatId());
			return errorMessage;
		}

		String appName = Arrays.stream(textSegments)
				.skip(1)
				.collect(Collectors.joining(" "));

		try {
			Response<BuildStartedDto> response = buildsClient.postBuild(appName).execute();

			if (!response.isSuccessful()) {
				throw new BuildException(response);
			}

			BuildStartedDto body = response.body();
			if (body == null) {
				throw new BuildException(response);
			}

			String replyText = messageLocalizationService.getMessage(
					"build.start.success",
					new Object[]{body.getBuildId()}
			);

			SendMessage replyMessage = new SendMessage();
			replyMessage.setText(replyText);
			replyMessage.setChatId(message.getChatId());
			return replyMessage;
		} catch (Exception e) {
			return messageExceptionHandler.handle(e);
		}

	}
}
