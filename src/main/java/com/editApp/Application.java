package com.editApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Приложение Spring-boot, которое запускает функционал библиотеки, собирает проект,
// ищет контроллеры, файлы подключения к БД, шаблоны и все необходимое
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}