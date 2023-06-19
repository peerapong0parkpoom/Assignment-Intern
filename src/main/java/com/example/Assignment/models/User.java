package com.example.Assignment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user" , schema = "postgres")
public class User {
    @Id
    int id;
    String u_name;
    String department;
}
