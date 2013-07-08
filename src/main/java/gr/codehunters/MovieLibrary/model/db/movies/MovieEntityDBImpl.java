package gr.codehunters.MovieLibrary.model.db.movies;

import gr.codehunters.MovieLibrary.model.MovieEntity;
import gr.codehunters.MovieLibrary.model.dto.movies.MovieEntityDTOImpl;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntityDBImpl implements MovieEntity<Long,String,MovieEntityDTOImpl,MovieEntityDBImpl> {

  public MovieEntityDBImpl() {
    super();
  }

  public MovieEntityDBImpl(String name) {
    super();
    this.name = name;
  }

  @Id
 	@Column(name = "movie_id")
  @GeneratedValue
  private Long id;

  @Column(name = "name")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public MovieEntityDTOImpl createImp() {
    return new MovieEntityDTOImpl(this.getId(),this.getName());
  }

  @Override
  public MovieEntityDBImpl resynch(MovieEntityDTOImpl movieEntity) {
    this.setName(movieEntity.getName());
    return this;
  }
}
