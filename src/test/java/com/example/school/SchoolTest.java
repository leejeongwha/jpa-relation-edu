package com.example.school;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.JpaRelationEduApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JpaRelationEduApplication.class)
@Transactional
public class SchoolTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void test() {
		Student student = new Student();
		student.setName("student");
		em.persist(student);

		Teacher teacher = new Teacher();
		teacher.setName("Teacher");

		student.addTeacher(teacher);
		em.persist(teacher);

		em.flush();
		em.clear();

		Student find = em.find(Student.class, student.getId());

		System.out.println("선생님 이름 : " + find.getTeachers().get(0).getName());
		System.out.println("학생 이름 : " + find.getName());

	}

}
