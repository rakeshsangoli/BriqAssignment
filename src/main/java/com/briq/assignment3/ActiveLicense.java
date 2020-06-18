package com.briq.assignment3;

import java.io.IOException;
import java.util.List;

import com.briq.pdfreader.ReadPDF;

public class ActiveLicense {
	public static void main(String[] args) throws IOException {
		ReadPDF readPDF = new ReadPDF();
		List<String> data=readPDF.readPDF("E:\\Eclipse\\BriqAssignment\\code\\Briq\\output\\active_licences.PDF");
		System.out.println(data);
	}

}
