package com.filipe.erp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "EMPRESA", uniqueConstraints = { 
		@UniqueConstraint (columnNames = "ID_EMPRESA", name = "UK_ID_EMPRESA")
})
@SequenceGenerator(name = "SEQ_EMPRESA", sequenceName = "SQ_EMPRESA", allocationSize = 1)
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EMPRESA")
	@Column(name = "ID_EMPRESA")
	private Long id;

	@Column(name = "TX_NOME_FANTASIA", nullable = false, length = 80)
	private String nomeFantasia;

	@Column(name = "TX_CNPJ", nullable = false, length = 18)
	private String cnpj;

	@Column(name = "TX_RAZAO_SOCIAL", nullable = false, length = 120)
	private String razaoSocial;

	@Column(name = "DH_FUNDACAO")
	@Temporal(TemporalType.DATE)
	private Date dataFundacao;

	@Column(name = "EN_TIPO_EMPRESA", nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private TipoEmpresaEnum tipoEmpresa;

	@ManyToOne
	@JoinColumn(name = "ID_RAMO_ATIVIDADE", nullable = false, 
	foreignKey = @ForeignKey(name = "FK_EMPRESA_RAMOATIVIDADE"))
	private RamoAtividade ramoAtividade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
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
		Empresa other = (Empresa) obj;
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
		builder.append("Empresa [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
