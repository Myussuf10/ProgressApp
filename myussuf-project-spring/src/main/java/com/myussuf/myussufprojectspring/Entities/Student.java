package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "student")
@Validated
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "studentid", updatable = false, nullable = false)
    private int id;

    @Pattern(regexp = "^[A-Za-z]+$", message = "First name cannot have spaces")
    private String firstname;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name cannot have spaces")
    private String lastname;

    @Past(message = "Date of birth must be in the past")
    @Temporal(TemporalType.DATE)
    private Date dob;

    private String school;

    @ManyToMany
    @JoinTable(
//            schema = "students",
            name = "subject_students",
            joinColumns = @JoinColumn(name = "studentid"),
            inverseJoinColumns = @JoinColumn(name = "subjectid")

    )
    @JsonManagedReference(value = "subject-student")
    private List<Subject> subjects;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "parentid")
    @JsonBackReference(value = "parent-child")
    private Parent parent;


    public Student() {
    }

    public Student(String firstname, String lastname, Date dob, String school, List subjects) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.school = school;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjectID) {
        this.subjects = subjectID;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void addSubject(Subject subject){
        if(this.subjects == null){
            subjects = new ArrayList<>();
        }
        subjects.add(subject);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dob=" + dob +
                ", school='" + school + '\'' +
                '}';
    }
}
