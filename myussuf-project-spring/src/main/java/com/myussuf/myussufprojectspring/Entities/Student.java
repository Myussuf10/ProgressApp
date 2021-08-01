package com.myussuf.myussufprojectspring.Entities;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "student_pkey")
    @SequenceGenerator(name="student_pkey", sequenceName = "student_pkey", allocationSize = 1)
    @Column(name = "studentid", updatable = false, nullable = false)
    private int id;

    private String firstname;

    private String lastname;

    private Date dob;

    private String school;



}
