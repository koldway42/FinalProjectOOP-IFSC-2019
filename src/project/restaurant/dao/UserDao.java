package project.restaurant.dao;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public void register(User user) throws IllegalArgumentException{
			Session session = null;
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			String errorMsg = "";
			
			if(user.getName().isEmpty() || user.getName() == null) 
				errorMsg += "Nome não informado \n";
			
			if(user.getEmail().isEmpty() || user.getEmail() == null) 
				errorMsg += "Email não informado \n";
			
			if(user.getPassword().isEmpty() || user.getPassword() == null) 
				errorMsg += "Senha não informada \n";
			
			if(!errorMsg.isEmpty()) throw new IllegalArgumentException(errorMsg);
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			user.setPassword(encoder.encode(user.getPassword()));
			
			user.setCreatedAt(Date.valueOf(LocalDate.now()));
			
			session.save(user);
			session.flush();
			transaction.commit();
			
			if(session != null && session.isOpen()) {
				session.close();
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
	
	public User login(User user) throws NotFoundException, NotAuthorizedException{
		
		Session session = null;
		User userFromDB = null;
		session = factory.openSession();
		
		System.out.println(user.getEmail());
		
		userFromDB = (User) session.createQuery("SELECT e FROM User e WHERE e.email=:email")
				.setParameter("email", user.getEmail())
				.uniqueResult();
		
		if(userFromDB == null) throw new NotFoundException("Email não encontrado");
		

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Boolean Auth = encoder.matches(user.getPassword(), userFromDB.getPassword());
		
		if(!Auth) throw new NotAuthorizedException("Não autorizado");
		
		return userFromDB;
			
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
			session.flush();
			transaction.commit();
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
	}

	public void delete(User user) {
		Session session = null;
		try {
			
			if(user == null) throw new NotFoundException("Comanda não encontrada!");
			
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.delete(user);
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
	
	public List<User> getAllLog() {
		List<User> users = new ArrayList<User>();
		Session session = factory.openSession();
		
		// Enquanto existir dados (registros) no banco de dados, recupera
		List<User> userArray = list();
		for(int i = 0; i < userArray.size(); i++) {
			User userFromList = userArray.get(i);
			User user = new User();
			user.setId(userFromList.getId());
			user.setName(userFromList.getName());
			user.setEmail(userFromList.getEmail());
			user.setCreatedAt(userFromList.getCreatedAt());
			
			System.out.println(user.getFormattedCreatedAt());
			
			users.add(user);
		}
	    
		return users;
	}
	
	public SessionFactory getFactory() {
		return factory;
	}

}
