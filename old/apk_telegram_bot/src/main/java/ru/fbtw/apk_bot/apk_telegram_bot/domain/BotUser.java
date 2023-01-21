package ru.fbtw.apk_bot.apk_telegram_bot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bot_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BotUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
}
