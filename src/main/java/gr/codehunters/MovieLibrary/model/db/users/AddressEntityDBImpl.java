package gr.codehunters.MovieLibrary.model.db.users;

import gr.codehunters.MovieLibrary.model.AddressEntity;
import gr.codehunters.MovieLibrary.model.dto.users.AddressEntityDTOImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntityDBImpl implements AddressEntity<Long,AddressEntityDTOImpl,AddressEntityDBImpl> {
	@Id
	@Column(name = "address_id")
  @GeneratedValue
	private Long address_id;
	@Column(name = "user_id")
	private Long personId;
	
	@Column(name = "country")
	private String country;
	@Column(name = "detail_address")
	private String detailAddress;
	@Column(name = "postcode")
	private String postCode;

  public AddressEntityDBImpl(AddressEntityDTOImpl userAddress) {
    this.country=userAddress.getCountry();
    this.postCode=userAddress.getPostCode();
    this.detailAddress=userAddress.getDetailAddress();
    this.setPersonId(personId);
  }

  public Long getId() {
		return address_id;
	}

  @Override
  public void setId(Long id) {
    address_id=id;
  }


	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
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
  public AddressEntityDTOImpl createImp() {
    return new AddressEntityDTOImpl(this);
  }

  @Override
  public AddressEntityDBImpl resynch(AddressEntityDTOImpl addressEntityDTO) {
    this.setCountry(addressEntityDTO.getCountry());
    this.setPostCode(addressEntityDTO.getPostCode());
    this.setDetailAddress(addressEntityDTO.getDetailAddress());
    return this;
  }
}
