package com.cpulover.hibernate.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.entity.Course;
import com.cpulover.hibernate.entity.Instructor;
import com.cpulover.hibernate.entity.InstructorDetail;
import com.cpulover.hibernate.entity.Review;
import com.cpulover.hibernate.entity.Student;

public class DeleteDemo {

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

			// get object by id
			int id = 4;
			Course tempCourse = session.get(Course.class, id);
			System.out.println("Get course: " + tempCourse);

			// delete object
			if (tempCourse != null) {
				session.delete(tempCourse); // this will not delete the associated "instructor" if no
											// CascadeType.ALL/REMOVE
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
