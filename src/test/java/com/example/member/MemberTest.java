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
public class MemberTest {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 2개의 insert 후에 update 문이 추가로 수행 됨
	 */
	@Test
	public void memberTest() {
		// team insert
		Team team = new Team();
		team.setName("team");
		em.persist(team);

		// member insert
		Member member = new Member();
		member.setUsername("userName");
		member.setTeam(team);
		em.persist(member);

		em.flush();
		em.clear();
	}

}
