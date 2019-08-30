package com.example.RestAPI;
import java.util.List;

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
@RequestMapping("/backfillFailureStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerBackfillFailure {
    private restRepositoryBackfillFailure backfillFailureRepository;
    public ControllerBackfillFailure(restRepositoryBackfillFailure backfillFailureRepository) {
        this.backfillFailureRepository = backfillFailureRepository;
    }
    @GetMapping
    public List<BackfillFailure> getList() {
        return (List<BackfillFailure>) backfillFailureRepository.findAll(sortByIdAsc());
    }
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "id");
    }
    @PostMapping
    public BackfillFailure addUser(@RequestBody BackfillFailure backfillstatus) {
        return backfillFailureRepository.save(backfillstatus);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	backfillFailureRepository.deleteById(id);
    }
}

