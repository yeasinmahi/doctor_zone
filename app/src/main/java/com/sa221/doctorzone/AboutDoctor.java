package com.sa221.doctorzone;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AboutDoctor extends Activity {
    TextView doctorName, doctorQualification, doctorExpertise, doctorChamber, doctorChamberLocation, doctorDesignation, doctorVisitingHours, doctorOffDay;
    DoctorInfo doctorInfo;

    public void init() {
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        Intent intent = getIntent();
        int doctorId = (Integer) intent.getExtras().get("doctorId");
        doctorInfo = dbHelper.getAllDoctorInfo(doctorId);
        doctorName = (TextView) findViewById(R.id.doctorNameView);
        doctorQualification = (TextView) findViewById(R.id.doctorQualificationView);
        doctorDesignation = (TextView) findViewById(R.id.doctorDesignationView);
        doctorExpertise = (TextView) findViewById(R.id.doctorExpertiseView);
        doctorChamber = (TextView) findViewById(R.id.doctorChamberView);
        doctorChamberLocation = (TextView) findViewById(R.id.doctorChamberLocationView);
        doctorVisitingHours = (TextView) findViewById(R.id.doctorVisitingHourView);
        doctorOffDay = (TextView) findViewById(R.id.doctorOffDayView);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        init();
        doctorName.setText(doctorInfo.getDoctorName().toString());
        doctorQualification.setText(doctorInfo.getQualification());
        doctorExpertise.setText(doctorInfo.getExperise());
        doctorChamberLocation.setText(doctorInfo.getChamberLocation());
        doctorChamber.setText(doctorInfo.getChamber());
        doctorDesignation.setText(doctorInfo.getDesignation());
        doctorVisitingHours.setText(doctorInfo.getVisitingHour());
        doctorOffDay.setText(doctorInfo.getOffDay());
    }

    public void appointment(View view) {
        String mobileNumber = doctorInfo.getMobile();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + mobileNumber);
        callIntent.setData(data);
        Utility.getPermission(this, new String[]{Manifest.permission.CALL_PHONE});
        if (Utility.checkPermission(this, Manifest.permission.CALL_PHONE)) return;
        startActivity(callIntent);
    }

    public void seeOnMap(View view) {
        Intent intent = new Intent(AboutDoctor.this, MapsActivity.class);
        intent.putExtra("lat", doctorInfo.getLat());
        intent.putExtra("lang", doctorInfo.getLang());
        startActivity(intent);
    }

    public void sentSms(View view) {
        Utility.getPermission(this, new String[]{Manifest.permission.SEND_SMS});
        if (Utility.checkPermission(this, Manifest.permission.SEND_SMS)) {
            Toast.makeText(this, "You have no permisson to sent sms", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("address", doctorInfo.getMobile());
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
        /*SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(doctorInfo.getMobile(), null, "sms message", null, null);*/
    }
}