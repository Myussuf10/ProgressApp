package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "teacher_pkey")
    @SequenceGenerator(name="teacher_pkey", sequenceName = "teacher_pkey", allocationSize = 1)
    @Column(name = "teacherid", updatable = false, nullable = false)
    private int id;

    private String firstname;

    private String lastname;

    @Email
    private String email;

    private String password;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "teacher-subject")
    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Teacher(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public Teacher() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Convenience method for bi-directional relationship

    public void addSubject(Subject subject){
        if(subjects == null){
            subjects = new ArrayList<>();
        }
        subjects.add(subject);
        subject.setTeacher(this);
    }

    public void removeSubject(Subject subject){
        subjects.remove(subject);
        subject.setTeacher(null);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
