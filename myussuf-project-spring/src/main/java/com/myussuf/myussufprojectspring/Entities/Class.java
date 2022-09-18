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
import java.util.*;

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

//    @OneToMany(mappedBy = "classes", orphanRemoval = true)
//    @JsonBackReference(value = "comments_classes")
//    private Set<Comments> comments = new HashSet<>();

    @OneToMany(mappedBy = "register", fetch =FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendance;

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
    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDow() {
        return dow;
    }

    public void setDow(String dow) {
        this.dow = dow;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
