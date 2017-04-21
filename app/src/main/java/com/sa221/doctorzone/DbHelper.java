package com.sa221.doctorzone;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private static final String dbName = "doctorZone";
	private static final int version = 1;
	

	public DbHelper(Context context) {
		super(context, dbName, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Hospital> getAllHospital() {
		ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
		// First Index Of Spinner hardCoded
		Hospital fhospital = new Hospital();
		fhospital.hospitalId = 0;
		fhospital.hospitalName = "All";
		hospitals.add(fhospital);
		// Rest Index Of Spinner from database
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query("dhospital", null, null, null, null, null,
				"dhospital");
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Hospital hospital = new Hospital();
				int hospitalId = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex("hid")));
				String hospitalName = cursor.getString(cursor
						.getColumnIndex("dhospital"));
				hospital.hospitalId = hospitalId;
				hospital.hospitalName = hospitalName;
				hospitals.add(hospital);
			}
		}
		if (cursor != null) {
			cursor.close();
		}
		db.close();
		return hospitals;
	}

	public ArrayList<Department> getAllDepartment() {
		ArrayList<Department> departments = new ArrayList<Department>();
		// First Index Of Spinner hardCoded
		Department fdepartment = new Department();
		fdepartment.departmentId = 0;
		fdepartment.departmentName = "All";
		departments.add(fdepartment);
		// Rest Index Of Spinner from database
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query("dspecial", null, null, null, null, null,
				"dspecial");
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Department department = new Department();
				int departmentId = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex("cid")));
				String departmentName = cursor.getString(cursor
						.getColumnIndex("dspecial"));
				department.departmentId = departmentId;
				department.departmentName = departmentName;
				departments.add(department);
			}
		}
		if (cursor != null) {
			cursor.close();
		}
		db.close();
		return departments;
	}

	public ArrayList<DoctorAndHospital> loadDataBySearch(String sql) {
		ArrayList<DoctorAndHospital> doctorAndHospitals = new ArrayList<DoctorAndHospital>();
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				DoctorAndHospital doctorAndHospital = new DoctorAndHospital();
				String doctorId = cursor.getString(cursor
						.getColumnIndex("did"));
				String doctorName = cursor.getString(cursor
						.getColumnIndex("dname"));
				String hospitalName = cursor.getString(cursor
						.getColumnIndex("dhospital"));
				String departmentName = cursor.getString(cursor
						.getColumnIndex("dspecial"));
				doctorAndHospital.setDoctorId(doctorId);
				doctorAndHospital.setDoctorName(doctorName);
				doctorAndHospital.setDepartmentName(departmentName);
				doctorAndHospital.setHospitalName(hospitalName);
				doctorAndHospitals.add(doctorAndHospital);
			}
		}
		if (cursor != null) {
			cursor.close();
		}
		db.close();
		return doctorAndHospitals;
	}
	
	public DoctorInfo getAllDoctorInfo(int did) {
		final String sql="select i.dname,i.mobile," +
				"i.qualification,i.designation,h.dhospital" +
				",h.hlocation,h.lat, h.lang,c.dspecial,s.visitingHour,s.offDay" +
				" from dinfo as i join dhospital as h on i.hid=h.hid " +
				"join dspecial as c on i.cid=c.cid join shedule as s " +
				"on i.sid=s.sid where i.did="+did;
		DoctorInfo doctorInfo = null;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				doctorInfo = new DoctorInfo();
				String doctorName = cursor.getString(cursor
						.getColumnIndex("dname"));
				String qualification = cursor.getString(cursor
						.getColumnIndex("qualification"));
				String designation = cursor.getString(cursor
						.getColumnIndex("designation"));
				String experise = cursor.getString(cursor
						.getColumnIndex("dspecial"));
				String chamber = cursor.getString(cursor
						.getColumnIndex("dhospital"));
				String chamberLocation = cursor.getString(cursor
						.getColumnIndex("hlocation"));
				String visitingHour = cursor.getString(cursor
						.getColumnIndex("visitingHour"));
				String offDay = cursor.getString(cursor
						.getColumnIndex("offDay"));
				String mobile = cursor.getString(cursor
						.getColumnIndex("mobile"));
				Double lat = cursor.getDouble(cursor
						.getColumnIndex("lat"));
				double lang = cursor.getDouble(cursor
						.getColumnIndex("lang"));
				
				doctorInfo.setDoctorId(did);
				doctorInfo.setDoctorName(doctorName);
				doctorInfo.setChamber(chamber);
				doctorInfo.setChamberLocation(chamberLocation);
				doctorInfo.setDesignation(designation);
				doctorInfo.setExperise(experise);
				doctorInfo.setMobile(mobile);
				doctorInfo.setOffDay(offDay);
				doctorInfo.setQualification(qualification);
				doctorInfo.setVisitingHour(visitingHour);
				doctorInfo.setLat(lat);
				doctorInfo.setLang(lang);
			}
		}
		if (cursor != null) {
			cursor.close();
		}
		db.close();
		return doctorInfo;
	}

	/*
	 * public long insertStudent(Student student){ SQLiteDatabase db=
	 * this.getWritableDatabase(); ContentValues values= new ContentValues();
	 * values.put(nameField, student.getName()); values.put(emailField,
	 * student.getEmail()); values.put(phoneField, student.getPhone()); long
	 * inserted=db.insert(tableName, null, values); db.close(); return inserted;
	 * } public ArrayList<Student> getAllStudent(){ ArrayList<Student> students
	 * = new ArrayList<Student>(); SQLiteDatabase db=
	 * this.getReadableDatabase(); Cursor cursor = db.query(tableName, null,
	 * null, null, null, null, null); if(cursor!=null && cursor.getCount()>0){
	 * cursor.moveToFirst(); for (int i = 0; i < cursor.getCount(); i++) { int
	 * id = cursor.getInt(cursor.getColumnIndex(idField)); String name=
	 * cursor.getString(cursor.getColumnIndex(nameField)); String email=
	 * cursor.getString(cursor.getColumnIndex(emailField)); String phone=
	 * cursor.getString(cursor.getColumnIndex(phoneField)); Student student =
	 * new Student(id, name, email, phone); students.add(student);
	 * cursor.moveToNext(); } } cursor.close(); return students; } public
	 * ArrayList<Student> searchByName(String name){ ArrayList<Student> students
	 * = new ArrayList<Student>(); SQLiteDatabase db= getReadableDatabase();
	 * Cursor cursor = db.query(tableName, null, " name like '%"+name+"%'",
	 * null, null, null, null); if(cursor!=null && cursor.getCount()>0){
	 * cursor.moveToFirst(); for (int i = 0; i < cursor.getCount(); i++) { int
	 * id = cursor.getInt(cursor.getColumnIndex(idField)); String name1=
	 * cursor.getString(cursor.getColumnIndex(nameField)); String email=
	 * cursor.getString(cursor.getColumnIndex(emailField)); String phone=
	 * cursor.getString(cursor.getColumnIndex(phoneField)); Student student =
	 * new Student(id, name1, email, phone); students.add(student);
	 * cursor.moveToNext(); } } cursor.close(); return students; } public int
	 * updateName(int id, String newName){ SQLiteDatabase db=
	 * getWritableDatabase(); ContentValues values= new ContentValues();
	 * values.put(nameField, newName); int updated = db.update(tableName,
	 * values, idField+"=?", new String[]{""+id}); return updated; } public int
	 * delete(int id){ SQLiteDatabase db= getWritableDatabase(); int deleted=
	 * db.delete(tableName, idField+"=?", new String[]{""+id}); return deleted;
	 * }
	 */

}
