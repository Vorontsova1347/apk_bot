package ru.fbtw.apk_bot.apk_telegram_bot.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bot_state")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BotState {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@ManyToOne()
	@Column(name="parent_id")
	BotState parent;

	@OneToMany(mappedBy = "parent_id")
	List<BotState> children;
}
