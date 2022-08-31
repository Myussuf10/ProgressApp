package com.myussuf.myussufprojectspring.Entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @Id
    private int id;

    @OneToMany
    @JoinTable(
            name = "student_attendance",
            joinColumns = @JoinColumn(name = "attendanceid")
    )
    private List<Student> students = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class register;

    public int getId() {
        return id;
    }

    public Class getRegister() {
        return register;
    }

    public void setRegister(Class register) {
        this.register = register;
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
