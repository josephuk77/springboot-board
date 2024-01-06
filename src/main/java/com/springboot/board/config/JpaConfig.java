package com.springboot.board.config;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configurable
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("lee"); // TODO: 추후 로그인한 사용자의 이름을 가져오도록 수정
    }
}
