package com.demojsf.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.demojsf.HibernateUtil;
import com.demojsf.pojo.OrderItem;

public class OrderItemService {
	private final static SessionFactory factory = HibernateUtil.getFactory();

	public List<OrderItem> getOrderItems() {
		try (Session session = factory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<OrderItem> query = builder.createQuery(OrderItem.class);
			Root<OrderItem> root = query.from(OrderItem.class);
			query.select(root);

			List<OrderItem> list = session.createQuery(query).getResultList();
			return list;
		}
	}

	public boolean addOrSaveOrderItem(OrderItem orderItem) {
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();
				session.saveOrUpdate(orderItem);
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}
		}
		return true;
	}

	public boolean deleteOrderItem(OrderItem orderItem) { // p phải ở trạng thái persistent
		try (Session session = factory.openSession()) {
			try {
				session.getTransaction().begin();
				session.delete(orderItem);
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				return false;
			}
		}

		return true;
	}

	public OrderItem getOrderItemById(int orderItemId) {
		try (Session session = factory.openSession()) {
			return session.get(OrderItem.class, orderItemId);
		}

	}
}
