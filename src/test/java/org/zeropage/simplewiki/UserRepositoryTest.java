package org.zeropage.simplewiki;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zeropage.simplewiki.crud.UserRepository;
import org.zeropage.simplewiki.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-context.xml")
public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;
	User user;
	
	@Before
	public void setUp(){
		user = new User("linflus", "1234", "linflus@linfl.us");
	}
	
	@Test
	public void delete() {
		repository.update(user);
		assertNotNull(repository.get(user.getId()));
		repository.delete(user);
		assertNull(repository.get(user.getId()));
	}
	
	@Test
	public void update() {
		assertNull(repository.get(user.getId()));
		repository.update(user);
		User actual = repository.get(user.getId());
		assertEquals(user.getId(), actual.getId());
	}
}
