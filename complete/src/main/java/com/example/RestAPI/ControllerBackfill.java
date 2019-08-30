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
@RequestMapping("/backfillStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerBackfill {
    private restRepositoryBackfillStatus backfillRepository;
    public ControllerBackfill(restRepositoryBackfillStatus backfillRepository) {
        this.backfillRepository = backfillRepository;
    }
    @GetMapping
    public List<BackfillStatus> getList() {
        return (List<BackfillStatus>) backfillRepository.findAll();
    }
    @PostMapping
    public BackfillStatus addUser(@RequestBody BackfillStatus backfillstatus) {
        return backfillRepository.save(backfillstatus);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	backfillRepository.deleteById(id);
    }
}

