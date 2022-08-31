package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "subject_pkey")
    @SequenceGenerator(name="subject_pkey", sequenceName = "subject_pkey", allocationSize = 1)
    @Column(name = "subjectid", updatable = false, nullable = false)
    private int id;

    @Column(name = "subjectname")
    private String subjectname;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "teacherid")
    @JsonBackReference(value = "teacher-subject")
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> student;

    @OneToMany(mappedBy = "subject")
    @JsonBackReference(value = "subject-class")
    private List<Class> aClass;

    @JsonBackReference(value = "subjects-students")
    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Subject(String subjectname) {
        this.subjectname = subjectname;
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
