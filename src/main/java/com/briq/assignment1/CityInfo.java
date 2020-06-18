package com.briq.assignment1;
/**
 * City info pojo
 * @author rsangoli
 *
 */
public class CityInfo {
	private String title;
	private String location;
	private String latitude;
	private String projectName;
	private String description;
	private String developerOrOwner;
	private String cost;
	private String status;
	private String estimatedCompletion;
	private String urlToStory;
	
	public CityInfo(String Title, String Location, String Latitude, String ProjectName, String Description, String DeveloperOrOwner, String Cost, String Status, String EstimatedCompletion, String UrlToStory){
		this.title=Title;
		this.location=Location;
		this.latitude=Latitude;
		this.projectName=ProjectName;
		this.description=Description;
		this.developerOrOwner=DeveloperOrOwner;
		this.cost=Cost;
		this.status=Status;
		this.estimatedCompletion=EstimatedCompletion;
		this.urlToStory=UrlToStory;
	}
	public String getTitle() {
		return title;
	}
	public String getLocation() {
		return location;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getProjectName() {
		return projectName;
	}
	public String getDescription() {
		return description;
	}
	public String getDeveloperOrOwner() {
		return developerOrOwner;
	}
	public String getCost() {
		return cost;
	}
	public String getStatus() {
		return status;
	}
	public String getEstimatedCompletion() {
		return estimatedCompletion;
	}
	public String getUrlToStory() {
		return urlToStory;
	}
}
