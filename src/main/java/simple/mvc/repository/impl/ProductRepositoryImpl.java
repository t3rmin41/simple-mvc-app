package simple.mvc.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import simple.mvc.jpa.Product;
import simple.mvc.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        String q = "SELECT p FROM Product p";
        TypedQuery<Product> query = em.createQuery(q, Product.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Product getProductById(Long id) {
        String q = "SELECT p FROM Product p WHERE p.id = :pid";
        TypedQuery<Product> query = em.createQuery(q, Product.class);
        query.setParameter("pid", id);
        return query.getSingleResult();
    }

}
