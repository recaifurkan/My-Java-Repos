package com.byrfb.tests;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.byrfb.entities.Telefon;



public class HibernateTests {
	public static void main(String[] args) {
//		addPerson();
//		updateTelefon(2, "Nokia");
		getTelefons();
//		getTelefon(3);
//		deleteTelefon(3);
	}

	static void addPerson() {
		// Personel s�n�f�m�zdan bir instance olu�turuyoruz
		Telefon telefon = new Telefon();

		telefon.setMarka("�phone");
		telefon.setModel("s3");
		// ------------------------------------

		// Bir oturum ba�lataca��z oturumdan �nce ise hibernate config dosyam�z�
		// belirtece�iz
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		/*
		 * Transactionlar ile bir i�lem yar�da kald�ysa veya tam olarak tamamlanad�ysa
		 * t�m ad�mlar ba�a al�n�r veri ve i�lem g�venli�i i�in �nemlidir.K�sacas� ya
		 * hep ya hi� prensibine g�re �al���r.
		 */
		System.out.println("Transaction ba�lat�ld�.");
		session.beginTransaction();
		// personel s�n�f�ndan ald���m�z instance kaydedilmesi i�in g�nderildi
		session.save(telefon);
		session.getTransaction().commit();
		session.close();
		System.out.println("Transaction tamamland�.");
		System.out.println("Veri kaydedildi.");
	}

	static void updateTelefon(Integer EmployeeID, String marka) {
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
		}
	}

	static void getTelefons() {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();

		// here get object
		List<Telefon> list = session.createQuery("FROM Telefon").list();

		tx.commit();
		session.close();

		for (Telefon telefon : list) {
			System.out.println(telefon);
		}

	}

	static void getTelefon(int id) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();

		// here get object
//		List<Telefon> list = session.createQuery("FROM Telefon").list();
		Telefon telefon = (Telefon) session.get(Telefon.class, id);

		tx.commit();
		session.close();

		System.out.println(telefon);

	}

	static void deleteTelefon(int id) {

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		tx = session.beginTransaction();
		Telefon telefon = new Telefon();
		telefon.setId(id);
		session.delete(telefon);

		// here get object
//		List<Telefon> list = session.createQuery("FROM Telefon").list();
//		Telefon telefon =  (Telefon) session.get(Telefon.class, id);

		tx.commit();
		session.close();

		System.out.println(telefon);

	}
}
