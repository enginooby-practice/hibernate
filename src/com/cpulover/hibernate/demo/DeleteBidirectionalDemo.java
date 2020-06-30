package com.cpulover.hibernate.demo;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.demo.entity.Instructor;
import com.cpulover.hibernate.demo.entity.InstructorDetail;

public class DeleteBidirectionalDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get object
			int id = 3;
			InstructorDetail tempDetail = session.get(InstructorDetail.class, id);
			System.out.println("Get detail: " + tempDetail);
			
			//break bi-directional link to delete only instructorDetail without instructor
			tempDetail.getInstructor().setInstructorDetail(null);

			// delete object
			if (tempDetail != null) {
				session.delete(tempDetail); // this will also delete the associated "instructor" if
											// CascadeType.ALL or CascadeType.REMOVE
			}

			// commit transaction
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
