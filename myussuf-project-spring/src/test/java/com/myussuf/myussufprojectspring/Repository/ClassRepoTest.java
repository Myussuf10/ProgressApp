package com.myussuf.myussufprojectspring.Repository;

import com.myussuf.myussufprojectspring.Entities.Class;
import com.myussuf.myussufprojectspring.Entities.Subject;
import com.myussuf.myussufprojectspring.Entities.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ClassRepoTest {

    @Autowired
    private ClassRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findBySubjectId() {
        Teacher teacher = new Teacher("Mohamed","Ahmed","mo@gmail.com", "1234");
        Subject x = new Subject("Maths");
        x.setTeacher(teacher);
        Class y = new Class();

        y.setId(1);
        y.setTopic("Grammar");
        y.setSubject(x);
        underTest.save(y);

        Class u = underTest.findBySubjectId(x.getId());

        assertThat(u.getSubject()).isEqualTo(x);
    }
}