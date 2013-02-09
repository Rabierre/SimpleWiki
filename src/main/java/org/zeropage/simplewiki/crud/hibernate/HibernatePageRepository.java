package org.zeropage.simplewiki.crud.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zeropage.simplewiki.crud.PageRepository;
import org.zeropage.simplewiki.model.Page;

@Repository
@Transactional
public class HibernatePageRepository implements PageRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void delete(Page page) {
		sessionFactory.getCurrentSession().delete(page);
	}

	@Override
	public void update(Page page) {
		// TODO check page before save
		sessionFactory.getCurrentSession().saveOrUpdate(page);
	}

	@Override
	public Page get(String title) {
		List<Page> pages = sessionFactory.getCurrentSession().createCriteria(Page.class).add(Restrictions.eq("title", title)).list();
		if(pages.size() > 0){
			return pages.get(0);
		}
		return null;
	}

	@Override
	public List<Page> getRecentChanges() {
		//TODO : 理쒓렐 �섏씠吏�荑쇰━ 留뚮뱾湲�
		List<Page> pages = sessionFactory.getCurrentSession().createCriteria(Page.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return pages;
	}
}
