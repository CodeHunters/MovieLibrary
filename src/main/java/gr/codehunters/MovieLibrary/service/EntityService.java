package gr.codehunters.MovieLibrary.service;

import gr.codehunters.MovieLibrary.dao.EntityDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.codehunters.MovieLibrary.model.MyEntity;

@Service
public class EntityService {
	
	private static final Logger logger = LoggerFactory.getLogger(EntityService.class);
	
  @Autowired
  EntityDAO entityDAO;
	
	public MyEntity findEntity(String id) {
		if("new".equals(id)) {
			return new MyEntity();
		} else {
			return entityDAO.findById(new Integer(id));
		}
	}

	public MyEntity save(MyEntity entity) {
    entityDAO.save(entity);
		logger.info("entity saved: ");
		return entity;

	}
	
	@Transactional
	public boolean delete(String entityId) {
		MyEntity entity = entityDAO.findById(new Integer(entityId));
		if(entity==null) {
			return false;
		}
		entityDAO.delete(entity);
		return true;
	}

}
