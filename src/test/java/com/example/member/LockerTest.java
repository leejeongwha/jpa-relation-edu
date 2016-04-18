package com.example.member;

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
public class LockerTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void test() {
		Locker locker = new Locker();
		locker.setName("자물쇠");
		em.persist(locker);

		Member member = new Member();
		member.setLocker(locker);
		member.setUsername("leejeongwha");

		locker.setMember(member);
		em.persist(member);

		em.flush();
		em.clear();

		Member find = em.find(Member.class, member.getId());

		System.out.println(find.getUsername() + " : " + find.getLocker().getName());

	}

}
