package gr.codehunters.MovieLibrary.dao;

import gr.codehunters.MovieLibrary.dao.core.Create;
import gr.codehunters.MovieLibrary.dao.core.Delete;
import gr.codehunters.MovieLibrary.dao.core.Find;
import gr.codehunters.MovieLibrary.dao.core.Update;
import gr.codehunters.MovieLibrary.model.MyEntity;
import gr.codehunters.MovieLibrary.util.CustomHibernateDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Repository("entityDAO")
public class EntityDAOImpl extends CustomHibernateDaoSupport implements EntityDAO {

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
    find.setFullyQualifiedClassName(MyEntity.class.getName());
  }



  @Override
  public void save(MyEntity myEntity) {
    if (findById(myEntity.getId())!=null){
    create.execute(myEntity);
    }else {
      update.execute(myEntity);
    }
  }

  @Override
  public void update(MyEntity myEntity) {
    update.execute(myEntity);
  }

  @Override
  public void delete(MyEntity myEntity) {
    delete.execute(myEntity);
  }

  @Override
  public Set<MyEntity> listMyEntities() {
    return find.execute();
  }

  @Override
  public MyEntity findById(int id) {
    List<MyEntity> entities = getHibernateTemplate().findByNamedParam("select u from MyEntity u where u.id = :id", "id", id);
  		if (entities!=null && entities.size()>0)
  		{
  			return entities.get(0);
  		}else{
  			return null;
  		}
  }
}
