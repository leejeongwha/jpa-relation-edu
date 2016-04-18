package com.example.phone;

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
public class PhoneTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void test() {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName("samsung");
		em.persist(manufacturer);

		Phone phone = new Phone();
		phone.setName("galaxy7");
		phone.setManufacturer(manufacturer);
		em.persist(phone);

		em.flush();
		em.clear();

		Phone find = em.find(Phone.class, phone.getId());

		System.out.println(find.getName() + " : " + find.getManufacturer().getName());

		Manufacturer find2 = em.find(Manufacturer.class, manufacturer.getId());

		System.out.println(find2.getPhones().get(0).getName() + " : " + find2.getName());

	}

}
