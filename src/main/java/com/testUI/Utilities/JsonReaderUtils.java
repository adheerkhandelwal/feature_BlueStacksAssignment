package com.testUI.Utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.LocationObjects;
import io.restassured.response.Response;

public class JsonReaderUtils {
	
	
	
	
	public static JSONObject getJsonObject() throws IOException, ParseException
	{
		 //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
		
		try {
			FileReader file = new FileReader(DriverFactory.getJsonFilePath());
			
			JSONObject object = (JSONObject)jsonParser.parse(file);
			
			System.out.println(object.toJSONString());
			
			return object;
			
		} catch (FileNotFoundException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
		
		
		
  public static LocationObjects convertToObject() throws JsonMappingException, JsonProcessingException, IOException, ParseException
  {
	  
	      
	  ObjectMapper mapper = new ObjectMapper();
	  LocationObjects loc = mapper.readValue(getJsonObject().toJSONString(), LocationObjects.class);
  
	  return loc;
  }
	
  
  public static String getJSONResponse(Response response,String key) throws ParseException
  {

          JSONParser parser = new JSONParser();
	      JSONObject object = (JSONObject)parser.parse(response.asString());
	      System.out.println(object.get(key).toString());
	      return object.get(key).toString();
  }
  
  public static String getJSONObjectResponse(Response response,String parent, String child) throws ParseException
  {

          JSONParser parser = new JSONParser();
	      JSONObject object = (JSONObject)parser.parse(response.asString());
	      JSONObject ob = (JSONObject) object.get(parent);
	      System.out.println(ob.get(child).toString());
	      return ob.get(child).toString();
  }
  
}
	
	

