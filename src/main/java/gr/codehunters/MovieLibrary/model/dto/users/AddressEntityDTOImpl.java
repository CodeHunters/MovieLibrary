package gr.codehunters.MovieLibrary.model.dto.users;

import gr.codehunters.MovieLibrary.model.AddressEntity;
import gr.codehunters.MovieLibrary.model.db.users.AddressEntityDBImpl;

public class AddressEntityDTOImpl implements AddressEntity<Long,AddressEntityDBImpl,AddressEntityDTOImpl> {

	private Long address_id;
	private String country;
	private String detailAddress;
	private String postCode;

  public AddressEntityDTOImpl() {
  }

  public AddressEntityDTOImpl(AddressEntityDBImpl addressEntityDB) {
    this.setId(addressEntityDB.getId());
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
    return new AddressEntityDBImpl(this);
  }

  @Override
  public AddressEntityDTOImpl resynch(AddressEntityDBImpl addressEntityDB) {
    return new AddressEntityDTOImpl(addressEntityDB);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AddressEntityDTOImpl)) return false;

    AddressEntityDTOImpl that = (AddressEntityDTOImpl) o;

    return !(address_id != null ? !address_id.equals(that.address_id) : that.address_id != null) && !(country != null ?
      !country.equals(that.country) : that.country != null) && !(detailAddress != null ?
      !detailAddress.equals(that.detailAddress) : that.detailAddress != null) && !(postCode != null ?
      !postCode.equals(that.postCode) : that.postCode != null);

  }

  @Override
  public int hashCode() {
    int result = address_id != null ? address_id.hashCode() : 0;
    result = 31 * result + (country != null ? country.hashCode() : 0);
    result = 31 * result + (detailAddress != null ? detailAddress.hashCode() : 0);
    result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
    return result;
  }
}
