package com.myussuf.myussufprojectspring.Controllers;

import com.myussuf.myussufprojectspring.Entities.Parent;
import com.myussuf.myussufprojectspring.Services.EmailSenderServ;
import com.myussuf.myussufprojectspring.Services.ParentServ;
import lombok.AllArgsConstructor;
import org.springframework.data.web.JsonPath;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parent")
@AllArgsConstructor
public class ParentController {

    private ParentServ parentServ;

    @GetMapping("{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public Parent getParent(@PathVariable Integer id){return parentServ.getParent(id);}

    @GetMapping("/parents")
    public List<Parent> allParents(){
        List<Parent> parent = new ArrayList<>();

        return parentServ.getParents();
    }
    @PostMapping("/parent")
    public void signUpParent(@RequestBody Parent parent){
        parentServ.saveParent(parent);
    }
}
