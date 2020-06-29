package com.cpulover.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cpulover.hibernate.demo.entity.Student;

public class CreateStudentTest {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		Session session=factory.getCurrentSession();
		
		try {
			//Student student=new Student("Hiem","Ngo","cpulover@gmail.com");
			session.beginTransaction();
			//session.save(student);
			System.out.println("hihi");
			Student retrievedStudent=session.get(Student.class, 2);
			System.out.println(retrievedStudent);
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

}
