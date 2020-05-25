package com.filipe.erp.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/*
 * A anotação será utilizada em métodos sempre que for necessário abrir uma transação com o 
 * banco de dados.
 * Essa anotação trabalha em conjunto com a classe TransacionalInterceptor.java (que é um interceptor)
 * responsável pela implementação de métodos necessários para abrir e fechar uma transação.
 * */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
public @interface Transacional {

}
