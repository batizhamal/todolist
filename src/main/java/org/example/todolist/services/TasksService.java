package org.example.todolist.services;

import org.example.todolist.dao.TasksRepo;
import org.example.todolist.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TasksService {

    private final TasksRepo tasksRepo;

    @Autowired
    public TasksService(TasksRepo tasksRepo) {
        this.tasksRepo = tasksRepo;
    }

    public List<Task> allTasks() {
        return tasksRepo.findAll();
    }

    public Task aTask(Long id) {
        return tasksRepo.findById(id).orElseThrow(() -> new IllegalStateException("There is no task with id "+ id));
    }

    public void createTask(Task task) {

        //checks if there is a task with the same title
        Optional<Task> taskOptional = tasksRepo
                .findTaskByTitle(task.getTitle());

        if (taskOptional.isPresent()) {
            throw new IllegalStateException("That title already exists.");
        }

        tasksRepo.save(task);
    }

    @Transactional
    public void updateTask(Long id,
                           String title,
                           String description,
                           LocalDate deadline) {
        Task task = tasksRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("The task with id " + id + " +does not exist."));

        if (title != null &&
                title.length()>0 &&
                !Objects.equals(task.getTitle(), title)) {

            Optional<Task> taskOptional = tasksRepo.findTaskByTitle(title);

            if (taskOptional.isPresent()) {
                throw new IllegalStateException("The title is taken.");
            }

            task.setTitle(title);
        }

        if (description != null &&
                description.length()>0 &&
                !Objects.equals(task.getDescription(), description)) {
            task.setDescription(description);
        }

        if (deadline != null &&
                !deadline.isEqual(task.getDeadline())) {
            task.setDeadline(deadline);
        }
    }


    public void deleteTask(Long id) {
        boolean exists = tasksRepo.existsById(id);

        if (!exists) {
            throw new IllegalStateException("There is no task with id "+ id);
        }

        tasksRepo.deleteById(id);
    }

    @Transactional
    public void markAsDone(Long id) {
        Task task = tasksRepo.findById(id).orElseThrow(() -> new IllegalStateException("There is no task with id "+id));

        task.setDone(true);
    }
}
