package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cidades;
import br.edu.ifsul.modelo.UnidadesAtendimento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Felipe
 */
public class TestePersistirUnidades {
    
  EntityManagerFactory emf;
    EntityManager em;
    

    public TestePersistirUnidades() {

    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TA-6N1-Trabalho-PU");
        em = emf.createEntityManager();
    }
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    @Test
    public void testePersistirPais() {
        boolean exception = false;
        try {
            UnidadesAtendimento obj = new UnidadesAtendimento();
            obj.setNome("Unidade Marau 1");
            obj.setCapacidade(100);
            obj.setEndereco("Centro");
            Cidades c = new Cidades();
            c.setNome("Marau");
            c.setEstado("Rio Grande do Sul");
            obj.setCidades(c);
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
            
        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }
    
}