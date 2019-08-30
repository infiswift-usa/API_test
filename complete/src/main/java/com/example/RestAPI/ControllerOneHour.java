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
@RequestMapping("/oneHourStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerOneHour {
    private restRepositoryOneHourStatus oneHourRepository;
    public ControllerOneHour(restRepositoryOneHourStatus oneHourRepository) {
        this.oneHourRepository = oneHourRepository;
    }
    @GetMapping
    public List<OneHourStatus> getList() {
        return (List<OneHourStatus>) oneHourRepository.findAll();
    }
    @PostMapping
    public OneHourStatus addUser(@RequestBody OneHourStatus onehourstatus) {
        return oneHourRepository.save(onehourstatus);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	oneHourRepository.deleteById(id);
    }
}

