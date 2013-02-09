package org.zeropage.simplewiki.crud.simple;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.zeropage.simplewiki.crud.PageRepository;
import org.zeropage.simplewiki.model.Page;

//@Repository
public class SimplePageRepository implements PageRepository {
	Map<String, Page> pages = new LinkedHashMap<String, Page>();
	List<Page> recent = new LinkedList<Page>();
	
	public void delete(Page page) {
		pages.remove(page.getTitle());
		recent.add(page);
	}

	public void update(Page page) {
		pages.put(page.getTitle(), page);
		recent.add(page);
	}

	public Page get(String title) {
	Page page = pages.get(title); 
		if(page == null){
			page = new Page(title);
		}
		return page;
	}

	@Override
	public List<Page> getRecentChanges() {
		return recent;
	}	
}