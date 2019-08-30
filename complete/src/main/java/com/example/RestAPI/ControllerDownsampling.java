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
@RequestMapping("/downsamplingError")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerDownsampling {
    private restRepositoryDownsamplingError downsamplingRepository;
    public ControllerDownsampling(restRepositoryDownsamplingError downsamplingRepository) {
        this.downsamplingRepository = downsamplingRepository;
    }
    @GetMapping
    public List<DownsamplingError> getList() {
        return (List<DownsamplingError>) downsamplingRepository.findAll(sortByIdAsc());
    }
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "id");
    }
    @PostMapping
    public DownsamplingError addUser(@RequestBody DownsamplingError downsamplingerror) {
        return downsamplingRepository.save(downsamplingerror);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id
    ) {
    	downsamplingRepository.deleteById(id);
    }
}
