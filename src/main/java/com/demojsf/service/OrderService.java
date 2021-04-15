package com.demojsf.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.demojsf.HibernateUtil;
import com.demojsf.pojo.Order;

public class OrderService {
	private final static SessionFactory factory = HibernateUtil.getFactory();

	public List<Order> getOrders() {
		try (Session session = factory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Order> query = builder.createQuery(Order.class);
			Root<Order> root = query.from(Order.class);
			query.select(root);

			List<Order> list = session.createQuery(query).getResultList();
			return list;
		}
	}

	public boolean addOrSaveOrder(Order order) {
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();
				session.saveOrUpdate(order);
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}
		}
		return true;
	}

	public boolean deleteOrder(Order order) { // p phải ở trạng thái persistent
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();
				session.delete(order);
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}
		}

		return true;
	}

	public Order getOrderById(int orderId) {
		try (Session session = factory.openSession()) {
			return session.get(Order.class, orderId);
		}

	}
}
