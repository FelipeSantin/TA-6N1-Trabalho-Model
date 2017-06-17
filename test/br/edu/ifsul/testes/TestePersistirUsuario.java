package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Pacientes;
import java.util.Calendar;
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
public class TestePersistirUsuario {
    
  EntityManagerFactory emf;
    EntityManager em;
    

    public TestePersistirUsuario() {

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
            Pacientes obj = new Pacientes();
            obj.setNome("Joao");
            obj.setEmail("joao@gmail.com");
            obj.setNascimento(Calendar.getInstance());
            obj.setRg("96533543222");
            obj.setNrCartaoSUS("12908393275834731238");
            
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