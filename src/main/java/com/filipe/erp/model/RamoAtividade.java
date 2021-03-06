package com.filipe.erp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RAMO_ATIVIDADE")
@SequenceGenerator(name = "SEQ_RAMO_ATIVIDADE", sequenceName = "SQ_RAMO_ATIVIDADE", initialValue = 1, allocationSize = 1)
public class RamoAtividade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RAMO_ATIVIDADE")
	@Column(name = "ID_RAMO_ATIVIDADE")
	private Long id;

	@Column(name = "TX_DESCRICAO", nullable = false, length = 80)
	private String descricao;

	public RamoAtividade() {
		super();
	}

	public RamoAtividade(String descricao) {
		super();
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RamoAtividade other = (RamoAtividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RamoAtividade [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
