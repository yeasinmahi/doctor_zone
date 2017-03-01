package com.sa221.doctorzone;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Search extends Activity {
	private Spinner hospitalSpinner, departmentSpinner;
	private int hospitalId, departmentId;
	private EditText searchDoctorNameTextBox;
	private DbHelper dbHelper;
	private ArrayList<Hospital> hospitals;
	private ArrayList<Department> departments;

	public void init() {
		hospitalSpinner = (Spinner) findViewById(R.id.spinner1);
		departmentSpinner = (Spinner) findViewById(R.id.spinner2);
		searchDoctorNameTextBox = (EditText) findViewById(R.id.searchTextBox);
		dbHelper = new DbHelper(getApplicationContext());
	}

	public void Onload() {
		hospitals = dbHelper.getAllHospital();
		final ArrayAdapter<Hospital> hospitalAdapter = new ArrayAdapter<Hospital>(
				this, android.R.layout.simple_spinner_item, hospitals);
		// hospitalAdapter.add(hospitals);
		hospitalAdapter
				.setDropDownViewResource(android.R.layout.simple_list_item_1);

		hospitalSpinner.setAdapter(hospitalAdapter);
		hospitalSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int position, long Id) {
				Hospital hospital = hospitalAdapter.getItem(position);
				hospitalId = hospital.hospitalId;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		departments = dbHelper.getAllDepartment();
		final ArrayAdapter<Department> departmentAdapter = new ArrayAdapter<Department>(
				this, android.R.layout.simple_spinner_item, departments);
		// hospitalAdapter.add(hospitals);
		departmentAdapter
				.setDropDownViewResource(android.R.layout.simple_list_item_1);

		departmentSpinner.setAdapter(departmentAdapter);
		departmentSpinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View view,
							int position, long Id) {
						Department department = departmentAdapter
								.getItem(position);
						departmentId = department.departmentId;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		init();
		Onload();
	}

	public void search(View v) {
		String sql = searchMethod();
		Intent intentSearch = new Intent(Search.this, DoctorList.class);
		intentSearch.putExtra("sql", sql);
		Search.this.startActivity(intentSearch);
		//Log.i("Content ", " Search layout ");
		
	}

	public String searchMethod() {
		String doctorName = searchDoctorNameTextBox.getText().toString();
		String sql;
		if (hospitalId <= 0) {
			if (departmentId <= 0) {
				if (doctorName.equals("")) {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid";
				} else {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid where i.dname like '%"
							+ doctorName + "%'";
				}
			} else {
				if (doctorName.equals("")) {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid where s.cid="
							+ departmentId;
				} else {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid where i.dname like '%"
							+ doctorName + "%' and s.cid=" + departmentId;
				}
			}
		} else {
			if (departmentId <= 0) {
				if (doctorName.equals("")) {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid where h.hid="
							+ hospitalId;
				} else {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid where i.dname like '%"
							+ doctorName + "%' and h.hid=" + hospitalId;
				}
			} else {
				if (doctorName.equals("")) {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid where h.hid="
							+ hospitalId + " and s.cid=" + departmentId;
				} else {
					sql = "select i.did, i.dname, h.dhospital, s.dspecial from dinfo as i join dhospital as h on i.hid = h.hid join dspecial as s on s.cid=i.cid where i.dname like '%"
							+ doctorName
							+ "%' and h.hid="
							+ hospitalId
							+ " and s.cid=" + departmentId;
				}
			}
		}
		
		return sql;
	}

}
