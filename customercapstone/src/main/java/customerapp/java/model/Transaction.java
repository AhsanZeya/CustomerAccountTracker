package customerapp.java.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transId_GEN")
	@SequenceGenerator(initialValue = 7005, name = "transId_GEN", sequenceName = "transactionId_GEN")
	@Column(name = "transaction_id")
	long trasactionId;
	@Column(name = "from_account")
	long fromAccountNo;
	@Column(name = "to_account")
	long toAccountNo;
	@Column(name = "transaction_datetime")
	LocalDateTime transactionDateTime;
	@Column(name = "ammount_transfered")
	double amountTransfered;

	public long getTrasactionId() {
		return trasactionId;
	}

	public void setTrasactionId(long trasactionId) {
		this.trasactionId = trasactionId;
	}

	public long getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public long getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(long toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}

	public double getAmountTransfered() {
		return amountTransfered;
	}

	public void setAmountTransfered(double amountTransfered) {
		this.amountTransfered = amountTransfered;
	}

	public Transaction(long fromAccountNo, long toAccountNo, LocalDateTime transactionDateTime,
			double amountTransfered) {
		super();

		this.fromAccountNo = fromAccountNo;
		this.toAccountNo = toAccountNo;
		this.transactionDateTime = transactionDateTime;
		this.amountTransfered = amountTransfered;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

}
