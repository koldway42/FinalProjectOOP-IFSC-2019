package project.restaurant.test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import project.restaurant.dao.OrderPadDao;
import project.restaurant.dao.ProductDao;
import project.restaurant.dao.UserDao;
import project.restaurant.model.OrderPad;
import project.restaurant.model.Product;
import project.restaurant.model.User;

public class TestClass {
	public static void main(String[] args) {
		try {
			UserDao userDao = new UserDao();
			User user = new User();
			
			user.setName("Matheus");
			user.setEmail("matheusgraca08@yahoo.com.br");
			user.setPassword("050278cs");
			
			userDao.register(user);
			
		} catch(Exception err) {
			System.out.println("Erro Merda");
			err.printStackTrace();
		}
	}
}
