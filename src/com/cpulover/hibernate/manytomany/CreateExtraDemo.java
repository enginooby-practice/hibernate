package com.cpulover.hibernate.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.entity.Course;
import com.cpulover.hibernate.entity.Instructor;
import com.cpulover.hibernate.entity.InstructorDetail;
import com.cpulover.hibernate.entity.Review;
import com.cpulover.hibernate.entity.Student;

public class CreateExtraDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Student.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get student
			int id = 2;
			Student tempStudent = session.get(Student.class, id);
			System.out.println("Get student: " + tempStudent);

			// create objects and establish associations
			Course newCourse1 = new Course("Java 4");
			Course newCourse2 = new Course("Java 5");
			tempStudent.addCourse(newCourse1);
			tempStudent.addCourse(newCourse2);

			// use persist instead of save can save associated courses (when
			// CascadeType.ALL/PERSIST)
			session.persist(tempStudent);

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
