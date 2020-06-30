package com.cpulover.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.demo.entity.Instructor;
import com.cpulover.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get object by id
			int id = 1;
			Instructor tempInstructor = session.get(Instructor.class, id);
			System.out.println("Get instructor: " + tempInstructor);

			// delete object
			if (tempInstructor != null) {
				session.delete(tempInstructor); // this will also delete the associated "instructorDetail" because of
												// CascadeType.ALL
			}

			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
