package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.data.util.Lazy;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comments {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    CommentsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
            @JoinColumn(name="studentid")
            @JsonBackReference(value = "student-comment")
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teacherId") @JoinColumn(name = "teacherid")
    @JsonBackReference(value = "teacher-comments")
    Teacher teacher;

    String comment;
}
