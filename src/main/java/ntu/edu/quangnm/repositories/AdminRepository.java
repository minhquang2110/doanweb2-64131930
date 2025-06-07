package ntu.edu.quangnm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ntu.edu.quangnm.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query("SELECT s FROM Admin s WHERE s.account.id = :accountId")
    Admin findaAdminByAccountId(@Param("accountId") Integer accountId);
}
