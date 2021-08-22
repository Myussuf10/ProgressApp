package com.myussuf.myussufprojectspring.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "class")
public class Class {

    @Id
    private int id;

    @Column(name = "classdate")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "classtime")
    @Temporal(TemporalType.TIME)
    private Time time;

    @OneToOne(cascade = {CascadeType.PERSIST,
    CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    private Subject subject;

    @OneToOne()
    private Teacher teacher;

}
