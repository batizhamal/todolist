package org.example.todolist.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Task {
    @Id
    @SequenceGenerator(
            name = "tasks_sequence",
            sequenceName = "tasks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tasks_sequence"
    )
    private Long id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;

    @Transient
    private int daysLeft;

    public Task() {
    }

    public Task(String title,
                String description,
                LocalDate deadline,
                boolean done) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
    }

    public Task(Long id,
                String title,
                String description,
                LocalDate deadline,
                boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getDaysLeft() {
        return Period.between(LocalDate.now(), this.deadline).getDays();
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                ", daysLeft=" + daysLeft +
                '}';
    }
}
