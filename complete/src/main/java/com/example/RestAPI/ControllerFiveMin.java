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
@RequestMapping("/fiveMinStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerFiveMin {
    private restRepositoryFiveMinStatus fiveMinRepository;
    public ControllerFiveMin(restRepositoryFiveMinStatus fiveMinRepository) {
        this.fiveMinRepository = fiveMinRepository;
    }
    @GetMapping
    public List<FiveMinStatus> getList() {
        return (List<FiveMinStatus>) fiveMinRepository.findAll();
    }
    @PostMapping
    public FiveMinStatus addUser(@RequestBody FiveMinStatus fiveminstatus) {
        return fiveMinRepository.save(fiveminstatus);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	fiveMinRepository.deleteById(id);
    }
}

