package com.letsgotravel.myapp.domain;

public class ScrapVo {
    
    public int sidx;
    public int tcidx;
    public String destination;
    public int duration;
    public int peopleCount;
    public String budget;
    public String groupType;
    public String thema;
    public String date;

    public int getSidx() {
        return sidx;
    }
    public void setSidx(int sidx) {
        this.sidx = sidx;
    }

    public int getTcidx() {
        return tcidx;
    }
    public void setTcidx(int midx) {
        this.tcidx = midx;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPeopleCount() {
        return peopleCount;
    }
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getBudget() {
        return budget;
    }
    public void setBudget(String budget) {
        this.budget = budget;
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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
