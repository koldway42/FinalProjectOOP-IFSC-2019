package project.restaurant.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javassist.NotFoundException;
import project.restaurant.model.OrderPad;
import project.restaurant.model.Product;
import project.restaurant.model.User;

public class ProductDao {
	
	private SessionFactory factory;
	
	public ProductDao() {
		factory = FactoryBuilerConfig.factoryBuilder();
	}
	
	public void save(Product product) {
		try {
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			session.save(product);
			session.flush(); 
			transaction.commit();
		} catch(Exception err) {
			err.printStackTrace();
		}
		
	}
	
	public List<Product> list() {
		
		List<Product> products = null;
		
		try {
			Session session = factory.openSession();
			
			Query query = session.createQuery("from Product");
			
			products = query.getResultList();
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return products;
	}
	
	public Product get(int id) {
		Session session = null;
		Product product = null;
		try {
			session = factory.openSession();
			
			product = (Product) session.createQuery("SELECT e FROM Product e WHERE e.id=:id")
					.setParameter("id", id)
					.uniqueResult();
			
			
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
		return product;
		
	}

	public void update(Product product) {
		Session session = null;
		Product productFromDB = null;
		try {
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			productFromDB = (Product) session.get(Product.class, product.getId());
			
			productFromDB.setName(product.getName());
			productFromDB.setPrice(product.getPrice());
			
			
			session.update(productFromDB);
			session.flush();
			transaction.commit();
			
		} catch(Exception err) {
			err.printStackTrace();
		}
		
	}

	public void delete(Product product) {
		Session session = null;
		try {
			
			if(product == null) throw new NotFoundException("Produto não encontrada!");
			
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			session.delete(product);
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
}
