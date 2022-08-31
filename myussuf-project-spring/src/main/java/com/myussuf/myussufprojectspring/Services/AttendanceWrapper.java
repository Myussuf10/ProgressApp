package com.myussuf.myussufprojectspring.Services;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class AttendanceWrapper {
    private List<Integer> studentids = new ArrayList<>();

    public List<Integer> getStudentids() {
        return studentids;
    }

    public void setStudentids(List<Integer> studentids) {
        this.studentids = studentids;
    }
}
