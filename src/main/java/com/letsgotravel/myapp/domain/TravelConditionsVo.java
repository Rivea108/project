package com.letsgotravel.myapp.domain;

public class TravelConditionsVo {

	private int tcidx;
	private int budgetMin;
	private int budgetMax;
	private String departureMonth;
	private int duration;
    private String groupType;
    private String thema;
    private int peopleCount;
    private String medicine;
    private String destination;
    
	public int getTcidx() {
		return tcidx;
	}
	public void setTcidx(int tcidx) {
		this.tcidx = tcidx;
	}
	public int getBudgetMin() {
		return budgetMin;
	}
	public void setBudgetMin(int budgetMin) {
		this.budgetMin = budgetMin;
	}
	public int getBudgetMax() {
		return budgetMax;
	}
	public void setBudgetMax(int budgetMax) {
		this.budgetMax = budgetMax;
	}
	public String getDepartureMonth() {
		return departureMonth;
	}
	public void setDepartureMonth(String departureMonth) {
		this.departureMonth = departureMonth;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getThema() {
		return thema;
	}
	public void setThema(String thema) {
		this.thema = thema;
	}
	public int getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}    
}