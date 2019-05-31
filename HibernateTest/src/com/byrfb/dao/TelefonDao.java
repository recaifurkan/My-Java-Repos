package com.byrfb.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.byrfb.entities.Telefon;



public class TelefonDao {

	public void addTelefon(Telefon telefon) {
		// Personel sýnýfýmýzdan bir instance oluþturuyoruz

		// ------------------------------------

		// Bir oturum baþlatacaðýz oturumdan önce ise hibernate config dosyamýzý
		// belirteceðiz
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		/*
		 * Transactionlar ile bir iþlem yarýda kaldýysa veya tam olarak tamamlanadýysa
		 * tüm adýmlar baþa alýnýr veri ve iþlem güvenliði için önemlidir.Kýsacasý ya
		 * hep ya hiç prensibine göre çalýþýr.
		 */
		System.out.println("Transaction baþlatýldý.");
		session.beginTransaction();
		// personel sýnýfýndan aldýðýmýz instance kaydedilmesi için gönderildi
		session.save(telefon);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		System.out.println("Transaction tamamlandý.");
		System.out.println("Veri kaydedildi.");
	}

	public void updateTelefon(Integer EmployeeID, String marka) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Telefon telefon = (Telefon) session.get(Telefon.class, EmployeeID);
			telefon.setMarka(marka);
			session.update(telefon);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public void updateTelefon(Telefon telefon) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.update(telefon);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public List<Telefon> getTelefons() {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();

		// here get object
		List<Telefon> list = session.createQuery("FROM Telefon").list();

		tx.commit();
		session.close();
		sessionFactory.close();

		for (Telefon telefon : list) {
			System.out.println(telefon);
		}
		return list;

	}

	public Telefon getTelefon(int id) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();

		// here get object
//		List<Telefon> list = session.createQuery("FROM Telefon").list();
		Telefon telefon = (Telefon) session.get(Telefon.class, id);

		tx.commit();
		session.close();
		sessionFactory.close();

		System.out.println(telefon);
		return telefon;

	}

	public void deleteTelefon(Telefon telefon) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();

		session.delete(telefon);

		// here get object
//		List<Telefon> list = session.createQuery("FROM Telefon").list();
//		Telefon telefon =  (Telefon) session.get(Telefon.class, id);

		tx.commit();
		session.close();
		sessionFactory.close();

		System.out.println(telefon);

	}

}
