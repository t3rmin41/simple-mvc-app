package simple.mvc.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import simple.mvc.app.enums.OrderStatus;
import simple.mvc.jpa.Order;
import simple.mvc.jpa.Product;
import simple.mvc.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        String q = "SELECT p FROM Product p WHERE p.deleted = false";
        TypedQuery<Product> query = em.createQuery(q, Product.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        String q = "SELECT p FROM Product p WHERE p.id = :pid AND p.deleted = false";
        TypedQuery<Product> query = em.createQuery(q, Product.class);
        query.setParameter("pid", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public Product createProduct(Product jpa) {
      return em.merge(jpa);
    }

    @Override
    @Transactional
    public Product updateProduct(Product jpa) {
      return em.merge(jpa);
    }

    @Override
    @Transactional
    public boolean deleteProductById(Long id) {
      String q = "UPDATE Product p SET p.deleted = true WHERE p.id = :pid";
      Query query = em.createQuery(q);
      query.setParameter("pid", id);
      int productQueryStatus = query.executeUpdate();
      
      String orderQuery = "UPDATE Order o SET o.status = :pstatus WHERE o.product = :pproduct";
      Query queryOrder = em.createQuery(orderQuery);
      queryOrder.setParameter("pproduct", getAnyProductById(id));
      queryOrder.setParameter("pstatus", OrderStatus.PRODUCT_DELETED.toString());
      int orderQueryStatus = queryOrder.executeUpdate();
      
      return true;
    }

    @Transactional
    private Product getAnyProductById(Long id) {
      String q = "SELECT p FROM Product p WHERE p.id = :pid";
      TypedQuery<Product> query = em.createQuery(q, Product.class);
      query.setParameter("pid", id);
      return query.getSingleResult();
  }
}
