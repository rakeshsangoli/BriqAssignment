package com.briq.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.briq.assignment1.CityInfo;
import com.briq.assignment2.RestEndPointInfo;
import com.vocera.abx.configuration.ConfigOperation;
/**
 * 
 * @author rsangoli
 *
 */
public class WriteOutput {
	//Write map data point to csv
	
	public static LinkedHashMap<String, String> readCityInfoOutput() throws IOException{
		//Load properies
		Map properties=ConfigOperation.getProperties();
		//Data structure to maintain all information from csv file in order
		LinkedHashMap<String, String> cityInfo = new LinkedHashMap<String, String>(); 
		try  
		{   //Read existing data from csv file
			File file = new File((String) properties.get("cityinfoutput"));   
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
			int count=1;
			//Iterate each values and store into linkedhashmap
			while (itr.hasNext())                 
			{  
				Row row = itr.next();  
				Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
				String Key=cellIterator.next().getStringCellValue();
				String Value="";
				while (cellIterator.hasNext())   
				{  
					Cell cell = cellIterator.next();  
					switch (cell.getCellType())               
					{  
					case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
						Value+=cell.getStringCellValue()+"#";
						//System.out.print(cell.getStringCellValue() + "\t\t\t");  
						break;  
					default:  
					}  
				} 
				cityInfo.put(Key, Value);  
			}  
			return cityInfo;
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
			return cityInfo;
		}  
	}
	
	public static void writeCityInfoResult(ArrayList<CityInfo> Data) throws InvalidFormatException, FileNotFoundException, IOException{
		//Load properies
		Map properties=ConfigOperation.getProperties();
		Workbook workbook = WorkbookFactory.create(new FileInputStream((String) properties.get("cityinfoutput")));

        //Create a blank sheet
		Sheet sheet = workbook.getSheet("Data");
          
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        
        for (int i = 0; i < Data.size(); i++) {
        	 data.put(String.valueOf(i+1), new Object[] {Data.get(i).getTitle(),Data.get(i).getLocation(),Data.get(i).getLatitude(),Data.get(i).getProjectName(),Data.get(i).getDescription()
        			 ,Data.get(i).getDeveloperOrOwner(),Data.get(i).getCost(),Data.get(i).getStatus(),Data.get(i).getEstimatedCompletion(),Data.get(i).getUrlToStory()});
		}
         Set<String> keyset = data.keySet();
         int lastRow=sheet.getLastRowNum();
         System.out.println(lastRow);
        for (String key : keyset)
        {
            Row row = sheet.createRow(++lastRow);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File((String) properties.get("cityinfoutput")));
            workbook.write(out);
            out.close();
            System.out.println("City information updated successfully!");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	//Write map data point to csv
		public static LinkedHashMap<String, String> readRestEndPointInfoOutput() throws IOException{
			//Load properies
			Map properties=ConfigOperation.getProperties();
			//Data structure to maintain all information from csv file in order
			LinkedHashMap<String, String> RestEndPointInfo = new LinkedHashMap<String, String>(); 
			try  
			{   //Read existing data from csv file
				File file = new File((String) properties.get("restendpointoutput"));   
				FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
				//creating Workbook instance that refers to .xlsx file  
				XSSFWorkbook wb = new XSSFWorkbook(fis);   
				XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
				Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
				int count=1;
				//Iterate each values and store into linkedhashmap
				while (itr.hasNext())                 
				{  
					Row row = itr.next();  
					Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
					String Key=cellIterator.next().getStringCellValue();
					String Value="";
					while (cellIterator.hasNext())   
					{  
						Cell cell = cellIterator.next();  
						switch (cell.getCellType())               
						{  
						case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
							Value+=cell.getStringCellValue()+"#";
							//System.out.print(cell.getStringCellValue() + "\t\t\t");  
							break;  
						default:  
						}  
					} 
					RestEndPointInfo .put(Key, Value);  
				}  
				return RestEndPointInfo;
			}  
			catch(Exception e)  
			{  
				e.printStackTrace();  
				return RestEndPointInfo;
			}  
		}
	public static void writeRestEndPointInfoResult(ArrayList<RestEndPointInfo> Data) throws InvalidFormatException, FileNotFoundException, IOException{
		//Load properies
		Map properties=ConfigOperation.getProperties();
		Workbook workbook = WorkbookFactory.create(new FileInputStream((String) properties.get("restendpointoutput")));
        //Create a blank sheet
		Sheet sheet = workbook.getSheet("Data");
        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        for (int i = 0; i < Data.size(); i++) {
        	 data.put(String.valueOf(i+1), new Object[] {Data.get(i).getRecord_id(),
        			 Data.get(i).getComputed_region_rxqg_mtj9(),
        			 Data.get(i).getLatitude(),
        			 Data.get(i).getHuman_address(),
        			 Data.get(i).getNeeds_recoding(),
        			 Data.get(i).getLongitude(),
        			 Data.get(i).getFiled_date(),
        			 Data.get(i).getZipcode(),
        			 Data.get(i).getStreet_number(),
        			 Data.get(i).getComputed_region_ajp5_b2md(),
        			 Data.get(i).getExisting_units(),
        			 Data.get(i).getExisting_use(),
        			 Data.get(i).getComputed_region_yftq_j783(),
        			 Data.get(i).getComputed_region_bh8s_q3mv(),
        			 Data.get(i).getProposed_construction_type(),
        			 Data.get(i).getComputed_region_uruc_drv6(),
        			 Data.get(i).getBlock(),
        			 Data.get(i).getComputed_region_jx4q_fizf(),
        			 Data.get(i).getPermit_type_definition(),
        			 Data.get(i).getComputed_region_qgnn_b9vv(),
        			 Data.get(i).getNeighborhoods_analysis_boundaries(),
        			 Data.get(i).getSupervisor_district(),
        			 Data.get(i).getPlansets(),
        			 Data.get(i).getEstimated_cost(),
        			 Data.get(i).getExisting_construction_type(),
        			 Data.get(i).getExisting_construction_type_description(),
        			 Data.get(i).getDescription(),
        			 Data.get(i).getProposed_use(),
        			 Data.get(i).getRevised_cost(),
        			 Data.get(i).getPermit_creation_date(),
        			 Data.get(i).getStreet_name(),
        			 Data.get(i).getPermit_number(),
        			 Data.get(i).getStatus_date(),
        			 Data.get(i).getStatus(),
        			 Data.get(i).getNumber_of_existing_stories(),
        			 Data.get(i).getComputed_region_26cr_cadq(),
        			 Data.get(i).getLot(),
        			 Data.get(i).getProposed_units(),
        			 Data.get(i).getUnit(),
        			 Data.get(i).getNumber_of_proposed_stories(),
        			 Data.get(i).getComputed_region_6qbp_sg9q(),
        			 Data.get(i).getPermit_type()
        			 
        			 });
		}
         Set<String> keyset = data.keySet();
         int lastRow=sheet.getLastRowNum();
         System.out.println(lastRow);
        for (String key : keyset)
        {
            Row row = sheet.createRow(++lastRow);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File((String) properties.get("restendpointoutput")));
            workbook.write(out);
            out.close();
            System.out.println("Rest end point information updated successfully!");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	
}

