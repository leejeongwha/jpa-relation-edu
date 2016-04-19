package com.example.cascade;

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
public class CascadeTest {
	@PersistenceContext
	private EntityManager em;

	/**
	 * CascadeType.PERSIST, CascadeType.REMOVE 테스트
	 */
	@Test
	public void cascadeTest() {
		Team team = new Team();
		team.setName("team");

		// team을 persist를 통해 따로 저장하지 않아도 member 저장할때 team까지 자동으로 insert 됨
		Member member = new Member();
		member.setName("user");
		member.setTeam(team);
		em.persist(member);

		// member 삭제 시 team도 delete 됨
		Member find = em.find(Member.class, member.getId());
		em.remove(find);
		em.flush();

		em.clear();
	}

	/**
	 * Member에서 Team을 조회할때는 default Fetch전략이 Eager이다. 그러나 Member쪽에
	 * CascadeType.LAZY를 주면 조회할때 select쿼리에 join이 발생하지 않는다.
	 */
	@Test
	public void fetchTest() {
		Team team = new Team();
		team.setName("team");

		Member member = new Member();
		member.setName("user");
		member.setTeam(team);
		em.persist(member);
		em.flush();
		em.clear();

		Member find = em.find(Member.class, member.getId());

		System.out.println(find.getName() + " : " + find.getTeam().getName());
	}

}
