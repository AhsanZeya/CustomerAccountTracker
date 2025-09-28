package customerapp.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import customerapp.java.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Transactional
	@Modifying
	@Query("update Customer c set c.name=:name where c.customerId=:customerId")
	void updateCustomer(@Param(value = "customerId") long customerId, @Param(value = "name") String name);

	public Customer findByName(String name);

}
