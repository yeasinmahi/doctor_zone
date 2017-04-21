package com.sa221.doctorzone;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class DoctorList extends ListActivity {
    static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    String sql;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_list);
        //setListAdapter(new ArrayAdapter<String>(DoctorList.this, android.R.layout.activity_list_item, list));
        Intent i = getIntent();
        sql = i.getExtras().getString("sql");
        SimpleAdapter adapter = new SimpleAdapter(this, list,
                R.layout.custom_row_view, new String[]{"name", "department", "hospital"}, new int[]{R.id.text1, R.id.text2, R.id.text3});
        populateList();
        setListAdapter(adapter);

    }

    private void populateList() {
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        ArrayList<DoctorAndHospital> doctorAndHospitals = dbHelper.loadDataBySearch(sql);
        list.clear();
        for (DoctorAndHospital doctorAndHospital : doctorAndHospitals) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("id", doctorAndHospital.getDoctorId());
            temp.put("name", doctorAndHospital.getDoctorName());
            temp.put("department", doctorAndHospital.getDepartmentName());
            temp.put("hospital", doctorAndHospital.getHospitalName());
            //temp.put("color", "Silver, Grey, Black");
            list.add(temp);
        }
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        try {
            Intent intentMain = new Intent(DoctorList.this, AboutDoctor.class);
            int doctorId = Integer.parseInt(list.get(position).get("id"));
            intentMain.putExtra("doctorId", doctorId);
            DoctorList.this.startActivity(intentMain);
        } catch (Exception e) {

        }
    }
}
//	public void lvButton (View v){
//		Intent intentListView = new Intent(DoctorList.this, AboutDoctor.class);
//		DoctorList.this.startActivity(intentListView);
//		Log.i("Content ", " doctor_list layout ");
//	}
//	
//}

