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
@RequestMapping("/onDemandAlarm")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerOnDemandAlarm {
    private restRepositoryOnDemandAlarm onDemandAlarmRepository;
    public ControllerOnDemandAlarm(restRepositoryOnDemandAlarm onDemandAlarmRepository) {
        this.onDemandAlarmRepository = onDemandAlarmRepository;
    }
    @GetMapping
    public List<OnDemandAlarm> getList() {
        return (List<OnDemandAlarm>) onDemandAlarmRepository.findAll(sortByIdAsc());
    }
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "id");
    }
    @PostMapping
    public OnDemandAlarm addUser(@RequestBody OnDemandAlarm onDemandAlarm) {
    	
        return onDemandAlarmRepository.save(onDemandAlarm);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id
    ) {
    	onDemandAlarmRepository.deleteById(id);
    }
}

