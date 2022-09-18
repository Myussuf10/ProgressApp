package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToMany(mappedBy = "attendance", cascade = CascadeType.ALL)
    @JsonBackReference(value = "student-comment")
    Set<Student> student = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(nullable = false, name = "comments_classes")
//    Class classes;

    @ManyToOne()
    @JoinColumn(name = "teacherid")
    //@JsonBackReference(value = "teacher-comments")
    Teacher teacher;

    String date;

    String role;

    String comment;

    String sentBy;
}
