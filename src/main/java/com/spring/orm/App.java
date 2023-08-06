package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Databse using SPRING_ORM!");
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/orm/config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
//        Student student = new Student(2234, "Ashish_orm", "Kolkata_orm");
//        int r = studentDao.insert(student);
//        System.out.println("done..." + r);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean go = true;
		while (go) {
			System.out.println("PRESS 1 to add new student");
			System.out.println("PRESS 2 to Display All student");
			System.out.println("PRESS 3 to Get Details of Single student");
			System.out.println("PRESS 4 to to Delete student");
			System.out.println("PRESS 5 to Update student");
			System.out.println("PRESS 6 for Exit");

			try {

				int input = Integer.parseInt(br.readLine());

				switch (input) {
				case 1:
					// add a student
					// taking inputs from users
					System.out.println("Enter user id ");
					int uId = Integer.parseInt(br.readLine());

					System.out.println("Enter user name ");
					String uName = br.readLine();

					System.out.println("Enter user City ");
					String uCity = br.readLine();

					// creating object and setting values
					Student s = new Student();
					s.setStudentId(uId);
					s.setStudentName(uName);
					s.setStudentCity(uCity);
					// saving student object to Db..
					int r = studentDao.insert(s);
					System.out.println(r + " Student added");
					System.out.println("*****************************");
					break;

				case 2:
					// Display all student
					System.out.println("------------------------");
					List<Student> allStudents = studentDao.getAllStudents();
					for (Student st : allStudents) {
						System.out.println("Name :" + st.getStudentName());
						System.out.println("Id : " + st.getStudentId());
						System.out.println("City : " + st.getStudentCity());
						System.out.println("........................");
					}

					break;
				case 3:
					// Get single student data
					System.out.println("........................");
					System.out.println("Enter user id ");
					int userId = Integer.parseInt(br.readLine());
					Student student = studentDao.getStudent(userId);
					System.out.println("Name :" + student.getStudentName());
					System.out.println("Id : " + student.getStudentId());
					System.out.println("City : " + student.getStudentCity());
					System.out.println("........................");

					break;
				case 4:
					// Delete a student

					System.out.println("Enter user id ");
					int userrId = Integer.parseInt(br.readLine());
					studentDao.deleteStudent(userrId);
					System.out.println("Deleteed...");
					break;
				case 5:
					// update a student
					// taking inputs from users
					System.out.println("Enter user id ");
					int uuId = Integer.parseInt(br.readLine());

					System.out.println("Enter user name ");
					String uuName = br.readLine();

					System.out.println("Enter user City ");
					String uuCity = br.readLine();

					// creating object and setting values
					Student ss = new Student(uuId, uuName, uuCity);
					// saving student object to Db..
					studentDao.updateStudent(ss);
					System.out.println("Updated......");

					break;
				case 6:
					// exit
					go = false;
					break;

				default:
					System.out.println("Invalid choice");
					break;
				}

			} catch (Exception e) {
				System.out.println("Invalid Input Try with Another one!..");
				System.out.println(e.getMessage());
			}
		}
		System.out.println("ThankYou For Using my Application ");
		System.out.println("See you Soon!.. ");

	}
}
