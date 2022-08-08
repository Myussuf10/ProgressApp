package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "class")
@Getter
@Setter
public class Class {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dow")
    //@Temporal(TemporalType.DATE)
    //@JsonFormat(pattern = "yyyy-mm-dd")
    private String dow;

    @Column(name = "classtime")
    //@Temporal(TemporalType.TIME)
    private String time;

    @ManyToOne(cascade = {CascadeType.PERSIST,
    CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "subject_class", referencedColumnName = "subjectid")
    @JsonBackReference(value = "subject-class")
    private Subject subject;


}
