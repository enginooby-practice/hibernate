package com.cpulover.hibernate.onetomany;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.entity.Course;
import com.cpulover.hibernate.entity.Instructor;
import com.cpulover.hibernate.entity.InstructorDetail;
import com.cpulover.hibernate.entity.Review;
import com.cpulover.hibernate.entity.Student;

public class EagerLazyDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get object
			int id = 3;
			Instructor tempInstructor = session.get(Instructor.class, id);
			System.out.println("Get intrustor: " + tempInstructor);

			// Call getter method while session is still open to resolve Lazy loading issue
			System.out.println(tempInstructor.getCourses());

			// commit transaction
			session.getTransaction().commit();

			session.close();
			System.out.println("Session is closed!");
			// test loading FetchType.LAZY after closing session
			System.out.println(tempInstructor.getCourses());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
