package com.example.Assignment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "svc_backend_team" , schema = "public")
public class Person {
    @Id
    private int id;
    private String name;

}
