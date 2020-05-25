package com.filipe.erp.utils;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Classe que fará a interceptação de qualquer método(ou classe) anotado com @Transacional 
 * com o objetivo de abrir uma transação para aquele método anotado.
 * Após a interceptação o método invoke, anotado com @AroundInvoke, será executado. Isso será feito
 * antes do método que foi interceptado ser executado.
 * 
 * */
@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction trx = manager.getTransaction();
		//variavel para indicar se a transação foi criada por essa instância
		boolean criador = false;

		try {
			if (!trx.isActive()) {
				// truque para fazer rollback no que já passou
				// (senão, um futuro commit confirmaria até mesmo operações sem transação)
				trx.begin();
				trx.rollback();

				// agora sim inicia a transação
				trx.begin();

				criador = true;
			}
			
			//Informa ao CDI que o método interceptado já pode ser executado
			return context.proceed();
			
		} catch (Exception e) {
			if (trx != null && criador) {
				trx.rollback();
			}

			throw e;
		} finally {
			if (trx != null && trx.isActive() && criador) {
				trx.commit();
			}
		}
	}

}
