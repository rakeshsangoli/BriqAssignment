package com.briq.assignment1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.briq.output.WriteOutput;
import com.jayway.jsonpath.Configuration;
import com.vocera.abx.configuration.ConfigOperation;
/**
 * 
 * @author rsangoli
 *
 */
public class UpdateCities {
	public static void main(String[] args) throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException {
		//Load properties
		Map properties=ConfigOperation.getProperties();
		// System Property for Chrome Driver   
		System.setProperty("webdriver.chrome.driver", (String) properties.get("chromedriver"));  

		// Instantiate a ChromeDriver class.     
		WebDriver driver=new ChromeDriver();  

		// Launch Website  
		driver.navigate().to((String) properties.get("url"));  

		//Maximize the browser  
		driver.manage().window().maximize();  
		 //Read existing data from csv
		 LinkedHashMap<String, String> cityInfo = 
                 new LinkedHashMap<String, String>(); 
		 //To read unique titles for first time read
		 LinkedHashMap<String, String> CityTitle = 
                 new LinkedHashMap<String, String>(); 
		 //Read existing data from csv file
		 cityInfo=WriteOutput.readCityInfoOutput();
		 System.out.println(cityInfo.size());
		 //Maintain additional city info
		 ArrayList<CityInfo> arrayCityinfo = new ArrayList<CityInfo>(); 
		 //Wait for map grid load successflly
		 int wait=0;
		 while(wait<100){
			 
			 try {
				 driver.findElement(By.xpath("//*[@overflow='hidden']//*[@id='milwaukee_7663_layer']//*[@stroke='none'][1]")).isDisplayed();
				 break;
			} catch (Exception e) {
				System.out.println("exception occured on wait to load map!!");
			}
			 wait++;
			 Thread.sleep(1000);
		 }
		 System.out.println("Map loaded success, Reading all the map points!!");
		 //Read all map points information
		for (int i = 1;; i++) {
			try {
			System.out.println("\nMap point Count="+i);
					boolean status=driver.findElement(By.xpath("//*[@overflow='hidden']//*[@id='milwaukee_7663_layer']//*[@stroke='none']["+i+"]")).isDisplayed();
			if(!status)
					break;
			WebElement txtUsername = driver.findElement(By.xpath("//*[@overflow='hidden']//*[@id='milwaukee_7663_layer']//*[@stroke='none']["+i+"]"));
			//Mouse click on map points
			Actions builder = new Actions(driver);
			Action seriesOfActions = builder
				.moveToElement(txtUsername)
				.click()
				.build();
			seriesOfActions.perform() ;
			//Read all the map point informations
			String Title=driver.findElement(By.xpath("//div[@dojoattachpoint='_title']")).getText();
			String Location=driver.findElement(By.xpath("//td[text()='Location']/ancestor::tr[1]//td[2]")).getText();
			String Latitude=driver.findElement(By.xpath("//td[text()='Latitude']/ancestor::tr[1]//td[2]")).getText();
			String ProjectName=driver.findElement(By.xpath("//td[text()='Project Name']/ancestor::tr[1]//td[2]")).getText();
			String Description=driver.findElement(By.xpath("//td[text()='Description (HTML Allowed)']/ancestor::tr[1]//td[2]")).getText();
			String DeveloperOrOwner=driver.findElement(By.xpath("//td[text()='Owner/Developer']/ancestor::tr[1]//td[2]")).getText();
			String Cost=driver.findElement(By.xpath("//td[text()='Cost']/ancestor::tr[1]//td[2]")).getText();
			String Status=driver.findElement(By.xpath("//td[text()='Status']/ancestor::tr[1]//td[2]")).getText();
			String EstimatedCompletion=driver.findElement(By.xpath("//td[text()='Estimated Completion']/ancestor::tr[1]//td[2]")).getText();
			String URLToStory=driver.findElement(By.xpath("//td[text()='URL To Story']/ancestor::tr[1]//td[2]")).getText();
			//Add unique record to map point
			if(!cityInfo.containsKey(Title)){
				if(!CityTitle.containsKey(Title)){
					arrayCityinfo.add(new CityInfo(Title, Location, Latitude, ProjectName, Description, DeveloperOrOwner, Cost, Status, EstimatedCompletion, URLToStory));
					CityTitle.put(Title, Title);
				}
			}
			Thread.sleep(100);
				//Close map point popup
				driver.findElement(By.xpath("//div[@class='titleButton close']")).click();
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("Error occured on closing popup!");
//				break;
			}
			
		}
		//write all map point info to csv file
		WriteOutput.writeCityInfoResult(arrayCityinfo);
	}

}
