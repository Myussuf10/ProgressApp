package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "class")
@Data
public class Class {

    @Id
    @Column(name = "classid", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dow")
    //@Temporal(TemporalType.DATE)
    //@JsonFormat(pattern = "yyyy-mm-dd")
    private String dow;

    @OneToOne(mappedBy = "register")
    private Attendance attendance;

    @Column(name = "classtime")
    //@Temporal(TemporalType.TIME)
    private String time;

    @Column(name = "topic")
    private String topic;

    @ManyToOne(cascade = {CascadeType.PERSIST,
    CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectid")
    private Subject subject;

    @JsonBackReference(value = "attendance-register")
    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
