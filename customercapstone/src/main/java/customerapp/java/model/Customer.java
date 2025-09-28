package customerapp.java.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customers")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="customerId")
public class Customer {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custId_GEN")
	@SequenceGenerator(initialValue = 1007, name = "custId_GEN", sequenceName = "customer_Id_GEN")
	@Column(name = "customer_id")
	private long customerId;
	@Column(name = "customer_name")
	private String name;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnoreProperties("customer") // value = {"applications", "hibernateLazyInitializer"})
	List<Account> customerAccounts;

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the customerAccounts
	 */
	public List<Account> getCustomerAccounts() {
		return customerAccounts;
	}

	/**
	 * @param customerAccounts the customerAccounts to set
	 */
	public void setCustomerAccounts(List<Account> customerAccounts) {
		this.customerAccounts = customerAccounts;
	}

	public Customer(long customerId, String name, List<Account> customerAccounts) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.customerAccounts = customerAccounts;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
