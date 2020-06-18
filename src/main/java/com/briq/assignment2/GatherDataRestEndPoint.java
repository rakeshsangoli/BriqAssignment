package com.briq.assignment2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.briq.output.WriteOutput;
import com.vocera.abx.configuration.ConfigOperation;

/**
 * 
 * @author rsangoli
 *
 */
public class GatherDataRestEndPoint {
	
	public static void main(String[] args) throws InvalidFormatException, FileNotFoundException, IOException {
		//Load properies
		Map properties=ConfigOperation.getProperties();
		//Prepare get parameter
		Map<String,String> param = new HashMap();	
		String URI=(String) properties.get("restendpoint");
		String Header="Accept:application/json,Content-type:application/json";
		String Body="";
		param.put("uri", URI);
		param.put("header", Header);
		param.put("body",Body);
		//Create Get Rest object
		Get get=new Get(param);
		//Execute get request
		get.execute();
		//Get response of request
		String Response=get.getResponse();
		ParseJsonResponse jsonExp= new ParseJsonResponse();
		//Read existing data from csv
		 LinkedHashMap<String, String> RestEndPointInfo =  new LinkedHashMap<String, String>(); 
		 //To read unique titles for first time read
		 LinkedHashMap<String, String> RecordId =  new LinkedHashMap<String, String>(); 
		 //Read existing data from csv file
		 RestEndPointInfo=WriteOutput.readRestEndPointInfoOutput();
		 System.out.println("Already records present"+RestEndPointInfo.size());
		 
		//Hold all the information from rest point
		ArrayList<RestEndPointInfo> restEndPointInfo = new ArrayList<RestEndPointInfo>();
		//Read one by one information from rest point.
		for (int i = 0;; i++) {
			if(!jsonExp.isResultForPath(Response,"$["+i+"]"))
				break;
			String computed_region_rxqg_mtj9=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_rxqg_mtj9");
			String latitude=jsonExp.parseJson(Response,"$["+i+"].location.latitude");
			String human_address=jsonExp.parseJson(Response,"$["+i+"].location.human_address");
			String needs_recoding=jsonExp.parseJson(Response,"$["+i+"].location.needs_recoding");
			String longitude=jsonExp.parseJson(Response,"$["+i+"].location.longitude");
			String filed_date=jsonExp.parseJson(Response,"$["+i+"].filed_date");
			String record_id=jsonExp.parseJson(Response,"$["+i+"].record_id");
			String zipcode=jsonExp.parseJson(Response,"$["+i+"].zipcode");
			String street_number=jsonExp.parseJson(Response,"$["+i+"].street_number");
			String computed_region_ajp5_b2md=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_ajp5_b2md");
			String existing_units=jsonExp.parseJson(Response,"$["+i+"].existing_units");
			String existing_use=jsonExp.parseJson(Response,"$["+i+"].existing_use");
			String computed_region_yftq_j783=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_yftq_j783");
			String computed_region_bh8s_q3mv=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_bh8s_q3mv");
			String proposed_construction_type=jsonExp.parseJson(Response,"$["+i+"].proposed_construction_type");
			String computed_region_uruc_drv6=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_uruc_drv6");
			String block=jsonExp.parseJson(Response,"$["+i+"].block");
			String computed_region_jx4q_fizf=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_jx4q_fizf");
			String permit_type_definition=jsonExp.parseJson(Response,"$["+i+"].permit_type_definition");
			String computed_region_qgnn_b9vv=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_qgnn_b9vv");
			String neighborhoods_analysis_boundaries=jsonExp.parseJson(Response,"$["+i+"].neighborhoods_analysis_boundaries");
			String supervisor_district=jsonExp.parseJson(Response,"$["+i+"].supervisor_district");
			String plansets=jsonExp.parseJson(Response,"$["+i+"].plansets");
			String estimated_cost=jsonExp.parseJson(Response,"$["+i+"].estimated_cost");
			String existing_construction_type=jsonExp.parseJson(Response,"$["+i+"].existing_construction_type");
			String existing_construction_type_description=jsonExp.parseJson(Response,"$["+i+"].existing_construction_type_description");
			String description=jsonExp.parseJson(Response,"$["+i+"].description");
			String proposed_construction_type_description=jsonExp.parseJson(Response,"$["+i+"].proposed_construction_type_description");
			String proposed_use=jsonExp.parseJson(Response,"$["+i+"].proposed_use");
			String revised_cost=jsonExp.parseJson(Response,"$["+i+"].revised_cost");
			String permit_creation_date=jsonExp.parseJson(Response,"$["+i+"].permit_creation_date");
			String street_name=jsonExp.parseJson(Response,"$["+i+"].street_name");
			String permit_number=jsonExp.parseJson(Response,"$["+i+"].permit_number");
			String status_date=jsonExp.parseJson(Response,"$["+i+"].status_date");
			String status=jsonExp.parseJson(Response,"$["+i+"].status");
			String number_of_existing_stories=jsonExp.parseJson(Response,"$["+i+"].number_of_existing_stories");
			String computed_region_26cr_cadq=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_26cr_cadq");
			String lot=jsonExp.parseJson(Response,"$["+i+"].lot");
			String proposed_units=jsonExp.parseJson(Response,"$["+i+"].proposed_units");
			String unit=jsonExp.parseJson(Response,"$["+i+"].unit");
			String number_of_proposed_stories=jsonExp.parseJson(Response,"$["+i+"].number_of_proposed_stories");
			String computed_region_6qbp_sg9q=jsonExp.parseJson(Response,"$["+i+"].:@computed_region_6qbp_sg9q");
			String permit_type=jsonExp.parseJson(Response,"$["+i+"].permit_type");
			if(!RestEndPointInfo.containsKey(record_id))
				if(!RecordId.containsKey(record_id)){
					restEndPointInfo.add(new RestEndPointInfo(computed_region_rxqg_mtj9, 
							latitude,
							human_address, 
							needs_recoding, longitude,
							filed_date, record_id, 
							zipcode, 
							street_number, 
							computed_region_ajp5_b2md, 
							existing_units, 
							existing_use, 
							computed_region_yftq_j783, 
							computed_region_bh8s_q3mv, 
							proposed_construction_type, 
							computed_region_uruc_drv6, 
							block, 
							computed_region_jx4q_fizf,
							permit_type_definition, 
							computed_region_qgnn_b9vv, 
							neighborhoods_analysis_boundaries,
							supervisor_district, 
							plansets, 
							estimated_cost, 
							existing_construction_type, 
							existing_construction_type_description, 
							description, 
							proposed_construction_type_description, 
							proposed_use, 
							revised_cost, 
							permit_creation_date, 
							street_name, 
							permit_number, 
							status_date, 
							status, 
							number_of_existing_stories, 
							computed_region_26cr_cadq, 
							lot, 
							proposed_units, 
							unit, 
							number_of_proposed_stories, 
							computed_region_6qbp_sg9q, 
							permit_type));
					RecordId.put(record_id, record_id);
				}
		}
		System.out.println("New records found="+restEndPointInfo.size());
		//Write information to csv file
		WriteOutput.writeRestEndPointInfoResult(restEndPointInfo);
		
		
	}

}
