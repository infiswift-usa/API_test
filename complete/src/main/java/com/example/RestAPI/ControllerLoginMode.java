package com.example.RestAPI;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/loginMode")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerLoginMode {
    private restRepositoryLoginMode loginModeRepository;
    public ControllerLoginMode(restRepositoryLoginMode loginModeRepository) {
        this.loginModeRepository = loginModeRepository;
    }
    @GetMapping
    public List<LoginMode> getList() {
        return (List<LoginMode>) loginModeRepository.findAll();
    }
    @PostMapping
    public LoginMode addUser(@RequestBody LoginMode loginmode) {
        return loginModeRepository.save(loginmode);
    }
    @PutMapping("/{id}")
    public LoginMode updateUser(@PathVariable("id") Integer id, @RequestBody LoginMode loginmode)
    {
    	return loginModeRepository.save(loginmode);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	loginModeRepository.deleteById(id);
    }
}

