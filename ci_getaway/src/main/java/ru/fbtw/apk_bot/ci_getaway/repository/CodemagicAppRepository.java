package ru.fbtw.apk_bot.ci_getaway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fbtw.apk_bot.ci_getaway.domain.CodemagicApp;

import java.util.Optional;

public interface CodemagicAppRepository extends JpaRepository<CodemagicApp, Integer> {
	Optional<CodemagicApp> findByAppName(String name);
}