package br.com.BeautyManager.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Minha factory fica em escopo de aplicação pois eu tenho somente uma fabrica por aplicação e vou
// criando entity manager a cada nova requisição
@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("BeautyPU");
	}
	
	// Diz que esse método é um produtor onde a casa manager produzido em um escopo de requisição
	@Produces
	@RequestScoped
	public EntityManager createEntityManager(){
		return factory.createEntityManager();
	}

	// O disposes é um gatilho para que a requisição seja finalizada o metodo vai 
	// fazer algumas ações como fecha o entityManager
	public void cliseEntityManager(@Disposes EntityManager manager){
		manager.close();
		
	}
}
