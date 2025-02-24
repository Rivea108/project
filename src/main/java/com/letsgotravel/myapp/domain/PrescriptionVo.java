package com.letsgotravel.myapp.domain;

import java.util.List;

public class PrescriptionVo {
		
	public int pidx;
	public int midx;
	public String resMenufactureDate;
	public String resPrescribeOrg;
	public String resTelNo;
	public String commBrandName;
	public String commTelNo;
	public String date;
	public String ip;
	public String delyn;
	
	
	// 약물 정보 리스트 필드 추가
    private List<DrugVo> drugs;
	
	public List<DrugVo> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<DrugVo> drugs) {
		this.drugs = drugs;
	}
	public int getPidx() {
		return pidx;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getResMenufactureDate() {
		return resMenufactureDate;
	}
	public void setResMenufactureDate(String resMenufactureDate) {
		this.resMenufactureDate = resMenufactureDate;
	}
	public String getResPrescribeOrg() {
		return resPrescribeOrg;
	}
	public void setResPrescribeOrg(String resPrescribeOrg) {
		this.resPrescribeOrg = resPrescribeOrg;
	}
	public String getResTelNo() {
		return resTelNo;
	}
	public void setResTelNo(String resTelNo) {
		this.resTelNo = resTelNo;
	}
	public String getCommBrandName() {
		return commBrandName;
	}
	public void setCommBrandName(String commBrandName) {
		this.commBrandName = commBrandName;
	}
	public String getCommTelNo() {
		return commTelNo;
	}
	public void setCommTelNo(String commTelNo) {
		this.commTelNo = commTelNo;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	
}
