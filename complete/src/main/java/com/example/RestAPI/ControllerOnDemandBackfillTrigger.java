package com.example.RestAPI;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.persistence.NamedQuery;

import org.json.JSONException;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/onDemandBackfillControl")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerOnDemandBackfillTrigger {
    private restRepositoryOnDemandBackfillTrigger onDemandBackfillTriggerRepository;
    public ControllerOnDemandBackfillTrigger(restRepositoryOnDemandBackfillTrigger onDemandBackfillTriggerRepository) {
        this.onDemandBackfillTriggerRepository = onDemandBackfillTriggerRepository;
    }
    @GetMapping
    public List<OnDemandBackfillTrigger> getList() {
        return (List<OnDemandBackfillTrigger>) onDemandBackfillTriggerRepository.findAll(sortByIdAsc());
    }
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "id");
    }
    @PostMapping
    public OnDemandBackfillTrigger addUser(@RequestBody OnDemandBackfillTrigger onDemandBackfillTrigger) throws SQLException, ParseException, JSONException, InterruptedException {
       
    	return onDemandBackfillTriggerRepository.save(onDemandBackfillTrigger);
    }
    @PutMapping("/{id}")
    public OnDemandBackfillTrigger updateUser(@PathVariable("id") Integer id, @RequestBody OnDemandBackfillTrigger onDemandBackfillTrigger) throws SQLException, ParseException, JSONException, InterruptedException
    {
    	 DatabaseConnection dbCon = new DatabaseConnection();
         dbCon.setUp();
         List<String> result = dbCon.getBackfillDownsamplingForSpecificPeriodInput();
         String start_time = result.get(0);
         String end_time = result.get(1);
         String interval = result.get(2);
         String Id = result.get(3);
         BackfillDownsamplingForSpecificPeriod downsampling = new BackfillDownsamplingForSpecificPeriod();
         downsampling.backfillDownsampling(interval, start_time, end_time);
         dbCon.updateBackfillDownsamplingForSpecificPeriodStatus(Id,start_time,end_time);
    	return onDemandBackfillTriggerRepository.save(onDemandBackfillTrigger);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id
    ) {
    	onDemandBackfillTriggerRepository.deleteById(id);
    }
}

