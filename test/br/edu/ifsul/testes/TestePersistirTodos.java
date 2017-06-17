/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Cidades;
import br.edu.ifsul.modelo.Pacientes;
import br.edu.ifsul.modelo.UnidadesAtendimento;
import java.util.ArrayList;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersistirTodos {
    
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirTodos() {
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
    public void teste() {
        boolean exception = false;
        try {
            UnidadesAtendimento obj = new UnidadesAtendimento();
            obj.setNome("Unidade Marau 1");
            obj.setCapacidade(100);
            obj.setEndereco("Centro");
            
            Cidades c = new Cidades();
            c.setNome("Marau");
            c.setEstado("Rio Grande do Sul");
            c.adicionarUnidadeAtendimento(obj);
            obj.setCidades(c);
            
            Pacientes Pac = new Pacientes();
            Pac.setNome("Joao");
            Pac.setEmail("joao@gmail.com");
            Pac.setNascimento(Calendar.getInstance());
            Pac.setRg("96533543222");
            Pac.setNrCartaoSUS("12908393275834731238");
            Pac.setTiposanguineo("tipo O positivo");
            
            ArrayList<UnidadesAtendimento> unidades = new ArrayList<>();
            
            unidades.add(obj);
            
            Pac.setUnidadesPacientes(unidades);
            
            em.getTransaction().begin();
            em.persist(c);
            em.persist(Pac);
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        
        Assert.assertEquals(false, exception);
        
    }
    
}
