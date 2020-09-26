package com.gft.cs;

import io.restassured.path.json.JsonPath;

public class ReuseableMethods {
	
	public static JsonPath rawToJson(String response){
		{
			JsonPath js=new JsonPath(response);
			return js;
		}
	}
	
	
	public static String getAddPlaceRequest(double lat, double lng, int accuracy, String name, String phone_Number, String address
			,String website,String language)
	{
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": \""+lat+"\",\r\n" + 
				"    \"lng\": \""+lng+"\"\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": "+accuracy+",\r\n" + 
				"  \"name\": \""+name+"\",\r\n" + 
				"  \"phone_number\": \""+phone_Number+"\",\r\n" + 
				"  \"address\": \""+address+"\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \""+website+"\",\r\n" + 
				"  \"language\": \""+language+"\"\r\n" + 
				"}\r\n" + 
				"";
	}

}
