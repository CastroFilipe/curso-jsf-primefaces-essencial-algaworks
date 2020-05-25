package com.filipe.erp.model;

public enum TipoEmpresaEnum {

	MEI("Microempreendedor individual"), 
	EIRELI("Empresa individual de responsabilidade limitada"),
	LTDA("Sociedade limitada"), 
	SA("Sociedade anônima");

	private String descricao;

	TipoEmpresaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
