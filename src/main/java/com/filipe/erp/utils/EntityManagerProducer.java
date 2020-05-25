package com.filipe.erp.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Classe responsável por criar a dependência para a injeção.
 * */
@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	//construtor
	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("baseDadosPU");
	}
	
	/**
	 * Método produtor de Objetos EntityManagers. 
	 * No momento de necessidade da injeção de uma EntityManager esse método será invocado..
	*/
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}
	
	//método que finaliza uma entityManager criada
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}