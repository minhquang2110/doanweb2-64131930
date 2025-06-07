package ntu.edu.quangnm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.edu.quangnm.entity.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer> {
}
