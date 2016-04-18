package com.example.person;

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
public class PersonTest {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 단방향 다대일 연관관계
	 */
	@Test
	public void test() {
		Job job = new Job();
		job.setName("teacher");
		em.persist(job);

		Person person = new Person();
		person.setName("leejeongwha");
		person.setJob(job);
		em.persist(person);

		em.flush();
		em.clear();

		Person find = em.find(Person.class, person.getId());

		System.out.println("person 이름 : " + find.getName());
		System.out.println("job 이름 : " + find.getJob().getName());
	}

}
