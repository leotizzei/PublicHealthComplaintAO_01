package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import publichealthcomplaint.datatypes.ISituationDt;

 class Situation implements ISituationDt{

	private int code;

	private String description;

	
	
	private int complaintStatus;
	
	
	public int getComplaintStatus() {
		return complaintStatus;
	}

	public void setComplaintStatus(int complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	public Situation(int codigo, String descricao) {
		this.code = codigo;
		this.description = descricao;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	
}
