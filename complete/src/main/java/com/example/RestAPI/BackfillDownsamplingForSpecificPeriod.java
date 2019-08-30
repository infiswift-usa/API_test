package com.example.RestAPI;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;

public class BackfillDownsamplingForSpecificPeriod {
     public void backfillDownsampling(String interval,String startTime,String endTime) throws ParseException, SQLException, JSONException, InterruptedException {
    	
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 DatabaseConnection dbCon = new DatabaseConnection();
 	     dbCon.setUp();
    	 while(sdf.parse(startTime).before(sdf.parse(endTime))) {
    
    		 Date start_time = sdf.parse(startTime);
    		 Long time = start_time.getTime();
    		
    		 if(interval.equals("5min")) {
    			 time += (5 * 60 * 1000);
    		 }else if(interval.equals("15min")) {
    			 time += (15 * 60 * 1000);
    		 }else if(interval.equals("1hr")) {
    			 time += (60 * 60 * 1000);
    		 }else if(interval.equals("4hr")){
    			 time += (4 * 60 * 60 * 1000);
    		 }
    		
    		 String sub_endTime = sdf.format(time);  
    		 
    		 System.out.println("startTime: " + startTime);
    		 System.out.println("endTime: " + sub_endTime);
    		 
    		 backfillDownsamplingHelper(interval, startTime, sub_endTime);
    		 startTime = sub_endTime;
    	 }
    	 dbCon.tearDownBackfill();
    	 dbCon.tearDownIntervalCheck();
 
     }
     private void backfillDownsamplingHelper(String interval, String sub_startTime, String sub_endTime) throws SQLException, JSONException, InterruptedException {
    	    DatabaseConnection dbCon = new DatabaseConnection();
    	   	Map<String,List<String>> missings = new HashMap<>();
     		dbCon.findProjects();
     		Map<String,List<String>> project_equip_expected = dbCon.findProjectEquipmentExpectedForSpecificTimePeriod(interval,sub_startTime,sub_endTime);
     		Map<String,List<String>> project_equip_actual = dbCon.findProjectEquipmentActualForSpecificTimePeriod(interval,sub_startTime,sub_endTime);

     		for(String key : project_equip_expected.keySet()) {
     			if( project_equip_actual.containsKey(key) && project_equip_actual.get(key).size() == project_equip_expected.get(key).size()) {
     				Collections.sort(project_equip_actual.get(key));
     				Collections.sort(project_equip_expected.get(key));
     				for(int i=0; i<project_equip_expected.get(key).size(); i++) {
     					String element_expected = project_equip_expected.get(key).get(i);
     					String element_actual = project_equip_actual.get(key).get(i);
     					String[] equipAndNum_actual = element_actual.split(" ");
     					String[] equipAndNum_expected = element_expected.split(" ");
     					if(equipAndNum_actual[0].equals(equipAndNum_expected[0]) && equipAndNum_actual[1].equals(equipAndNum_expected[1])) {
     						continue;
     					}else {
     						if(!missings.containsKey(key)) {
     							missings.put(key,new ArrayList<>());
     						}
     						missings.get(key).add(equipAndNum_expected[0]);
     					}
     				}
     			}else {
     				if(!missings.containsKey(key)) {
     					missings.put(key,new ArrayList<>());
     				}
     				if(project_equip_actual.containsKey(key)) {
     				   Collections.sort(project_equip_actual.get(key));
     				}
     				Collections.sort(project_equip_expected.get(key));
     				for(int i = project_equip_expected.get(key).size()-1; i>=0; i--) {
     					if(project_equip_actual.containsKey(key) && project_equip_actual.get(key).size() < i+1) {
     						String element_expected = project_equip_expected.get(key).get(i);
     						String[] equipAndNum_expected = element_expected.split(" ");
     						missings.get(key).add(equipAndNum_expected[0]);
     					}else {
     						String element_expected = project_equip_expected.get(key).get(i);
     						String[] equipAndNum_expected = element_expected.split(" ");
     						String element_actual="";
     						String[] equipAndNum_actual = new String[2];
     						if(project_equip_actual.containsKey(key)) {
     							element_actual = project_equip_actual.get(key).get(i);
     							equipAndNum_actual = element_actual.split(" ");
     						}
     						
     						if(equipAndNum_actual[0]!=null && equipAndNum_actual[1]!=null && equipAndNum_actual[0].equals(equipAndNum_expected[0]) && equipAndNum_actual[1].equals(equipAndNum_expected[1])) {
     							continue;
     						}else {
     							missings.get(key).add(equipAndNum_expected[0]);
     						}
     					}
     				}
     			}
           }
     		if(missings.size() != 0) {
    			System.out.println("BackFilling between "+ sub_startTime +" and "+sub_endTime);
    			String errorHappenTime = dbCon.findErrorHappenTimeForSpecificTimePeriod(interval, sub_startTime, sub_endTime);
    			SendMqttMessage sendmqttmessage = new SendMqttMessage();
    			sendmqttmessage.sendMqttMessageToBroker(errorHappenTime, missings);
    			
    		}
    		
    	 
     }
}
