package com.example.JPointV2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;
    @Email
    @Column(name = "email")
    @NotEmpty(message = "Email should be empty ")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "descriptions")
    private String descriptions;
    @Column(name = "type")
    private String type;
    @Column(name = "website")
    private String website;
    @Column(name = "inn")
    private Long INN;
    @ManyToMany(mappedBy = "company")
    private List<Task> tasks;
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate creation;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate update;

    public Company() {
        this.INN = new Random().nextLong();
    }

    public void addTask(Task _task) {
        if (this.tasks == null)
            this.tasks = new ArrayList<>();
        this.tasks.add(_task);


    }

}
