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
@RequestMapping("/fifteenMinStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerFifteenMin {
    private restRepositoryFifteenMinStatus fifteenMinRepository;
    public ControllerFifteenMin(restRepositoryFifteenMinStatus fifteenMinRepository) {
        this.fifteenMinRepository = fifteenMinRepository;
    }
    @GetMapping
    public List<FifteenMinStatus> getList() {
        return (List<FifteenMinStatus>) fifteenMinRepository.findAll();
    }
    @PostMapping
    public FifteenMinStatus addUser(@RequestBody FifteenMinStatus fifteenminstatus) {
        return fifteenMinRepository.save(fifteenminstatus);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	fifteenMinRepository.deleteById(id);
    }
}

