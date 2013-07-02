package gr.codehunters.MovieLibrary.model.movies;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntityDBImpl {

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
  private long id;

  @Column(name = "name")
  private String name;

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}
