package ru.fbtw.apk_bot.ci_getaway.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "codemagic_app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodemagicApp {
	@Id
	private String appId;
	private String appName;
	private String workflowId;
}
