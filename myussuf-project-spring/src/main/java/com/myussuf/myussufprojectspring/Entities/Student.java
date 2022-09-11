package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "student")
@Validated
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "studentid", updatable = false, nullable = false)
    private int id;

    @Pattern(regexp = "^[A-Za-z]+$", message = "First name cannot have spaces")
    private String firstname;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "student_attendance",
    joinColumns = @JoinColumn(referencedColumnName = "studentid"))
    private Attendance attendance;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name cannot have spaces")
    private String lastname;

    @Past(message = "Date of birth must be in the past")
    private Date dob;

    private String school;

    @ManyToMany()
    @JoinTable(name = "student_subject",
            joinColumns = @JoinColumn(referencedColumnName = "studentid"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "subjectid"))
    private List<Subject> subjects;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", orphanRemoval = true)
    private List<Comments> comments;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "parentid")
    @JsonBackReference(value = "a")
    private Parent parent;

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(firstname, student.firstname) && Objects.equals(lastname, student.lastname) && Objects.equals(dob, student.dob) && Objects.equals(school, student.school) && Objects.equals(subjects, student.subjects) && Objects.equals(parent, student.parent);
    }

    @JsonManagedReference(value = "student-comment")
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, dob, school, subjects, parent);
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

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
