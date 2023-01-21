/*
package ru.fbtw.apk_bot.apk_telegram_bot.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

//@Entity
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
	BotState parent;

	@OneToMany(mappedBy = "parent")
	List<BotState> children;
}
*/
