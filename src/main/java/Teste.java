import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.systributo.model.Pis;


public class Teste {
public static void main(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("impostoPU");
	EntityManager manager = factory.createEntityManager();

	EntityTransaction trx = manager.getTransaction();
	trx.begin();
	Pis pis = new Pis();
	pis.setDescricao("saasfasdf");
	pis.setCst("asfdasd");
	
	manager.persist(pis);
	
	trx.commit();
	
}
}
