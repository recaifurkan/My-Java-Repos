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
		// Personel sýnýfýmýzdan bir instance oluþturuyoruz
		Telefon telefon = new Telefon();

		telefon.setMarka("Ýphone");
		telefon.setModel("s3");
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
		System.out.println("Transaction tamamlandý.");
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
