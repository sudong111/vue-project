package com.example.backend.config;

import com.example.backend.model.GuitarCategory;
import com.example.backend.repository.GuitarCategoriesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuitarInitializer {

    /**
     * 애플리케이션 시작 시 실행되는 CommandLineRunner 빈을 정의합니다.
     * 이 빈은 GuitarRepository를 주입받아 기본 카테고리를 insert 합니다.
     * @param guitarCategoriesRepository 카테고리 정보를 관리하는 레포지토리
     * @return CommandLineRunner 인스턴스
     */
    @Bean
    public CommandLineRunner initGuitarCategories(GuitarCategoriesRepository guitarCategoriesRepository) {
        return args -> {
            String[] categories = { "acoustic", "electric", "base", "etc" };
            System.out.println("기본 카테고리를 생성합니다.");

            for(String name : categories) {
                if(guitarCategoriesRepository.findByName(name) == null) {
                    GuitarCategory category = new GuitarCategory();
                    category.setName(name);
                    guitarCategoriesRepository.insertGuitarCategory(category);
                }
            }
            System.out.println("기본 카테고리 생성 성공");
        };
    }
}
