package org.zeropage.simplewiki;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.zeropage.simplewiki.crud.PageRepository;
import org.zeropage.simplewiki.model.Page;
import org.zeropage.simplewiki.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-context.xml")
public class PageRepositoryTest {
	@Autowired
	private PageRepository repository;
	Page page;
	
	@Before
	public void setUp(){
		page = new Page("1p");
		page.edit("contents", new User("oldbie", "1234", null));
	}
	
	@Test
	//@Transactional
	public void delete() {
		repository.update(page);
		System.out.println("##########################");
		repository.delete(page);
		Page p = repository.get(page.getTitle());
		assertNull(p);
	}
	
	//@Test
	//@Transactional
	public void update() {
		repository.update(page);
		Page p = repository.get(page.getTitle());
		//assertNotSame(page, p);
		assertEquals(p.getContents(), page.getContents());
		page.edit("new con", new User("newbie", "11", null));
		repository.update(page);
		p = repository.get(page.getTitle());
		assertEquals(p.getRevision(), 2);
	}
	
	@Test
	//@Transactional
	public void get() {
		
	}
	
	@Test
	//@Transactional
	public void getPages() {

	}
}
