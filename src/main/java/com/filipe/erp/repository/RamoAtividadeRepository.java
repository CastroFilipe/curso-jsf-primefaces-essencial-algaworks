package com.filipe.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.filipe.erp.model.RamoAtividade;

public class RamoAtividadeRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Anotação utilizada pelo CDI. 
	 * A depedência a ser injetada foi configurada na classe @EntityManagerProducer
	 */
	@Inject
	private EntityManager manager;

	public RamoAtividadeRepository() {

	}

	public RamoAtividadeRepository(EntityManager manager) {
		this.manager = manager;
	}

	public List<RamoAtividade> pesquisar(String descricao) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));

		TypedQuery<RamoAtividade> query = manager.createQuery(criteriaQuery);

		return query.getResultList();
	}
	
	public RamoAtividade guardar(RamoAtividade ramoAtividade) {
		return manager.merge(ramoAtividade);
	}

}
