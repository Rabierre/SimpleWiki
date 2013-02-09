package org.zeropage.simplewiki.crud;

import java.util.List;
import java.util.Map;

import org.zeropage.simplewiki.model.Page;

public interface PageRepository {
	public void delete(Page page);
	public void update(Page page);
	public Page get(String title);
	public List<Page> getRecentChanges();
}
