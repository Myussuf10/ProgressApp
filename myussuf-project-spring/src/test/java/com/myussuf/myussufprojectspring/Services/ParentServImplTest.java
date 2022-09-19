package com.myussuf.myussufprojectspring.Services;

import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Repository.ParentRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ParentServImplTest {

    private ParentServImpl underTest;
    @Mock private ParentRepo parentRepo;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setup(){
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ParentServImpl(parentRepo);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getParent() {
        Parent parent = new Parent(
                "John","phillip",
                "abc@gmail.com","123");
        parent.setId(2);
        underTest.getParent(2);
        ArgumentCaptor<Parent> parentArgumentCaptor =
                ArgumentCaptor.forClass(Parent.class);
        verify(parentRepo).findById(2);
        Parent capturedParent = parentArgumentCaptor.getValue();
        assertThat(capturedParent).isEqualTo(parent);
    }

    @Test
    void saveParent() {
    }

    @Test
    void getParentByEmail() {
        underTest.getParentByEmail("as@gmal.com");
    }
}