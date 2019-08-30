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
@RequestMapping("/APIInverterInsertStatus")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerAPIInverterInsert {
    private restRepositoryAPIInverterInsert APIInverterInsertRepository;
    public ControllerAPIInverterInsert(restRepositoryAPIInverterInsert APIInverterInsertRepository) {
        this.APIInverterInsertRepository = APIInverterInsertRepository;
    }
    @GetMapping
    public List<ApiInverterInsertStatus> getList() {
        return (List<ApiInverterInsertStatus>) APIInverterInsertRepository.findAll();
    }
    @PostMapping
    public ApiInverterInsertStatus addUser(@RequestBody ApiInverterInsertStatus ApiInverterInsertstatus) {
        return APIInverterInsertRepository.save(ApiInverterInsertstatus);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id) {
    	APIInverterInsertRepository.deleteById(id);
    }
}

