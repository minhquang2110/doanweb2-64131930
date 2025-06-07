package ntu.edu.quangnm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntu.edu.quangnm.entity.ResearchField;

@Repository
public interface ResearchFieldRepository extends JpaRepository<ResearchField, Integer> {

    List<ResearchField> findByParentFieldId(Integer parentFieldId);
}
