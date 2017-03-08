package com.ericsson.group.services;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ericsson.group.businessEntities.RequestAdapter;

public class GetBaseDataByDateRequest extends RequestAdapter{
	protected static Date date;
	
	public GetBaseDataByDateRequest(String[] request) {
		super(request);
		setDate(request[1]);
	}
	
	public static Date getDate() {
		return date;
	}
	
	public void setDate(String date) {
		System.out.println("The date given to the base service is: " + date);
		
		//turn string into a date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate;
		try {
			utilDate = sdf.parse(date);
		} catch (ParseException e) {
			utilDate = new Date(0000-00-00);
		}
		
		System.out.println("The Util Date is: " + utilDate);
		
		java.sql.Date sqlDate = new Date(utilDate.getTime()); 
		
		System.out.println("The SQL date is: " + sqlDate);
		
		this.date = sqlDate;
	}
}
