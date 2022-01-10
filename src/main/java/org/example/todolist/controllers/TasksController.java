package org.example.todolist.controllers;

import org.example.todolist.model.Task;
import org.example.todolist.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TasksController {

    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping()
    public List<Task> allTasks() {
        return tasksService.allTasks();
    }

    @GetMapping("{id}")
    public Task aTask(@PathVariable("id") Long id) {
        return tasksService.aTask(id);
    }

    @PostMapping
    public void createTask(@RequestBody Task task) {
        tasksService.createTask(task);
    }

    @PutMapping(path = "{id}")
    public void updateTask(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate deadline) {
        tasksService.updateTask(id, title, description, deadline);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        tasksService.deleteTask(id);
    }

    @PutMapping(path = "done/{id}")
    public void markAsDone(@PathVariable("id") Long id) {
        tasksService.markAsDone(id);
    }

}
