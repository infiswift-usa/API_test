package com.example.RestAPI;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import mqtt.Mqtt_Application.SendToMqttBroker;

public class AlarmForSpecificTime {
      public void sendAlarmForSpecificTime() throws JSONException, SQLException, InterruptedException {
    	  Date date = new Date();
    	  Calendar cal = Calendar.getInstance();
    	  cal.setTime(date);
    	  cal.add(Calendar.HOUR, +16);
    	  Date JPtime = cal.getTime();
    	
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	  
    	  JSONObject urlParameters = new JSONObject();
    	  urlParameters.put("timestamp",sdf.format(JPtime));
    	  urlParameters.put("DCA","0.00000000");
    	  urlParameters.put("PhVphA","0.00000000");
    	  urlParameters.put("W",0);
    	  urlParameters.put("DCV",100000);
    	  urlParameters.put("DCW",0);
    	  urlParameters.put("A","0.00000000");
    	  urlParameters.put("WH",72924000);
    	  System.out.println(urlParameters);
    	  
    	  SendToMqttBroker sendtobroker = new SendToMqttBroker();
    	  sendtobroker.sendAlarmInfoToMqttBroker(urlParameters.toString());
    	  
      }
}
