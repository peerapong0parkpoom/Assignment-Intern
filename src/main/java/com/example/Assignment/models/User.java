package com.example.Assignment.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user" , schema = "postgres")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String u_name;
    private String department;

    public User() {}
    public User(int id, String u_name, String department) {
        this.id = id;
        this.u_name = u_name;
        this.department = department;
    }
}
