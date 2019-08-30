package com.example.RestAPI;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/loginStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080/loginStatus"})
public class ControllerLoginStatus {
    private restRepositoryLoginStatus loginStatusRepository;
    public ControllerLoginStatus(restRepositoryLoginStatus loginStatusRepository) {
        this.loginStatusRepository = loginStatusRepository;
    }
    @GetMapping
    public List<LoginStatus> getList() {
        return (List<LoginStatus>) loginStatusRepository.findAll();
    }
    @PostMapping
    public LoginStatus addUser(@RequestBody LoginStatus loginstatus) {
        return loginStatusRepository.save(loginstatus);
        
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id
    ) {
    	loginStatusRepository.deleteById(id);
    }
}
