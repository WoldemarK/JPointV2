package com.example.JPointV2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
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

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", length = 64, nullable = false, unique = true)
    private String login;

    @Column(name = "password", length = 128, nullable = false, unique = true)
    private String password;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "First Name should be between 2 and 20 characters")
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Column(name = "year_of_birth")
    private String birth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    @Column(name = "email", length = 32, nullable = false, unique = true)
    private String email;
    @JsonProperty
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "sex")
    private String sex;
    @CreationTimestamp
    @Column(name = "creation")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate creation;

    @UpdateTimestamp
    @Column(name = "update")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate update;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_department", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private List<Department> departments;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_post", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_task", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task"))
    private List<Task> tasks;

    public void addDepartment(Department _department) {
        if (this.departments == null)
            this.departments = new ArrayList<>();
        this.departments.add(_department);

    }

    public void addPost(Post _post) {
        if (this.posts == null)
            this.posts = new ArrayList<>();
        this.posts.add(_post);

    }

    public void addTask(Task _task) {
        if (this.tasks == null)
            this.tasks = new ArrayList<>();
        this.tasks.add(_task);
    }

}
