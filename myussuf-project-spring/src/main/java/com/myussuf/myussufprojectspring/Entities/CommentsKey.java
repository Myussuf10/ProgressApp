package com.myussuf.myussufprojectspring.Entities;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsKey implements Serializable {

    @Column(name = "studentid")
    int studentId;

    @Column(name = "teacherid")
    int teacherId;

}
