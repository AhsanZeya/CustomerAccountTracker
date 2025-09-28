package customerapp.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "accounts")
public class Account {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccNo_Gen")
	@SequenceGenerator(initialValue = 1000007, name = "AccNo_Gen", sequenceName = "AccountNumber_Gen")
	@Column(name = "account_no")
	private long accountNumber;
	@Column(name = "account_type")
	private String accountType;
	@Column(name = "account_balance")
	private double balance;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_customer_id") // ,nullable = false)
	// @JsonIgnore
	@JsonIgnoreProperties(value = { "customerAccounts", "applications", "hibernateLazyInitializer" })
	private Customer customer;

	/**
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(long accountNumber, String accountType, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}

	public Account(String accountType, double balance, Customer customer) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.customer = customer;
	}

}
