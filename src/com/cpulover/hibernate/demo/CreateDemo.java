package com.cpulover.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.demo.entity.Instructor;
import com.cpulover.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create objects
			Instructor instructor1 = new Instructor("Hieu", "Ngo", "justacpulover@gmail.com");
			InstructorDetail detail1 = new InstructorDetail("Justa Cpulover VIVO", "programming");
			instructor1.setInstructorDetail(detail1);

			// start a transaction
			session.beginTransaction();

			// save objects
			session.save(instructor1); // this will also save detail1 because of CascadeType.ALL

			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
