package com.infra;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HibernateInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// Inicialize o HibernateUtil aqui
		HibernateUtil.getEntityManagerFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Faça qualquer limpeza necessária aqui, se aplicável
		HibernateUtil.closeEntityManagerFactory();
	}
}
