package com.example.RestAPI;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import mqtt.Mqtt_Application.SendToMqttBroker;;
public class SendMqttMessage {
    public void sendMqttMessageToBroker(String errorHappenTime, Map<String,List<String>> missings) throws JSONException, InterruptedException {
    	for(String key: missings.keySet()) {
    		JSONObject obj = new JSONObject();
            JSONArray tsArray = new JSONArray();
            for(int i=0; i<missings.get(key).size(); i++) {
            	JSONObject timestamp= new JSONObject();
            	timestamp.put("equipmentName", missings.get(key).get(i));
                timestamp.put("timestamp",errorHappenTime);
                timestamp.put("status","completed");
                tsArray.put(timestamp);
            }
            obj.put("projectId", key);
            obj.put("timestamps", tsArray);
            SendToMqttBroker sendtobroker = new SendToMqttBroker();
            sendtobroker.sendHoleInfoToMqttBroker(obj.toString());;
    
    	}
    	
    }
}
