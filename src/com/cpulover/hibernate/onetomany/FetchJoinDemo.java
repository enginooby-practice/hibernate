package com.cpulover.hibernate.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.cpulover.hibernate.entity.Course;
import com.cpulover.hibernate.entity.Instructor;
import com.cpulover.hibernate.entity.InstructorDetail;
import com.cpulover.hibernate.entity.Review;
import com.cpulover.hibernate.entity.Student;

public class FetchJoinDemo {

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

			// create query
			Query<Instructor> query = session.createQuery(
					"select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id=:instructorId",
					Instructor.class);

			// set parameter on the query
			int id = 3;
			query.setParameter("instructorId", id);

			// execute query and get object
			Instructor tempInstructor = query.getSingleResult();

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
