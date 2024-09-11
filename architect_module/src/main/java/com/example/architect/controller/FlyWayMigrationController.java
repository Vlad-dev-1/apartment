package com.example.architect.controller;


import com.example.architect.model.fly_migration.FlywayMigrationDto;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.util.Objects.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FlyWayMigrationController {

    private final Flyway flyway;

    @PostMapping("/fly-way-migration")
    public ResponseEntity<?> saveFlyMigration(@RequestBody FlywayMigrationDto flywayMigrationDto) {

        //Получаем имя файла
        String fileName = flywayMigrationDto.getFileName();
        // Получаем данные для записи в файл
        String dataToWrite = flywayMigrationDto.getContent();
        File file = null;

        if (!isNull(fileName) && !fileName.isEmpty() && !dataToWrite.isEmpty() && !isNull(dataToWrite)) {

            // Записываем данные в файл
            try {

                //Создаем каталог
                String dirPath = "C:\\IdeaProjects\\rent_apartment_app\\architect_module\\src\\main\\resources\\db\\migration";
                File directory = new File(dirPath);

                //Проверяем существует ли каталог
                if (!directory.exists()) {

                    // Создаем родительский каталог
                    directory.mkdirs();
                }
                file = new File(directory, fileName + ".sql");

//                // Создаем файл, если он не существует
//                if (!file.exists()) {
//                    file.createNewFile();
//                }

                // Используем FileWriter для записи данных
                FileWriter writer = new FileWriter(file, true); // true для дозаписи
                writer.write(dataToWrite);
                writer.flush();
                writer.close();

                //Запускаем миграцию базы данных
                flyway.migrate();

                return ResponseEntity.ok("Файл успешно записан: " + file.getAbsolutePath() +
                        ". Миграция базы данных успешно создана");
            } catch (IOException e) {
                throw new RuntimeException("Файл не был записан. Было выброшено исключение " + e.getMessage());
            }
        }else {
            throw new RuntimeException("Пришли пустые данные, нет возможности записать файл.");
        }
    }
}
