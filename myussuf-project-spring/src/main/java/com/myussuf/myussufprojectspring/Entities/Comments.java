package com.myussuf.myussufprojectspring.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comments {

    @EmbeddedId
    CommentsKey id;

    @ManyToOne @MapsId("studentId")
            @JoinColumn(name="studentid")
    Student student;

    @ManyToOne @MapsId("teacherId") @JoinColumn(name = "teacherid")
    Teacher teacher;

    @ManyToOne @MapsId("subjectId") @JoinColumn(name = "subjectid")
    Subject subject;

    String comment;
}
