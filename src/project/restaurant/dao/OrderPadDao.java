package project.restaurant.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javassist.NotFoundException;
import project.restaurant.model.OrderPad;
import project.restaurant.model.User;

public class OrderPadDao {
	
	private SessionFactory factory = null;

	public OrderPadDao() {
		factory = FactoryBuilerConfig.factoryBuilder();
	}

	public void save(OrderPad orderPad) {
		UserDao userDao = new UserDao();
		try {
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			User user = orderPad.getUser();
			
			Set<OrderPad> orderPads = new HashSet<OrderPad>();
			
			orderPads.add(orderPad);
			
			orderPad.setId(null);
			orderPad.setCreatedAt(Date.valueOf(LocalDate.now()));
			
			user.setOrderPads(orderPads);
			
			orderPad.setUser(user);
			session.flush(); 
			session.merge(orderPad);
			transaction.commit();
		} catch(Exception err) {
			err.printStackTrace();
		}
		
	}

	public List<OrderPad> list() {
		List<OrderPad> orderPads = null;
		try {
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			Query query = session.createQuery("from OrderPad");
			
			orderPads = query.getResultList();
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return orderPads;
		
	}

	public OrderPad get(int id) {
		Session session = null;
		OrderPad orderPad = null;
		try {
			session = factory.openSession();
			
			orderPad = (OrderPad) session.createQuery("SELECT e FROM OrderPad e WHERE e.id=:id")
					.setParameter("id", id)
					.uniqueResult();
			
			
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return orderPad;
	}
	
	public List<OrderPad> getByUserId(int id) {
		Session session = null;
		List<OrderPad> orderPads = null;
		try {
			session = factory.openSession();
			
			Query query = session.createQuery("from OrderPad where user.id=:id");
			query.setParameter("id", id);
			
			orderPads = query.getResultList();
					
			
			
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return orderPads;
	}

	public void update(OrderPad orderPad) {
		Session session = null;
		OrderPad orderPadFromDB = null;
		try {
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			orderPadFromDB = (OrderPad) session.get(OrderPad.class, orderPad.getId());
			
			orderPadFromDB.setTotal(orderPad.getTotal());
			orderPadFromDB.setPaid(orderPad.getPaid());;
			
			session.merge(orderPadFromDB);
			session.flush();
			transaction.commit();
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
	}

	public void delete(OrderPad orderPad) {
		Session session = null;
		try {
			
			if(orderPad == null) throw new NotFoundException("Comanda não encontrada!");
			
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.delete(orderPad);
			session.flush();
			transaction.commit();
		} catch(Exception err) {
			err.printStackTrace();
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}
	
	public Map<Integer, Double> TenHighestOrderPads() {
		Map<Integer, Double> result = new HashMap();
		Session session = factory.openSession();
		
		
		Query query = session.createQuery("from OrderPad order by Total desc").setMaxResults(10);

		List<OrderPad> orderPads = query.getResultList();
		
		for(int i = 0; i < orderPads.size(); i++) {
			result.put((i + 1), orderPads.get(i).getTotal());
		}
		
		return result;
	}
	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
}
