package com.cpulover.hibernate.manytomany;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.entity.Course;
import com.cpulover.hibernate.entity.Instructor;
import com.cpulover.hibernate.entity.InstructorDetail;
import com.cpulover.hibernate.entity.Review;
import com.cpulover.hibernate.entity.Student;

public class GetDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();

			// get student
			int studentId = 1;
			Student tempStudent = session.get(Student.class, studentId);
			System.out.println("Get Student: " + tempStudent);

			// get associated objects
			System.out.println("List of associated courses: ");
			System.out.println(tempStudent.getCourses());
			
			//get course
			int courseId=10;
			Course tempCourse =session.get(Course.class, courseId);
			System.out.println("Get course: "+tempCourse);
			
			//get associated objects
			System.out.println("List of associated students: ");
			System.out.println(tempCourse.getStudents());

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
