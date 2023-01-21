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
import ru.fbtw.apk_bot.apk_telegram_bot.exception.BuildsException;
import ru.fbtw.apk_bot.apk_telegram_bot.exception.DownloadException;
import ru.fbtw.apk_bot.apk_telegram_bot.service.MessageLocalizationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BuildsMessageHandler implements GenericMessageHandler {

	private final BuildsClient buildsClient;
	private final MessageExceptionHandler messageExceptionHandler;
	private final MessageLocalizationService messageLocalizationService;

	@Override
	public BotApiMethod<?> handle(Update update, BotApiMethod<?> previousHandle) {
		if (!update.hasMessage()) {
			return previousHandle;
		}

		Message message = update.getMessage();

		if (!message.getText().equals("/builds")) {
			return previousHandle;
		}


		try {
			Response<List<BuildStatusDto>> response = buildsClient.getBuilds().execute();

			if (!response.isSuccessful()) {
				throw new BuildsException(response);
			}

			List<BuildStatusDto> body = response.body();
			if (body == null) {
				throw new BuildsException(response);
			}

			String replyText = body.stream()
					.map(this::getBuildInfo)
					.collect(Collectors.joining("\n\n\n"));


			SendMessage replyMessage = new SendMessage();
			replyMessage.setText(replyText);
			replyMessage.setChatId(message.getChatId());
			return replyMessage;
		} catch (Exception e) {
			return messageExceptionHandler.handle(e);
		}

	}

	private String getBuildInfo(BuildStatusDto body) {
		String replyText = switch (body.getStatus()) {
			case fail -> messageLocalizationService.getMessage(
					"build.status.fail",
					new Object[]{body.getBuildId(), body.getAppName()}
			);
			case success -> messageLocalizationService.getMessage(
					"build.status.success",
					new Object[]{body.getBuildId(), body.getAppName(), body.getResultUrl()}
			);
			case inProgress -> messageLocalizationService.getMessage(
					"build.status.loading",
					new Object[]{body.getBuildId(), body.getAppName()}
			);
		};
		return replyText;
	}

	@Override
	public int priority() {
		return 2;
	}
}
