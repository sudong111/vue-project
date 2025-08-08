package com.example.backend.config;

import com.example.backend.model.Category;
import com.example.backend.model.Subtype;
import com.example.backend.repository.GuitarCategoriesRepository;
import com.example.backend.repository.GuitarSubtypesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class GuitarInitializer {

    /**
     * 애플리케이션 시작 시 실행되는 CommandLineRunner 빈을 정의합니다.
     * 이 빈은 GuitarCategoriesRepository를 주입받아 기본 카테고리를 insert 합니다.
     * @param guitarCategoriesRepository 카테고리 정보를 관리하는 레포지토리
     * @return CommandLineRunner 인스턴스
     */
    @Bean
    public CommandLineRunner initGuitarCategories(GuitarCategoriesRepository guitarCategoriesRepository) {
        return args -> {
            String[] categories = { "acoustic", "electric", "base", "etc" };

            for(String name : categories) {
                if(guitarCategoriesRepository.findByName(name) == null) {
                    System.out.println("기본 카테고리를 생성합니다. 생성할 카테고리 name : " + name);
                    Category category = new Category();
                    category.setName(name);
                    guitarCategoriesRepository.insertGuitarCategory(category);
                    System.out.println("기본 카테고리 생성 성공. 생성한 카테고리 name : " + name);
                }
            }
        };
    }

    /**
     * 애플리케이션 시작 시 실행되는 CommandLineRunner 빈을 정의합니다.
     * 이 빈은 GuitarRepository를 주입받아 기본 카테고리를 insert 합니다.
     * @param guitarSubtypesRepository 카테고리 정보를 관리하는 레포지토리
     * @return CommandLineRunner 인스턴스
     */
    @Bean
    public CommandLineRunner initGuitarSubtypes(GuitarSubtypesRepository guitarSubtypesRepository) {
        return args -> {
            Map<Long, List<String>> subtypeMap = new HashMap<>();

            subtypeMap.put(1L, Arrays.asList("Nylon", "Steel"));
            subtypeMap.put(2L, Arrays.asList("Solid", "Montreal", "5th", "Mutiac"));
            // 3L은 베이스로 subtype 없음 — 안 넣음
            subtypeMap.put(4L, Arrays.asList("amp", "peek"));

            for (Map.Entry<Long, List<String>> entry : subtypeMap.entrySet()) {
                Subtype subtype = new Subtype();
                subtype.setCategory_id(entry.getKey());
                List<String> subtypeNames = entry.getValue();

                for (String name : subtypeNames) {
                    if(guitarSubtypesRepository.findByName(name) == null) {
                        System.out.println("기본 서브타입을 생성합니다. 생성할 서브타입 name : " + name);
                        subtype.setName(name);
                        guitarSubtypesRepository.insertGuitarSubtypes(subtype);
                        System.out.println("기본 서브타입 생성 성공. 생성한 서브타입 name : " + name);
                    }
                    
                }
            }
        };
    }
}
