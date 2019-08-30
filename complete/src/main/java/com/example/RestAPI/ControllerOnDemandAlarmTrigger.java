package com.example.RestAPI;
import java.sql.SQLException;
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

import mqtt.Mqtt_Application.SendToMqttBroker;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/onDemandAlarmControl")
@CrossOrigin(origins= {"http://localhost:3000","http://localhost:3001","http://localhost:8080"})
public class ControllerOnDemandAlarmTrigger {
    private restRepositoryOnDemandAlarmTrigger onDemandAlarmStatusRepository;
    public ControllerOnDemandAlarmTrigger(restRepositoryOnDemandAlarmTrigger onDemandAlarmStatusRepository) {
        this.onDemandAlarmStatusRepository = onDemandAlarmStatusRepository;
    }
    @GetMapping
    public List<OnDemandAlarmTrigger> getList() {
        return (List<OnDemandAlarmTrigger>) onDemandAlarmStatusRepository.findAll(sortByIdAsc());
    }
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.DESC, "id");
    }
    @PostMapping
    public OnDemandAlarmTrigger addUser(@RequestBody OnDemandAlarmTrigger onDemandAlarmStatus) throws JSONException, SQLException, InterruptedException {
        return onDemandAlarmStatusRepository.save(onDemandAlarmStatus);
    }
    @PutMapping("/{id}")
    public OnDemandAlarmTrigger updateUser(@PathVariable("id") Integer id, @RequestBody OnDemandAlarmTrigger onDemandAlarmTrigger) throws JSONException, SQLException, InterruptedException
    {
    	AlarmForSpecificTime alarmforspecifictime = new AlarmForSpecificTime();
    	alarmforspecifictime.sendAlarmForSpecificTime();
    	return onDemandAlarmStatusRepository.save(onDemandAlarmTrigger);
    }
    @DeleteMapping(value = "/{id}")
    public void delUser(@PathVariable("id") Integer id
    ) {
    	onDemandAlarmStatusRepository.deleteById(id);
    }
}

