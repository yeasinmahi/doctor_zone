package com.sa221.doctorzone;

public class DoctorInfo {
	private int doctorId;
	private String doctorName;
	private String qualification;
	private String designation;
	private String experise;
	private String chamber;
	private String chamberLocation;
	private String visitingHour;
	private String offDay;
	private String mobile;
	private double lat;
	private double lang;
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qulification) {
		this.qualification = qulification;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getExperise() {
		return experise;
	}
	public void setExperise(String experise) {
		this.experise = experise;
	}
	public String getChamber() {
		return chamber;
	}
	public void setChamber(String chamber) {
		this.chamber = chamber;
	}
	public String getChamberLocation() {
		return chamberLocation;
	}
	public void setChamberLocation(String chamberLocation) {
		this.chamberLocation = chamberLocation;
	}
	public String getVisitingHour() {
		return visitingHour;
	}
	public void setVisitingHour(String visitingHour) {
		this.visitingHour = visitingHour;
	}
	public String getOffDay() {
		return offDay;
	}
	public void setOffDay(String offDay) {
		this.offDay = offDay;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Double getLat(){return lat; }
	public void setLat(Double lat){this.lat = lat; }
	public Double getLang(){return lang;}
	public void setLang(Double lang){this.lang=lang;}
}
