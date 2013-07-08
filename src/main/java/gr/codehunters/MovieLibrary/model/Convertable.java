package gr.codehunters.MovieLibrary.model;

public interface Convertable<T,V> {
    public T createImp();
    public V resynch(T t);
}