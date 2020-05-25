package com.filipe.erp.repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.filipe.erp.model.Empresa;
import com.filipe.erp.model.RamoAtividade;
import com.filipe.erp.model.TipoEmpresaEnum;

/*
 * Executar a classe para gerar a base de dados e inserir os primeiros registros.
 * A inserção via instrução SQL estava dando erro pois a sequence não estava sendo chamada para gerar
 * os valores dos IDS no postgreSQL. Com isso, ao inserir uma nova empresa após, a sequence tentava
 * inserir o primeiro valor(1) e como já existia um registro com id 1, ocorria o erro.
 * 
 * */
public class SchemaGeneration {
	
	public static void main(String[] args) {
		
		System.out.println("\n##### INICIANDO A CRIAÇÃO DA BASE DE DADOS #####");
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("criacaoBaseDadosPU");
		EntityManager em = emf.createEntityManager();	
		em.getTransaction().begin();
		
		RamoAtividadeRepository ramoAtividadeRepository = new RamoAtividadeRepository(em);
		EmpresaRepository empresaRepository = new EmpresaRepository(em);
		System.out.println("\n##### FIM DA CRIAÇÃO DA BASE DE DADOS #####");
		System.out.println("\n##### INSERINDO DADOS INICIAIS... #####");
		List<RamoAtividade> ramos = Arrays.asList(
				new RamoAtividade("Distribuição de alimentos"),
				new RamoAtividade("Telecomunicações"),
				new RamoAtividade("Vestuário"),
				new RamoAtividade("Lavanderia"),
				new RamoAtividade("Gráfica"),
				new RamoAtividade("Mecânica"),
				new RamoAtividade("Turismo"),
				new RamoAtividade("Saúde"),
				new RamoAtividade("Educação"),
				new RamoAtividade("Lazer"));
		
		//"Gambiarra", melhorar depois
		for (int i = 0; i < ramos.size(); i++) {
			RamoAtividade ramoAtividade = ramoAtividadeRepository.guardar(ramos.get(i));
			ramos.set(i, ramoAtividade);
		}
		
		List<Empresa> empresas = Arrays.asList(
					new Empresa(
							"Mercado do João", 
							"70.311.193/0001-87", 
							"João Mercado e Distribuidor de Alimentos Ltda", 
							new Date(), 
							TipoEmpresaEnum.LTDA,
							ramos.get(0)),
					
					new Empresa(
							"Fale Mais", 
							"52.822.994/0001-25", 
							"Fale Mais Telecom S.A.", 
							new Date(), 
							TipoEmpresaEnum.SA, 
							ramos.get(1)),
					
					new Empresa(
							"Maria de Souza da Silva", 
							"41.952.519/0001-57", 
							"Maria de Souza da Silva", 
							new Date(), 
							TipoEmpresaEnum.MEI, 
							ramos.get(2)),
					
					new Empresa(
							"Gomes Inovação", 
							"16.134.777/0001-89", 
							"José Fernando Gomes EIRELI ME", 
							new Date(), 
							TipoEmpresaEnum.EIRELI, 
							ramos.get(3))					
				);
		
		//Melhorar depois
		for (Empresa empresa : empresas) {
			empresaRepository.guardar(empresa);
		}
		
		em.getTransaction().commit();
		System.out.println("\n##### DADOS INSERIDOS COM SUCESSO #####");
		
		System.out.println("\n##### TESTE: BUSCANDO AS INFORMAÇÕES INSERIDAS #####");
		ramos = ramoAtividadeRepository.pesquisar("");
		empresas = empresaRepository.pesquisar("");
		System.out.println(empresas);
		System.out.println(ramos);
		em.close();
		emf.close();
	}

}
