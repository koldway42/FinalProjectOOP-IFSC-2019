package project.restaurant.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import project.restaurant.model.User;

public class TestClass {
	public static void main(String[] args) {
		try {
			Session session = new Configuration().configure("/project/restaurant/resources/hibernate.cfg.xml").buildSessionFactory().openSession();
			User user = new User();
			
			user.setName("Matheus Graça");
			user.setEmail("math12125.jr@gmail.com");
			user.setPassword("123456");
			user.setCreatedAt(Date.valueOf(LocalDate.now()));
			
			Transaction tx = session.beginTransaction();
			session.save(user);
			System.out.println("Commited Succesfully");
			tx.commit();
			session.close();
			
		} catch(Exception err) {
			System.out.println("Erro Merda");
			err.printStackTrace();
		}
	}
}
