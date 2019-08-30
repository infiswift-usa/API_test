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
@RequestMapping("/APIGetGraphDataStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerAPIGetGraphData {
    private restRepositoryAPIGetGraphData APIGetGraphDataRepository;
    public ControllerAPIGetGraphData(restRepositoryAPIGetGraphData APIGetGraphDataRepository) {
        this.APIGetGraphDataRepository = APIGetGraphDataRepository;
    }
    @GetMapping
    public List<ApiGetGraphDataStatus> getList() {
        return (List<ApiGetGraphDataStatus>) APIGetGraphDataRepository.findAll();
    }
    @PostMapping
    public ApiGetGraphDataStatus addUser(@RequestBody ApiGetGraphDataStatus ApiGetGraphDatastatus) {
        return APIGetGraphDataRepository.save(ApiGetGraphDatastatus);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	APIGetGraphDataRepository.deleteById(id);
    }
}

