/**
* The class includes all database related functions
* @version 1.0
* @author Haiyu Yu
*/

package com.example.RestAPI;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatabaseConnection {

	private final static int LAG_INTERVAL = 31 * 60 * 1000;
	private final static int TIME_DIFF = 16 * 60 * 60 * 1000;
	private final static int ONE_DAY = 24 * 60 * 60 * 1000;
	private final static int FIVE_MINUTES = 5 * 60 * 1000;
	private final static int FIFTEEN_MINUTES = 15 * 60 * 1000;
	private final static int ONE_HOUR = 60 * 60 * 1000;
	private final static int FOUR_HOURS = 4 * 60 * 60 * 1000;
	
	private Map<String,Integer> interval_number = new HashMap<>();
	private Map<String,Integer> divide_number = new HashMap<>();
	private Map<String, String> interval_table = new HashMap<>();
	private Map<String, Integer> interval_lag = new HashMap<>();
	
	DatabaseConnection(){
		
		this.interval_number.put("5min",FIVE_MINUTES);
		this.interval_number.put("15min", FIFTEEN_MINUTES);
		this.interval_number.put("1hr", ONE_HOUR);
		this.interval_number.put("4hr", FOUR_HOURS);
		this.interval_number.put("login", FIVE_MINUTES);
		this.interval_number.put("backfill", ONE_DAY);
		
		this.divide_number.put("5min", 1);
		this.divide_number.put("15min", 3);
		this.divide_number.put("1hr", 12);
		this.divide_number.put("4hr", 48);
		
		this.interval_table.put("5min", "five_min_status");
		this.interval_table.put("15min", "fifteen_min_status");
		this.interval_table.put("1hr", "one_hour_status");
		this.interval_table.put("4hr", "four_hour_status");
		this.interval_table.put("login", "login_status");
		this.interval_table.put("backfill", "backfill_status");
		this.interval_table.put("inverterInsert", "api_inverter_insert_status");
		this.interval_table.put("getGraphData", "api_get_graph_data_status");
		
		this.interval_lag.put("5min", LAG_INTERVAL);
		this.interval_lag.put("15min", LAG_INTERVAL);
		this.interval_lag.put("1hr", LAG_INTERVAL);
		this.interval_lag.put("4hr", LAG_INTERVAL);
		this.interval_lag.put("login", 0);
		this.interval_lag.put("backfill", 0);
		
	}
	
	private List<String> project_list = new ArrayList<>();
	private Map<String, String> project_id_name = new HashMap<>();
	
	private Map<String, List<String>> project_equip_expected = new HashMap<>();
	private Map<String, List<String>> project_equip_actual = new HashMap<>();
	
	//get current US time
	private java.util.Date cur_time_util = new java.util.Date();
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL_1 = "jdbc:mysql://preview-memsql.infiswift.tech:3306/swiftpv?characterEncoding=utf8&useSSL=false&serverTimezone=JST&rewriteBatchedStatements=true";
    static final String DB_URL_2 = "jdbc:mysql://preview-memsql.infiswift.tech:3306/infidb?characterEncoding=utf8&useSSL=false&serverTimezone=JST&rewriteBatchedStatements=true";
	
    //test for new DB
    static final String DB_URL_3 = "jdbc:mysql://preview-memsql.infiswift.tech:3306/infidb_test?characterEncoding=utf8&useSSL=false&serverTimezone=JST&rewriteBatchedStatements=true";
    
    
	//test for new DB
    static final String PREW_DB_USER = "root";
    static final String PREW_DB_PASSWORD = "4Ka-NdV-Wkz-5wC";
    
    private static Statement stmt_1;//get projects
    private static Statement stmt_2;//get equipment expected
    private static Statement stmt_3;//get equipment number expected
    
    
    private static Statement stmt_7;//get equipment expected
    private static Statement stmt_8;//get equipment number actual
    private static Statement stmt_9;//get sunrise and sunset
    
  
    private static Statement stmt_14;
    private static Statement stmt_15;
    private static Statement stmt_16;
    
    static Connection con_1 = null;  
    static Connection con_2 = null;  
    //test for new DB
    static Connection con_3 = null;
    static Connection con_4 = null;
    static Connection con_5 = null;
    static Connection con_6 = null;
    static Connection con_7 = null;
    static Connection con_8 = null;
    static Connection con_9 = null;
    static Connection con_10 = null;
    static Connection con_11 = null;
    static Connection con_12 = null;
    static Connection con_13 = null;
    static Connection con_14 = null;
    static Connection con_15 = null;
    static Connection con_16 = null;
    
    public void setUp(){
    	
    	try{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con_1 = DriverManager.getConnection(DB_URL_1,PREW_DB_USER,PREW_DB_PASSWORD);
    		con_2 = DriverManager.getConnection(DB_URL_2,PREW_DB_USER,PREW_DB_PASSWORD);
    		//test for new DB
    		con_3 = DriverManager.getConnection(DB_URL_2,PREW_DB_USER,PREW_DB_PASSWORD);
    	
    		
    		con_7 = DriverManager.getConnection(DB_URL_2,PREW_DB_USER,PREW_DB_PASSWORD);
    		con_8 = DriverManager.getConnection(DB_URL_2,PREW_DB_USER,PREW_DB_PASSWORD);
    		con_9 = DriverManager.getConnection(DB_URL_1,PREW_DB_USER,PREW_DB_PASSWORD);
    	
    		con_14 = DriverManager.getConnection(DB_URL_2,PREW_DB_USER,PREW_DB_PASSWORD);	
    		con_15 = DriverManager.getConnection(DB_URL_3,PREW_DB_USER,PREW_DB_PASSWORD);
    		con_16 = DriverManager.getConnection(DB_URL_3,PREW_DB_USER,PREW_DB_PASSWORD);
    		
    		stmt_1 = con_1.createStatement();
    		stmt_2 = con_2.createStatement();
    		stmt_3 = con_3.createStatement();
    		//test for new DB

    		stmt_7 = con_7.createStatement();
    		stmt_8 = con_8.createStatement();
    		stmt_9 = con_9.createStatement();
 
    		stmt_14 = con_14.createStatement();
    		stmt_15 = con_15.createStatement();
    		stmt_16 = con_16.createStatement();
    	
    	  }
            catch (Exception e)
            {
                  e.printStackTrace();
            }  
    }
    /* find sunrise and sunset time of current month */
    public String[] findSunriseAndSunset() throws SQLException{
    	
	  String[] sunrise_sunset = new String[2];
	
	  	  
     DateFormat dateFormat = new SimpleDateFormat("MM");
	 Date cur_month_JP = new Date(System.currentTimeMillis() + TIME_DIFF);
 
	 String sunriseAndSunset_query = "select sunrise,sunset from tbl_project_sunrise_sunset where project_id=7 && month = '"+dateFormat.format(cur_month_JP) +"'";
	
     ResultSet sunriseAndSunset_res = stmt_9.executeQuery(sunriseAndSunset_query);
     
     while (sunriseAndSunset_res.next())
     {
        sunrise_sunset[0] = sunriseAndSunset_res.getString("sunrise");     
        sunrise_sunset[1] = sunriseAndSunset_res.getString("sunset");
  
      }
		
       return sunrise_sunset;
   }
    
    /* find all project id and save into list */
    public List<String> findProjects() throws SQLException{
    	
	String project_query = "select DISTINCT project_id from tbl_project";
	ResultSet project_res = stmt_1.executeQuery(project_query);
	
	    while (project_res.next())
	    {
	      project_list.add(project_res.getString(1));
	      String id_name_query = "select project_name from tbl_project where project_id = '"+project_res.getString(1)+"'";
	      ResultSet id_name_res = stmt_9.executeQuery(id_name_query); 
	        while(id_name_res.next()) {
	           project_id_name.put(project_res.getString(1), id_name_res.getString(1));
		     }
	     }
            
        return project_list;
    }
    
    public Map<String,List<String>> findProjectEquipmentExpectedForSpecificTimePeriod(String interval, String startTime, String endTime) throws SQLException{
      	
	     int divide_num = divide_number.get(interval);
  
		 for (int i=0; i<project_list.size(); i++) {
			 Map<String,Integer> equip_num = new HashMap<>();
	        
	         String equip_query = "select DISTINCT equipment_name from tbl_stream_NER_Admin__infiswift__tech_pvdata where project_id="+project_list.get(i)+"&& timestamp >='"+startTime+"'&& timestamp <'"+endTime+"'&& equipment_name!=\"\"";
	         ResultSet equip_res = stmt_2.executeQuery(equip_query);
	         
	         while (equip_res.next())
	         {
	          String equipment_name = equip_res.getString(1);    
	         
	          String equipNum_query = "select COUNT('"+ equipment_name +"') AS total from tbl_stream_NER_Admin__infiswift__tech_pvdata where project_id="+ project_list.get(i)+"&& timestamp >='"+startTime+"'&& timestamp <'"+endTime+"'&& equipment_name!=\"\"";
	          ResultSet equipNum_res = stmt_3.executeQuery(equipNum_query); 
			      while(equipNum_res.next()) {
			    	  equip_num.put(equipment_name, equipNum_res.getInt("total"));
			      }
	        	 
		           if(!project_equip_expected.containsKey(project_list.get(i))) {
		        	   project_equip_expected.put(project_list.get(i), new ArrayList<>());
		            }
		           project_equip_expected.get(project_list.get(i)).add(equipment_name);

	          }
	         
	         for(String project: project_equip_expected.keySet()) {
			     for(int j=0; j<project_equip_expected.get(project).size(); j++) {
			    	 for(String equip: equip_num.keySet()) {
			    		if(equip.equals(project_equip_expected.get(project).get(j))) {
			    			 String temp = project_equip_expected.get(project).get(j);
	  	   				     project_equip_expected.get(project).set(j, temp + " "+ equip_num.get(equip)/divide_num);
			    		}
			    	 }
			     }
		     }   
	         
		  }
	    		
         return project_equip_expected;
}
  /* find project equipment actual for a specific time period */
  public Map<String,List<String>> findProjectEquipmentActualForSpecificTimePeriod(String interval,String startTime,String endTime) throws SQLException{
      
  	   int interval_num = interval_number.get(interval);
  	
   
  		 for (int i=0; i<project_list.size(); i++) {
  			 Map<String,Integer> equip_num = new HashMap<>();
  			
  			 String equip_query = "select DISTINCT equipment_name from tbl_stream_NER_Admin__infiswift__tech_pvdata_"+interval+" where project_id="+project_list.get(i)+"&& timestamp >='"+startTime+"'&& timestamp <'"+endTime+"'&& equipment_name!=\"\"";
  			 ResultSet equip_res = stmt_7.executeQuery(equip_query);
		 
		         while (equip_res.next())
		         {
		         
		          String equipNum_query = "select COUNT('"+ equip_res.getString(1)+"') AS total from tbl_stream_NER_Admin__infiswift__tech_pvdata_"+interval +" where project_id="+ project_list.get(i)+"&& timestamp >='"+startTime+"'&& timestamp <'"+endTime+"'&& equipment_name!=\"\"";
		          ResultSet equipNum_res = stmt_8.executeQuery(equipNum_query);
			      
		 	      while(equipNum_res.next()) {
			    	  equip_num.put(equip_res.getString(1),equipNum_res.getInt("total"));
			      }
		     
			      if(!project_equip_actual.containsKey(project_list.get(i))) {
		         	   project_equip_actual.put(project_list.get(i), new ArrayList<>());
		            }
		              project_equip_actual.get(project_list.get(i)).add(equip_res.getString(1));
	
		          }
		          for(String project: project_equip_actual.keySet()) {
	   			     for(int j=0; j<project_equip_actual.get(project).size(); j++) {
	   			    	 for(String equip: equip_num.keySet()) {
	   			    		if(equip.equals(project_equip_actual.get(project).get(j))) {
	   			    			 String temp = project_equip_actual.get(project).get(j);
		  	   				     project_equip_actual.get(project).set(j, temp + " "+ equip_num.get(equip));
	   			    		}
	   			    	 }
	   				  
	   			     }
	   		      }   
  		  }
  		  
          return project_equip_actual;
  }
  
  /* find the error happen for specific time period*/
  public String findErrorHappenTimeForSpecificTimePeriod(String interval,String startTime,String endTime) throws SQLException {
  	String timestamp_query = "select DISTINCT timestamp from tbl_stream_NER_Admin__infiswift__tech_pvdata where timestamp >='"+startTime+"'&& timestamp <'"+endTime+"'&& equipment_name!=\"\"";
  	int interval_num = interval_number.get(interval);
  	
  	String result="";
  	ResultSet timestamp_res = stmt_14.executeQuery(timestamp_query);
      
      while (timestamp_res.next())
      {
       String timestamp = timestamp_res.getString(1);  
       String timestamp_mininute = timestamp.split(" ")[1].split(":")[1];
   
	         if(timestamp_mininute.equals("00") && interval.equals("1hr")) {
	        	 result = timestamp;
	         }else if(timestamp_mininute.equals("00") && interval.equals("4hr")) {
	        	 result = timestamp;
	         }else if((timestamp_mininute.equals("00")||timestamp_mininute.equals("15")||timestamp_mininute.equals("30")||timestamp_mininute.equals("45"))&& interval.equals("15min")) {
	        	 result = timestamp;
	         }else if((timestamp_mininute.equals("00")||timestamp_mininute.equals("05")||timestamp_mininute.equals("10")||timestamp_mininute.equals("15")||timestamp_mininute.equals("20")||timestamp_mininute.equals("25")||timestamp_mininute.equals("30")||timestamp_mininute.equals("35")||timestamp_mininute.equals("40")||timestamp_mininute.equals("45")||timestamp_mininute.equals("50")||timestamp_mininute.equals("55"))&& interval.equals("5min")) {
	        	 result = timestamp;
	         }
		  
       }
  
      return result;
  }
  
  public List<String> getBackfillDownsamplingForSpecificPeriodInput() throws SQLException {
	  String input_query = "SELECT * from on_demand_backfill ORDER BY id DESC LIMIT 1";
	  ResultSet input_res = stmt_15.executeQuery(input_query);
	  List<String> result = new ArrayList<>();
	  while (input_res.next())
	    {
		  
		  String start_time = input_res.getString("start_time");
		  String end_time = input_res.getString("end_time");
		  String interval = input_res.getString("interval_num");
		  String id = input_res.getString("id");
	      result.add(start_time);
	      result.add(end_time);
	      result.add(interval);
	      result.add(id);
	     }
	  return result;
	  
  }
  public void updateBackfillDownsamplingForSpecificPeriodStatus(String id,String start_time,String end_time) throws SQLException {
	  
	  String update_status_query = "update on_demand_backfill set status = 'done',start_time ='"+start_time +"',end_time ='"+end_time +"' where id="+id+" ";
	   
      stmt_16.executeUpdate(update_status_query);

      System.out.println("On demand backfill process has been done");
	  
  }
  
  
    public void tearDownIntervalCheck(){
           // Close DB connection
    	   try {
    		  if (con_1 != null) {
    	         con_1.close();
    	       } 
    		  if (con_2 != null) {
     	         con_2.close();
     	       } 
    		  if (con_3 != null) {
      	         con_3.close();
      	       } 
    		  if (con_7 != null) {
     	         con_7.close();
     	       } 
     		  if (con_8 != null) {
      	         con_8.close();
      	       } 
     		  if (con_9 != null) {
       	         con_9.close();
       	       } 
     		 
    	   }catch(Exception e)
    	   {
    		  e.printStackTrace();
    	   }
           
    }
    public void tearDownBackfill(){
        // Close DB connection
 	   try {
 		  if (con_4 != null) {
 	         con_4.close();
 	       } 
 		  if (con_5 != null) {
  	         con_5.close();
  	       } 
 		  if (con_6 != null) {
   	         con_6.close();
   	       } 
 		  if (con_10 != null) {
  	         con_10.close();
  	       } 
  		  if (con_11 != null) {
   	         con_11.close();
   	       } 
  		if (con_12 != null) {
  	         con_12.close();
  	       } 
  		if (con_13 != null) {
  			con_13.close();
  		}
  		if (con_14 != null) {
  			con_14.close();
  		}
  		if (con_15 != null) {
  			con_15.close();
  		}
 	   }catch(Exception e)
 	   {
 		  e.printStackTrace();
 	   }
        
 }

}   
