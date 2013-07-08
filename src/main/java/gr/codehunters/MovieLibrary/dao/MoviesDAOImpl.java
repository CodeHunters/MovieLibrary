package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.dao.core.Create;
import gr.codehunters.MovieLibrary.dao.core.Delete;
import gr.codehunters.MovieLibrary.dao.core.Find;
import gr.codehunters.MovieLibrary.dao.core.Update;
import gr.codehunters.MovieLibrary.model.db.movies.MovieEntityDBImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Repository("moviesDAO")
public class MoviesDAOImpl extends CustomHibernateDaoSupport implements MoviesDAO {

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
    find.setFullyQualifiedClassName(MovieEntityDBImpl.class.getName());
  }



  @Override
  public void save(MovieEntityDBImpl myEntity) {
    if (myEntity.getId()==null || findById(myEntity.getId())==null){
    create.execute(myEntity);
    }else {
      update.execute(myEntity);
    }
  }

  @Override
  public void update(MovieEntityDBImpl myEntity) {
    update.execute(myEntity);
  }

  @Override
  public void delete(MovieEntityDBImpl myEntity) {
    delete.execute(myEntity);
  }

  @Override
  public Set<MovieEntityDBImpl> listMyMovies() {
    return find.execute();
  }

  @Override
  public MovieEntityDBImpl findById(long id) {
    List<MovieEntityDBImpl> entities = getHibernateTemplate().findByNamedParam("select u from MovieEntityDBImpl u where u.id = :id", "id", id);
  		if (entities!=null && entities.size()>0)
  		{
  			return entities.get(0);
  		}else{
  			return null;
  		}
  }
}
