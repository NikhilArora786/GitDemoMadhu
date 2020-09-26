package com.gf.sft.PojoClasses;

import com.gft.cs.Location;
public class GetPlaceResponse {
	
	private Location location;
	private String accuracy;
	private String name;
	private String phone_number;
	private String address;
	private String types;
	private String website;
	private String language;
	
	public Location getLocation() {
		return location;
	}
	public String getAccuracy() {
		return accuracy;
	}
	public String getName() {
		return name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public String getAddress() {
		return address;
	}
	public String getTypes() {
		return types;
	}
	public String getWebsite() {
		return website;
	}
	public String getLanguage() {
		return language;
	}


}
