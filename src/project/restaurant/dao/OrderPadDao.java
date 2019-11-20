package project.restaurant.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import project.restaurant.model.OrderPad;
import project.restaurant.model.User;

public class OrderPadDao {
	
	private SessionFactory factory = null;

	public OrderPadDao() {
		factory = FactoryBuilerConfig.factoryBuilder();
	}

	public void save(OrderPad orderPad, User user) {
		UserDao userDao = new UserDao();
		try {
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			System.out.println(user.getPassword());
			
			user.getOrderPads().add(orderPad);
			
			session.merge(user);			
			transaction.commit();
		} catch(Exception err) {
			err.printStackTrace();
		}
		
	}

	public List<OrderPad> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderPad get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(OrderPad obj) {
		// TODO Auto-generated method stub
		
	}

	public void delete(OrderPad obj) {
		// TODO Auto-generated method stub
		
	}
	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
}
