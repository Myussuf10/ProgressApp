package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "attendanceid")
    int id;

    @ManyToMany(mappedBy = "attendance")
    private Set<Student> student = new HashSet<>();

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "classid")
    private Class register;

    private String understanding;

    public String getUnderstanding() {
        return understanding;
    }

    public void setUnderstanding(String understanding) {
        this.understanding = understanding;
    }

    public int getId() {
        return id;
    }

    public void setRegister(Class register) {
        this.register = register;
    }

   // @JsonManagedReference(value = "attendance-register")
    public Class getRegister() {
        return register;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonBackReference(value = "students_attendance")
    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }
}
