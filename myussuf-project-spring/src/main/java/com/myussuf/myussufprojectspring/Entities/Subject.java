package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "subject")
    @JsonBackReference(value = "subject-class")
    private List<Class> aClass;

    @ManyToMany
    @JoinTable(
//            schema = "students",
            name = "subject_students",
            joinColumns = @JoinColumn(name = "subjectid"),
            inverseJoinColumns = @JoinColumn(name = "studentid")
    )
    @JsonBackReference(value = "subject-student")
    private List<Student> students;

    public Subject(String subjectname) {
        this.subjectname = subjectname;
    }

    public Subject() {

    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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

    public void addStudents(Student student){
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(student);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectname='" + subjectname + '\'' +
                '}';
    }
}
