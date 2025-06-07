package ntu.edu.quangnm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.edu.quangnm.entity.Degree;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer> {
}
