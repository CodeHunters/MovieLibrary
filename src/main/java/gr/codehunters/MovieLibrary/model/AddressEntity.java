/*
 *  Copyright 2013 ADVA Optical Networking SE. All rights reserved.
 *
 *  Owner: arongas
 *
 *  $Id: $
 */
package gr.codehunters.MovieLibrary.model;

public interface AddressEntity<T,V,G> extends Identifiable<T>,Convertable<V,G>{
  public Long getPersonId();
 	public void setPersonId(Long personId);
 	public String getCountry();
 	public void setCountry(String country);
 	public String getDetailAddress();
 	public void setDetailAddress(String detailAddress);
 	public String getPostCode();
 	public void setPostCode(String postCode);
}