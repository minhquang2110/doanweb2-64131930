package ntu.edu.quangnm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ntu.edu.quangnm.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Account a WHERE a.username = :username")
    boolean existsByUsername(String username);
}
