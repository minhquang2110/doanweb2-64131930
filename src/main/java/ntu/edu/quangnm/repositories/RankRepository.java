package ntu.edu.quangnm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.edu.quangnm.entity.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank, Integer> {
}
