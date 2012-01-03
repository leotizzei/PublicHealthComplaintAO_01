package publichealthcomplaint.infrastructuremgt.impl;

import publichealthcomplaint.datatypes.IMedicalSpecialityDt;

class MedicalSpeciality implements IMedicalSpecialityDt{

	private int codigo;

	private String descricao;

	MedicalSpeciality(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setCodigo(int cod) {
		this.codigo = cod;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}
}
