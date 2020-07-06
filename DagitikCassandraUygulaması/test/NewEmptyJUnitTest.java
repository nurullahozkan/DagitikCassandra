/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ApacheCassandra.Film;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nurullah
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    private Film film;
    @Before
    public void setUp() {
        film = new Film();
    }
    
    @After
    public void tearDown() {
        film = null;
    }

     @Test
     public void ReadTest() {         
     Assert.assertEquals("Okuma Islemi Basarılı", film.Read());
     }
     
     @Test
     public void CreateTest() {         
     Assert.assertEquals("Ekleme Islemi Basarılı", film.Create());
     }
     
     @Test
     public void UpdateTest() {         
     Assert.assertEquals("Guncelleme Islemi Basarılı", film.Update());
     }
     
     @Test
     public void DeleteTest() {         
     Assert.assertEquals("Silme Islemi Basarılı", film.Delete());
     }
}
