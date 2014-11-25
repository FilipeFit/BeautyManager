package br.com.BeautyManager.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.BeautyManager.model.Cliente;
import br.com.BeautyManager.model.Endereco;
import br.com.BeautyManager.model.TipoPessoa;

public class TesteCliente {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("BeautyPU");

		EntityManager manager = factory.createEntityManager();
		EntityTransaction trx = manager.getTransaction();

		trx.begin();

		Cliente cliente = new Cliente();
		cliente.setNome("João das Couves2");
		cliente.setEmail("joao@dascouves.com");
		cliente.setDocumentoReceitaFederal("123.123.123-12");
		cliente.setTipo(TipoPessoa.FISICA);
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua das Aboboras Vermelhas");
		endereco.setNumero("111");
		endereco.setCidade("Uberlândia");
		endereco.setUf("MG");
		endereco.setCep("38400-000");
		endereco.setCliente(cliente);
		cliente.getEnderecos().add(endereco);
		manager.persist(cliente);

		trx.commit();
	}

}
