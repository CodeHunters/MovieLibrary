package gr.codehunters.MovieLibrary.model.dto.users;

import gr.codehunters.MovieLibrary.model.AddressEntity;
import gr.codehunters.MovieLibrary.model.db.users.AddressEntityDBImpl;

public class AddressEntityDTOImpl implements AddressEntity<Long,AddressEntityDBImpl,AddressEntityDTOImpl> {

	private Long address_id;
	private Long personId;
	private String country;
	private String detailAddress;
	private String postCode;

  public AddressEntityDTOImpl(AddressEntityDBImpl addressEntityDB) {
    this.setId(addressEntityDB.getId());
    this.setPersonId(addressEntityDB.getPersonId());
    this.setCountry(addressEntityDB.getCountry());
    this.setDetailAddress(addressEntityDB.getDetailAddress());
    this.setPostCode(addressEntityDB.getPostCode());
  }

  public Long getId() {
		return address_id;
	}

  @Override
  public void setId(Long id) {
    address_id = id;
  }

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

  @Override
  public AddressEntityDBImpl createImp() {
    return null;
  }

  @Override
  public AddressEntityDTOImpl resynch(AddressEntityDBImpl addressEntityDB) {
    return new AddressEntityDTOImpl(addressEntityDB);
  }
}
