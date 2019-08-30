package com.example.RestAPI;
import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/loginFailure")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerLoginFailure {
    private restRepositoryLoginFailure loginRepository;
    public ControllerLoginFailure(restRepositoryLoginFailure loginRepository) {
        this.loginRepository = loginRepository;
    }
    @GetMapping
    public List<LoginFailure> getList() {
        return (List<LoginFailure>) loginRepository.findAll(sortByIdAsc());
    }
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "id");
    }
    @PostMapping
    public LoginFailure addUser(@RequestBody LoginFailure loginfailure) {
        return loginRepository.save(loginfailure);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id
    ) {
    	loginRepository.deleteById(id);
    }
}

