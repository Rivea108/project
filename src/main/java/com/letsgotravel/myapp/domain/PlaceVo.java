package com.letsgotravel.myapp.domain;

public class PlaceVo {

	private int plidx;
	private int ididx;
	private int nthDay;
	private String placeName;
	private String category;
	private String startTime;
	private String endTime;
	private String photoUrl;
	private String details;
	private String latitude;
	private String longitude;
	private String address;
	
	public int getPlidx() {
		return plidx;
	}
	public void setPlidx(int plidx) {
		this.plidx = plidx;
	}
	public int getIdidx() {
		return ididx;
	}
	public void setIdidx(int ididx) {
		this.ididx = ididx;
	}
	public int getNthDay() {
		return nthDay;
	}
	public void setNthDay(int nthDay) {
		this.nthDay = nthDay;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}