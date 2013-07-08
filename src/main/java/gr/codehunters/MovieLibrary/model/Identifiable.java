package gr.codehunters.MovieLibrary.model;

public interface Identifiable<T> {
  public T getId();
  public void setId(T id);
}