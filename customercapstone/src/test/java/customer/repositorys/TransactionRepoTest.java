package customer.repositorys;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import customerapp.java.model.Transaction;
import customerapp.java.repository.TransactionRepository;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class TransactionRepoTest {

	
	@Autowired
	TransactionRepository trepo;
	
	@Test
    @Order(1)
    @Rollback(value = false)
	public void transferTest() {
		Transaction trans = new Transaction();
		trans.setTrasactionId(7005);
		trans.setFromAccountNo(1000008);
		trans.setToAccountNo(1000007);
		
		trans.setAmountTransfered(7000);
		Transaction savetrans = trepo.save(trans);
		assertThat(savetrans.getTrasactionId()).isGreaterThan(0);
	}
	
	  @Test
	    @Order(2)
	    @Rollback(value = false)
	  public void getAllTransTest() {
		  List<Transaction> transactions = trepo.findAll();
		  assertThat(transactions.size()).isGreaterThan(0);
	  }
	  @Test
	    @Order(3)
	    @Rollback(value = false)
	  public void getTransByIdTest() {
		  Transaction trans = trepo.findById(7005L).get();
		  assertThat(trans.getTrasactionId()).isEqualTo(7005L);
	  }
}
