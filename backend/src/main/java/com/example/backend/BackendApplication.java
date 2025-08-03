package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/**
	 * 애플리케이션이 완전히 시작된 후 실행되는 러너를 정의합니다.
	 * 이 코드는 서버가 준비되었음을 콘솔에 출력하는 역할을 합니다.
	 * @return ApplicationRunner 인스턴스
	 */
	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			System.out.println("----------------------------------------------");
			System.out.println("      ✅ Spring Boot Application Started!      ");
			System.out.println("----------------------------------------------");
		};
	}
}
