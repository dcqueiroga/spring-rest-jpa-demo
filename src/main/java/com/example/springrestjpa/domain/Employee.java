package com.example.springrestjpa.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_employee")
public class Employee extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "department")
    private String department;
}
