package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ssubject_pkey")
    @SequenceGenerator(name="subject_pkey", sequenceName = "subject_pkey", allocationSize = 1)
    @Column(name = "subjectid", updatable = false, nullable = false)
    private int id;

    private String subjectname;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "teacherid")
    @JsonBackReference(value = "teacher-subject")
    private Teacher teacher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<Comments> comments;

    @OneToMany(mappedBy = "subject")
    @JsonManagedReference(value = "subject-class")
    private List<Class> aClass;

    public Subject(String subjectname) {
        this.subjectname = subjectname;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Subject() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Class> getaClass() {
        return aClass;
    }

    public void setaClass(List<Class> aClass) {
        this.aClass = aClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id && Objects.equals(subjectname, subject.subjectname) && Objects.equals(teacher, subject.teacher) && Objects.equals(aClass, subject.aClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectname, teacher, aClass);
    }
}
