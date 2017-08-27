package simple.mvc.repository.impl;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import simple.mvc.jpa.Order;
import simple.mvc.repository.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private static Logger log = LoggerFactory.getLogger(OrderRepositoryImpl.class);
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public Order createOrder(Order jpa) {
        Calendar cal = Calendar.getInstance();
        //cal.set(Calendar.HOUR_OF_DAY, 0);
        //cal.set(Calendar.MINUTE, 0);
        //cal.set(Calendar.SECOND, 0);
        jpa.setCreated(cal.getTime());
        return em.merge(jpa);
    }

    @Override
    @Transactional
    public List<Order> getOrders() {
        String q = "SELECT o FROM Order o";
        TypedQuery<Order> query = em.createQuery(q, Order.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Order getOrderById(Long id) {
        String q = "SELECT o FROM Order o WHERE o.id = :pid";
        TypedQuery<Order> query = em.createQuery(q, Order.class);
        query.setParameter("pid", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public Order updateOrder(Order jpa) {
        Calendar cal = Calendar.getInstance();
        //cal.set(Calendar.HOUR, 0);
        //cal.set(Calendar.MINUTE, 0);
        //cal.set(Calendar.SECOND, 0);
        jpa.setUpdated(cal.getTime());
        return em.merge(jpa);
    }

    @Override
    @Transactional
    public boolean deleteOrderById(Long id) {
        Order jpa = this.getOrderById(id);
        boolean result = false;
        try {
            em.remove(jpa);
            result = true;
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (TransactionRequiredException e) {
            log.error(e.getMessage());
        }
        return result;
    }

}
