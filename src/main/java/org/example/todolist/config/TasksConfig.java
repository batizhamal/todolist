package org.example.todolist.config;

import org.example.todolist.dao.TasksRepo;
import org.example.todolist.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class TasksConfig {

    @Bean
    CommandLineRunner commandLineRunner(TasksRepo repo) {
        return args -> {
            Task task1 = new Task(
                    "PHYS assignment",
                    "Write 7 pages lab report",
                    LocalDate.of(2022, Month.JANUARY, 15),
                    false
            );

            Task task2 = new Task(
                    "CSCI exam",
                    "Review the course material and prepare",
                    LocalDate.of(2022, Month.JANUARY, 20),
                    false
            );

            Task task3 = new Task(
                    "Read a book",
                    "The adventures of Sherlock Holmes",
                    LocalDate.of(2022, Month.JANUARY, 11),
                    false
            );

            repo.saveAll(
                    List.of(task1, task2, task3)
            );
        };
    }
}
