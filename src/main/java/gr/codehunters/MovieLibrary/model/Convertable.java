/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.model;

public interface Convertable<T,V> {
    public T createImp();
    public V resynch(T t);
}