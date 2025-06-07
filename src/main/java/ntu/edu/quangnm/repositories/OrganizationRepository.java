package ntu.edu.quangnm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.edu.quangnm.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
