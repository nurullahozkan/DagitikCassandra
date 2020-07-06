/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApacheCassandra;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import java.util.UUID;

/**
 *
 * @author Nurullah
 */
public class Film {

    public static void main(String[] args) {
        Create();
    }
       
    public static String Read() {
        try {
            Baglanti connector = new Baglanti();
            connector.connectdb("192.168.121.143", 9042);

            String selectQuery = "SELECT * FROM film_keyspace.film";
            ResultSet rs = connector.getSession().execute(selectQuery);

            rs.forEach(n -> {
                System.out.println("----------------------------------");
                System.out.println("Adı : " + n.getString("adi"));
                System.out.println("Imdb : " + n.getDouble("imdb"));
                System.out.println("Türü : " + n.getString("tur"));
                System.out.println("Yıl : " + n.getInt("yil"));
            });

            connector.close();
            return "Okuma Islemi Basarılı";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "HATA";
        }
    }
    
    public static String Create() {
        UUID uuid = UUID.randomUUID();

        try {
            Baglanti connector = new Baglanti();
            connector.connectdb("192.168.121.143", 9042);

            String insertQuery = "INSERT INTO film_keyspace.film (id,adi,imdb,tur,yil)"
                    + "VALUES (?,?,?,?,?)";

            PreparedStatement psInsert = connector.getSession().prepare(insertQuery);
            BoundStatement bsInsert = psInsert.bind(uuid, "Titanik", 7.8, "R/D", 1997);
            //BoundStatement bsInsert = psInsert.bind(uuid, "Başlangıç", 8.8, "G/B-K", 2010);

            connector.getSession().execute(bsInsert);
            connector.close();
            return "Ekleme Islemi Basarılı";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "HATA";
        }
    }

    public static String Update() {

        try {
            Baglanti connector = new Baglanti();
            connector.connectdb("192.168.121.143", 9042);

            String updateQuery = "UPDATE film_keyspace.film SET imdb = ? WHERE id =  01dd29b0-db71-40c6-a098-95637eec1906   ";

            PreparedStatement psUpdate = connector.getSession().prepare(updateQuery);
            BoundStatement bsUpdate = psUpdate.bind(10.0);
            connector.getSession().execute(bsUpdate);

            connector.close();
            return "Guncelleme Islemi Basarılı";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "HATA";
        }
    }

    public static String Delete() {
        try {
            Baglanti connector = new Baglanti();
            connector.connectdb("192.168.121.143", 9042);
            
              
            String deleteQuery = "DELETE FROM film_keyspace.film WHERE id = 01dd29b0-db71-40c6-a098-95637eec1906  ";

            PreparedStatement psDelete = connector.getSession().prepare(deleteQuery);
            BoundStatement bsDelete = psDelete.bind();
            connector.getSession().execute(bsDelete);

            connector.close();
            return "Silme Islemi Basarılı";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "HATA";
        }
    }

    private static void keyFilm() {
        try {
            Baglanti connector = new Baglanti();
            connector.connectdb("192.168.121.143", 9042);

            String keyFilm = "CREATE KEYSPACE IF NOT EXISTS film_keyspace WITH "
                    + "replication = {'class' : 'NetworkTopologyStrategy', 'dc1' : 2 }";

            connector.getSession().execute(keyFilm);
            connector.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tableFilm() {
        try {
            Baglanti connector = new Baglanti();
            connector.connectdb("192.168.121.143", 9042);

            String createmovieTable = "CREATE TABLE IF NOT EXISTS film_keyspace.film"
                    + "(id uuid, adi varchar, tur varchar, yil int, imdb double, PRIMARY KEY (id))";

            connector.getSession().execute(createmovieTable);
            connector.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
