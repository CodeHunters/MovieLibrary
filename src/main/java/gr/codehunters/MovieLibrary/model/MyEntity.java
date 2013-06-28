package gr.codehunters.MovieLibrary.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class MyEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
	private String name;

	// TODO: Add additional commented-out sample fields
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
