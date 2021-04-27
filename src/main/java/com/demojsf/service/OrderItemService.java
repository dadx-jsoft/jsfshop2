package com.demojsf.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.demojsf.HibernateUtil;
import com.demojsf.pojo.Order;
import com.demojsf.pojo.OrderItem;

public class OrderItemService {
	private final static SessionFactory factory = HibernateUtil.getFactory();

	public boolean deleteOrderItem(OrderItem orderItem) {
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
	
	public List<OrderItem> getOrderItemsByOrderId(Order o) {
		try (Session session = factory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<OrderItem> query = builder.createQuery(OrderItem.class);
			Root<OrderItem> root = query.from(OrderItem.class);
			query.select(root);
			query.where(builder.equal(root.get("order"), o));

			List<OrderItem> list = session.createQuery(query).getResultList();
			return list;
		}
	}

}
