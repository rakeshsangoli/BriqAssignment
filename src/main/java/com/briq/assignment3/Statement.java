package com.briq.assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.briq.pdfreader.ReadPDF;

public class Statement {
	public static void main(String[] args) throws IOException {
		ReadPDF readPDF = new ReadPDF();
		List<String> data=readPDF.readPDF("E:\\Eclipse\\BriqAssignment\\code\\Briq\\output\\sample_statement.PDF");
		System.out.println(data);
		Pojo statement= new Pojo();
		for (int i = 0; i < data.size(); i++) {
			if(data.get(i).length()==0)
				continue;
			if(data.get(i).contains("Primary Account Number")){
				statement.setAccountNumber(data.get(i+1).trim());
			}
			if(data.get(i).contains("Total Checks Paid")){
				statement.setTotalChecks(data.get(i+1).trim());
			}
			if(data.get(i).contains("Total ATM Withdrawals & Debits")){
				statement.setTotalWithdrawls(data.get(i+1).trim());
			}
			if(data.get(i).contains("Total Deposits & Other Credits")){
				statement.setTotalDeposits(data.get(i+1).trim());
			}
			if(data.get(i).contains("Ending Balance on")){
				//   Ending Balance on June 5, 2003 $10,521.19
				statement.setEndingBalance(data.get(i+1).trim());
			}
			if(data.get(i).contains("Statement Date:")){
				statement.setStatementDate(data.get(i+1).trim());
			}
		}
		statement.setCustomerName(data.get(2));
		statement.setBankAddress(data.get(0)+","+data.get(1));
		statement.setCustomerAddress(data.get(3)+","+data.get(4));
		
		System.out.println("***********************************************************");
		System.out.println("Bank Address="+statement.getBankAddress());
		System.out.println("Customer Name="+statement.getCustomerName());
		System.out.println("Customer Address="+statement.getCustomerAddress());
		System.out.println("Account Number="+statement.getAccountNumber());
		System.out.println("Statement Date="+statement.getStatementDate());
		System.out.println("Ending Balance="+statement.getEndingBalance());
		System.out.println("Total Withdrawals="+statement.getTotalWithdrawls());
		System.out.println("Total Deposite="+statement.getTotalDeposits());
		System.out.println("Total Checks="+statement.getTotalChecks());
		System.out.println("***********************************************************");
	}

}
