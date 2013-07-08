package gr.codehunters.MovieLibrary.model.dto.movies;

import gr.codehunters.MovieLibrary.model.MovieEntity;
import gr.codehunters.MovieLibrary.model.db.movies.MovieEntityDBImpl;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieEntityDTOImpl implements MovieEntity<Long,String,MovieEntityDBImpl,MovieEntityDTOImpl> {

  private Long id;
  @NotNull(message = "Movie name cannot be empty")
  @Size(min = 1, message = "Movie name is invalid")
  private String name;

  public MovieEntityDTOImpl() {
    super();
  }

  public MovieEntityDTOImpl(String name) {
    this();
    this.name = name;
  }

  public MovieEntityDTOImpl(Long id, String name) {
    this(name);
    setId(id);
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }


  @Override
  public MovieEntityDBImpl createImp() {
    return new MovieEntityDBImpl(getName());
  }

  @Override
  public MovieEntityDTOImpl resynch(MovieEntityDBImpl movieEntity) {
    setName(movieEntity.getName());
    setId(movieEntity.getId());
    return this;
  }

  public static MovieEntity create(MovieEntityDBImpl movieEntity) {
    MovieEntity entity=new MovieEntityDTOImpl();
    entity.setName(movieEntity.getName());
    entity.setId(movieEntity.getId());
    return entity;
  }
}
