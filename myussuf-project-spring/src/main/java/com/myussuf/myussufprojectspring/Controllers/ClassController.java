package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Class;

import com.myussuf.myussufprojectspring.Services.ClassServImpl;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/class")
@AllArgsConstructor
public class ClassController {
    private ClassServImpl classServ;

    @GetMapping("{id}")
    public Class getClass(@PathVariable int id){
        return classServ.getClassDetails(id);
    }

    @GetMapping("subject/{id}")
    public List<Class> getSubject(@PathVariable int id){
        return classServ.getClassBySubject(id);
    }

    @PostMapping("/class/{subjectId}")
    public void setupClass(
             @RequestBody Class clas,
             @PathVariable int subjectId) throws ParseException {
        classServ.setUpClass(clas, subjectId);
    }
}
