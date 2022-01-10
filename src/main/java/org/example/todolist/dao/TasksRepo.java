package org.example.todolist.dao;

import org.example.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasksRepo extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.title = ?1")
    Optional<Task> findTaskByTitle(String title);
}
