package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attendance", orphanRemoval = true)
    private List<Student> students = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "classid")
    private Class register;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
