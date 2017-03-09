package com.muletut.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muletut.entity.Post;
import com.muletut.entity.Reference;
import com.muletut.entity.Tutorial;
import com.muletut.exceptions.MuletutException;

@Service("daoService")
public class DaoServiceImpl implements DaoService {
	@Autowired
	SessionFactory sessionFactory;

	/************* Add Index Menu Items ***************/
	public boolean addMenuItems(String[] menuItemsNames) throws MuletutException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query sizeQuery = session.createQuery("SELECT COUNT(*) FROM Tutorial T");
			Long dataSize = (Long) (sizeQuery.uniqueResult());
			if (dataSize != menuItemsNames.length) {
				session.createQuery("DELETE FROM Tutorial").executeUpdate();
				for (int i = 0; i < menuItemsNames.length; i++) {
					Tutorial object = new Tutorial();
					object.setName(menuItemsNames[i]);
					session.save(object);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
				transaction.commit();
			}
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new MuletutException("Can't connect with database", e);

		} finally {
			session.close();
		}
	}

	/************* Get Index Menu Items ***************/
	public ArrayList<String> getIndexMenu() throws MuletutException {
		String table = "Tutorial";
		return getMenu(table);
	}

	/************* Add Reference Menu Items ***************/
	public boolean addReferenceMenuItems(String[] referenceMenuItemsNames) throws MuletutException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query sizeQuery = session.createQuery("SELECT COUNT(*) FROM Reference R");
			Long dataSize = (Long) (sizeQuery.uniqueResult());
			if (dataSize != referenceMenuItemsNames.length) {
				session.createQuery("DELETE FROM Reference").executeUpdate();
				for (int i = 0; i < referenceMenuItemsNames.length; i++) {
					Reference object = new Reference();
					object.setName(referenceMenuItemsNames[i]);
					session.save(object);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
				transaction.commit();
			}
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new MuletutException("Can't connect with database", e);

		} finally {
			session.close();
		}
	}

	/************* Get Reference Menu Items ***************/
	public ArrayList<String> getReferenceMenu() throws MuletutException {
		String table = "Reference";
		return getMenu(table);
	}

	/************* Add Blog Posts Names ***************/
	public boolean addBlogPosts(String[] blogPostsNames) throws MuletutException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query sizeQuery = session.createQuery("SELECT COUNT(*) FROM Post P");
			Long dataSize = (Long) (sizeQuery.uniqueResult());
			if (dataSize != blogPostsNames.length) {
				session.createQuery("DELETE FROM Post").executeUpdate();
				for (int i = 0; i < blogPostsNames.length; i++) {
					Post object = new Post();
					object.setName(blogPostsNames[i]);
					session.save(object);
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
				transaction.commit();
			}
			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new MuletutException("Can't connect with database", e);

		} finally {
			session.close();
		}
	}

	/************* Get Blog Posts ***************/
	public ArrayList<String> getBlogPosts() throws MuletutException {
		String table = "Post";
		return getMenu(table);
	}

	/************* Get Blog Posts For Single ***************/
	public ArrayList<String> getBlogPostsForSingle(String postName) throws MuletutException {
		String table = "Post";
		return getMenu(table, postName);
	}

	/************* Get Menu Items ***************/
	private ArrayList<String> getMenu(String table, String... values) throws MuletutException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List<String> items = new ArrayList<String>();
			String query;
			Query itemQuery;
			query = "SELECT T.name FROM " + table + " T";
			itemQuery = session.createQuery(query);
			if (values.length != 0) {
				query = "SELECT T.name FROM " + table + " T WHERE T.name != '" + values[0] + "' ORDER BY RAND()";
				itemQuery = session.createQuery(query).setMaxResults(10);
			}
			System.out.println("ABHAY"+query);
			for (Object item : itemQuery.list()) {
				items.add(item.toString().replaceAll("\\s", "-"));
			}
			transaction.commit();
			return (ArrayList<String>) items;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new MuletutException("Can't connect with database", e);
		} finally {
			session.close();
		}
	}
}
