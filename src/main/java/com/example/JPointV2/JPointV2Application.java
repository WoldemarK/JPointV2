package com.example.JPointV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JPointV2Application {

	public static void main(String[] args) {
		SpringApplication.run(JPointV2Application.class, args);
	}
//    @Bean
//    public CommandLineRunner addUser(UserRepository userRepository) {
//        return args -> {
//            userRepository.save(User.builder()
//                    .login("123")
//                    .password("password")
//                    .firstName("Ivan")
//                    .lastName("Tomsin")
//                    .birth("20.02.1987")
//                    .phoneNumber("88000000")
//                    .email("ivan@email.com")
//                    .isActive(true)
//                    .sex("M")
//                    .build());
//            userRepository.save(User.builder()
//                    .login("321")
//                    .password("password321")
//                    .firstName("Tom")
//                    .lastName("Mik")
//                    .birth("12.02.1990")
//                    .phoneNumber("88000001")
//                    .email("tom@email.com")
//                    .isActive(true)
//                    .sex("M")
//                    .build());
//
//        };
//    }
//
//    @Bean
//    public CommandLineRunner addPost(PostRepository postRepository) {
//        return args -> {
//            postRepository.save(Post.builder()
//                    .name("Java Developer")
//                    .description("Разработчик")
//                    .build());
//            postRepository.save(Post.builder()
//                    .name("manager")
//                    .description("Отдел продаж")
//                    .build());
//        };
//    }
//    @Bean
//    public CommandLineRunner addDepartment(DepartmentRepository departmentRepository) {
//        return args -> {
//            departmentRepository.save(Department.builder()
//                    .name("IT")
//                    .description("Подразделение разработки")
//                    .build());
//            departmentRepository.save(Department.builder()
//                    .name("Продажи")
//                    .description("Проектные продажи")
//                    .build());
//        };
//    }
//    @Bean
//    public CommandLineRunner addTask(TaskRepository taskRepository) {
//        return args -> {
//            taskRepository.save(Task.builder()
//                    .title("Фича")
//                    .descriptions("Написать новый функционал")
//                    .build());
//            taskRepository.save(Task.builder()
//                    .title("Документы")
//                    .descriptions("Подготовить к проектную документацию")
//                    .build());
//        };
//    }
}
