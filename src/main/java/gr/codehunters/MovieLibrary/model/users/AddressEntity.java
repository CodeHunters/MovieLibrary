package gr.codehunters.MovieLibrary.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adress")
public class AddressEntity {
	public static final String NAMED_QUERY_CREATE_TABLE = "ADDRESS.create_table";
	public static final String NAMED_QUERY_INSERT_ONE = "ADDRESS.insert_one";
	public static final String NAMED_QUERY_DELETE_ONE = "ADDRESS.delete_one";
	public static final String NAMED_QUERY_UPDATE_ONE = "ADDRESS.update_one";
	public static final String NAMED_QUERY_SELECT_ONE = "ADDRESS.select_one";
	public static final String NAMED_QUERY_SELECT_ALL = "ADDRESS.select_all";
	public static final String NAMED_QUERY_SELECT_BY_CONDITION = "ADDRESS.select_by_condition";
	@Id
	@Column(name = "address_id")
    @GeneratedValue
	private long address_id;
	@Column(name = "user_id")
	private long personId;
	
	@Column(name = "country")
	private String country;
	@Column(name = "detail_address")
	private String detailAddress;
	@Column(name = "postcode")
	private String postCode;
 	
	public long getId() {
		return address_id;
	}

	public void setId(long id) {
		address_id = id;
	}

	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
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
}
