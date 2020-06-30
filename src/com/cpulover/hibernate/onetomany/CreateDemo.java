package com.cpulover.hibernate.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.entity.Course;
import com.cpulover.hibernate.entity.Instructor;
import com.cpulover.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// create objects and establish associations
			Instructor instructor1 = new Instructor("Hieu", "Ngo", "justacpulover@gmail.com");
			InstructorDetail detail1 = new InstructorDetail("Justa Cpulover VIVO", "programming");
			Course course1 = new Course("Java 2");
			Course course2 = new Course("Spring 2");
			instructor1.setInstructorDetail(detail1);
			instructor1.addCourse(course1);
			instructor1.addCourse(course2);

			// start a transaction
			session.beginTransaction();

			// save objects
			session.persist(instructor1); // this will also save detail1 if CascadeType.ALL/PERSIST
			// use persist instead of save can save associated course also (course1,
			// course2)

			instructor1.getCourses().forEach(System.out::println);

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
