package com.sa221.doctorzone;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutDoctor extends Activity {
	TextView doctorName,doctorQualification,doctorExpertise,doctorChamber,doctorChamberLocation,doctorDesignation,doctorVisitingHours,doctorOffDay;
	DoctorInfo doctorInfo;
	public void init(){
		DbHelper dbHelper = new DbHelper(getApplicationContext());
		Intent intent = getIntent();
		int doctorId=(Integer) intent.getExtras().get("doctorId");
		doctorInfo= dbHelper.getAllDoctorInfo(doctorId);
		doctorName=(TextView) findViewById(R.id.doctorNameView);
		doctorQualification=(TextView) findViewById(R.id.doctorQualificationView);
		doctorDesignation=(TextView) findViewById(R.id.doctorDesignationView);
		doctorExpertise=(TextView) findViewById(R.id.doctorExpertiseView);
		doctorChamber=(TextView) findViewById(R.id.doctorChamberView);
		doctorChamberLocation=(TextView) findViewById(R.id.doctorChamberLocationView);
		doctorVisitingHours=(TextView) findViewById(R.id.doctorVisitingHourView);
		doctorOffDay=(TextView) findViewById(R.id.doctorOffDayView);
		
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
		
		init();
		doctorName.setText(doctorInfo.getDoctorName().toString());
		doctorQualification.setText(doctorInfo.getQulification());
		doctorExpertise.setText(doctorInfo.getExperise());
		doctorChamberLocation.setText(doctorInfo.getChamberLocation());
		doctorChamber.setText(doctorInfo.getChamber());
		doctorDesignation.setText(doctorInfo.getDesignation());
		doctorVisitingHours.setText(doctorInfo.getVisitingHour());
		doctorOffDay.setText(doctorInfo.getOffDay());
	}
	
	public void Appoinment(View v){
		String mobileNumber= doctorInfo.getMobile();
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		Uri data = Uri.parse("tel:"+mobileNumber);
		callIntent.setData(data);
		startActivity(callIntent);
	}
}