package gr.codehunters.MovieLibrary.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import gr.codehunters.MovieLibrary.model.db.users.UserEntityDBImpl;
import gr.codehunters.MovieLibrary.dao.core.Create;
import gr.codehunters.MovieLibrary.dao.core.Delete;
import gr.codehunters.MovieLibrary.dao.core.Find;
import gr.codehunters.MovieLibrary.dao.core.Update;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository("entityDao")
public class UserDAOImpl extends CustomHibernateDaoSupport implements UserDAO {

  @Autowired
  Find find;
  @Autowired
  Create create;
  @Autowired
  Delete delete;
  @Autowired
  Update update;

  @PostConstruct
  public void postConstruct(){
    find.setFullyQualifiedClassName(UserEntityDBImpl.class.getName());
  }

  @Override
	public void save(UserEntityDBImpl user) {
		create.execute(user);
	}

	@Override
	public void update(UserEntityDBImpl user) {
		update.execute(user);
	}

	@Override
	public void delete(UserEntityDBImpl user) {
		delete.execute(user);
	}

  @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
	@Override
	public UserEntityDBImpl findByUserName(String userName) {
		List find = getHibernateTemplate().findByNamedParam("select u from UserEntityDBImpl u where u.userName = :un", "un", userName);
		List<UserEntityDBImpl> users = find;
		if (users!=null && users.size()>0)
		{
			return users.get(0);
		}else{
			return null;
		}
	}

  @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
 	@Override
  public UserEntityDBImpl findById(Long id){
 		List find = getHibernateTemplate().findByNamedParam("select u from UserEntityDBImpl u where u.user_id = :uid", "uid", id);
 		List<UserEntityDBImpl> users = find;
 		if (users!=null && users.size()>0)
 		{
 			return users.get(0);
 		}else{
 			return null;
 		}
 	}

  @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
 	@Override
  public void deleteUserById(Long id){
    getHibernateTemplate().delete(getHibernateTemplate().get(UserEntityDBImpl.class,id));
 	}


	
  @SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public Set<UserEntityDBImpl> listUser(Integer firstResult,Integer maxResults,String shortingColumn,Boolean asc) {
	    if (firstResult==null) firstResult=0;
	    if (maxResults==null) maxResults=10;
	    List<Order> orders=new ArrayList<Order>();
	    if (asc!=null && shortingColumn!=null){
	    orders.add(asc?Order.asc(shortingColumn):Order.desc(shortingColumn));
	    }
	    return find.execute(firstResult,maxResults,orders,null);
		}

  @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Set<UserEntityDBImpl> listUser() {
    return find.execute();
	}

  @Override
  public long countUsers() {
    return find.count();
  }

}
