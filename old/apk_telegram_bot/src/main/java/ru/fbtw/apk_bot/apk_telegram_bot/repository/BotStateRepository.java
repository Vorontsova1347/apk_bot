package ru.fbtw.apk_bot.apk_telegram_bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fbtw.apk_bot.apk_telegram_bot.domain.BotState;

public interface BotStateRepository extends JpaRepository<BotState, Long> {
}