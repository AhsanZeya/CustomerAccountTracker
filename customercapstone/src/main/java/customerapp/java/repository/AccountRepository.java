package customerapp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import customerapp.java.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Transactional
	@Modifying
	@Query("update Account a set a.balance=:balance where a.accountNumber=:accountNumber")
	public void updateBalance(@Param(value = "accountNumber") long accountNumber,
			@Param(value = "balance") double balance);

}
