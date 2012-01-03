package publichealthcomplaint.infrastructuremgt.impl;

import java.util.Iterator;
import java.util.List;

import publichealthcomplaint.datatypes.IHealthUnitDt;


class HealthUnit implements IHealthUnitDt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	private String description;

	private List specialities;

	HealthUnit() {
	}

	public HealthUnit(String description, List specialities) {
		this.description = description;
		this.specialities = specialities;
	}

	public boolean hasSpeciality(int code) {
		for(Iterator i = specialities.iterator(); i.hasNext();) {
			MedicalSpeciality m = (MedicalSpeciality) i.next();
			if (m.getCodigo() == code) {
				return true;
			}
		}
		return false;
	}
	
	public int getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

	
	public List getSpecialities() {
		return this.specialities;
	}

	public void setCode(int cod) {
		this.code = cod;
	}

	public void setDescription(String descricao) {
		this.description = descricao;
	}

	public String toString() {
		return description;
	}
}