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
import project.restaurant.dao.UserDao;
import project.restaurant.model.OrderPad;
import project.restaurant.model.User;

public class TestClass {
	public static void main(String[] args) {
		try {
			
			User user = new User();
			UserDao userDao = new UserDao();
			
			user = userDao.get(1011);
			
			OrderPad orderPad = new OrderPad();
			OrderPadDao orderPadDao = new OrderPadDao();
			
			orderPad.setTotal(2500.00);
			orderPad.setPaid(true);
			orderPad.setCreatedAt(Date.valueOf(LocalDate.now()));
			
			orderPadDao.save(orderPad, user);
			
		} catch(Exception err) {
			System.out.println("Erro Merda");
			err.printStackTrace();
		}
	}
}
