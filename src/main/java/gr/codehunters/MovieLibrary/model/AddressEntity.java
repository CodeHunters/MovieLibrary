package gr.codehunters.MovieLibrary.model;

public interface AddressEntity<T,V,G> extends Identifiable<T>,Convertable<V,G>{
 	public String getCountry();
 	public void setCountry(String country);
 	public String getDetailAddress();
 	public void setDetailAddress(String detailAddress);
 	public String getPostCode();
 	public void setPostCode(String postCode);
}