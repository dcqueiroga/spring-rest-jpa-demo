package com.example.springrestjpa.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SALARY")
    private Integer salary;

    @Column(name = "DEPARTMENT")
    private String department;
}
