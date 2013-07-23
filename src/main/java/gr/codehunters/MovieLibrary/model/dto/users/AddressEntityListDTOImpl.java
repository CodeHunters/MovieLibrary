package gr.codehunters.MovieLibrary.model.dto.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddressEntityListDTOImpl {
  List<AddressEntityDTOImpl> addressEntityDTOs=new ArrayList<AddressEntityDTOImpl>();
  Long id;

  public List<AddressEntityDTOImpl> getAddressEntityDTOs() {
    return addressEntityDTOs;
  }

  public void setAddressEntityDTOs(Set<AddressEntityDTOImpl> addressEntityDTOs) {
    this.addressEntityDTOs.addAll(addressEntityDTOs);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
