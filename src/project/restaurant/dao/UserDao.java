package project.restaurant.dao;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mchange.rmi.NotAuthorizedException;

import javassist.NotFoundException;
import project.restaurant.model.OrderPad;
import project.restaurant.model.User;

public class UserDao {
	
	private SessionFactory factory;
	
	public UserDao() {
		factory = FactoryBuilerConfig.factoryBuilder();
	}
	
	public void register(User user) {
			Session session = null;
		try {
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			if(user.getName() == "" || user.getName() == null) 
				throw new IllegalArgumentException("Nome não informado");
			
			if(user.getEmail() == "" || user.getEmail() == null) 
				throw new IllegalArgumentException("Email não informado");
			
			if(user.getPassword() == "" || user.getPassword() == null) 
				throw new IllegalArgumentException("Senha não informada");
			
			user.setOrderPads(new HashSet<OrderPad>());
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			user.setPassword(encoder.encode(user.getPassword()));
			
			session.save(user);
			transaction.commit();
			
		} catch(Exception err) {
			err.printStackTrace();
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public List<User> list() {
		
		List<User> users = null;
		
		try {
			Session session = factory.openSession();
			
			Query query = session.createQuery("from User");
			
			users = query.getResultList();
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return users;
	}
	
	public User login(User user) {
		
		Session session = null;
		User userFromDB = null;
		try {
			session = factory.openSession();
			
			userFromDB = (User) session.createQuery("SELECT e FROM User e WHERE e.email=:email")
					.setParameter("email", user.getEmail())
					.uniqueResult();
			
			if(userFromDB == null) throw new NotFoundException("Email não encontrado");
			

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			Boolean Auth = encoder.matches(user.getPassword(), userFromDB.getPassword());
			
			if(!Auth) throw new NotAuthorizedException("Não autorizado");
			
			return userFromDB;
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return null;
	}
	
	public User get(int id) {
		Session session = null;
		User user = null;
		try {
			session = factory.openSession();
			
			user = (User) session.createQuery("SELECT e FROM User e WHERE e.id=:id")
					.setParameter("id", id)
					.uniqueResult();
			
			
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return user;
		
	}

	public void update(User user) {
		Session session = null;
		User userFromDB = null;
		try {
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			userFromDB = (User) session.get(User.class, user.getId());
			
			userFromDB.setName(user.getName());
			userFromDB.setEmail(user.getEmail());
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			userFromDB.setPassword(encoder.encode(user.getPassword()));
			
			session.update(userFromDB);
			
			transaction.commit();
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
	}

	public void delete(User user) {
			Session session = null;
		try {
			
			if(user == null) throw new NotFoundException("Usuário não Encontrado");
			
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.delete(user);
			
			transaction.commit();
		} catch(Exception err) {
			err.printStackTrace();
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public SessionFactory getFactory() {
		return factory;
	}

}
