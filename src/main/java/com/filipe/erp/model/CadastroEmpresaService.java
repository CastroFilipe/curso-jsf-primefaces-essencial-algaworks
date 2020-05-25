package com.filipe.erp.model;

import java.io.Serializable;

import javax.inject.Inject;

import com.filipe.erp.repository.EmpresaRepository;
import com.filipe.erp.utils.Transacional;

public class CadastroEmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Usando anotação do CDI para injetar uma classe que não é o EntityManager.
	 * */
	@Inject
	private EmpresaRepository empresaRepository;

	@Transacional
	public void salvar(Empresa empresa) {
		empresaRepository.guardar(empresa);
	}

	@Transacional
	public void excluir(Empresa empresa) {
		empresaRepository.remover(empresa);
	}

}
