package org.zeropage.simplewiki.crud.hibernate; 

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zeropage.simplewiki.crud.UserRepository;
import org.zeropage.simplewiki.model.User;

@Transactional
@Repository
public class HibernateUserRepository implements UserRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	//현재 아무데서도 안 씀
	@Override
	public void delete(User user) {
		update(user);
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	public User get(String id) {
		List<User> users = sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("id", id)).list();
		
		if(users.size() > 0){
			return users.get(0);
		}
		return null;
	}
}
