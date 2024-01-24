package com.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.infra.HibernateUtil;

public class GenericDao<T, ID extends Serializable> {

	private final Class<T> entityClass;

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T findById(ID id) {
		EntityManager entityManager = HibernateUtil.createEntityManager();
		try {
			return entityManager.find(entityClass, id);
		} finally {
			entityManager.close();
		}
	}

	public List<T> findAll() {
		EntityManager entityManager = HibernateUtil.createEntityManager();
		try {
			return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
		} finally {
			entityManager.close();
		}
	}

	public void save(T entity) {
		EntityManager entityManager = HibernateUtil.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void update(T entity) {
		EntityManager entityManager = HibernateUtil.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			entityManager.merge(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void delete(T entity) {
		EntityManager entityManager = HibernateUtil.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			entityManager.remove(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
