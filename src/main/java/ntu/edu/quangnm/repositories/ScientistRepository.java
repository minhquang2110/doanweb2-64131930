package ntu.edu.quangnm.repositories;

import jakarta.transaction.Transactional;
import ntu.edu.quangnm.entity.Scientist;
import ntu.edu.quangnm.repositories.custom.ScientistRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScientistRepository extends JpaRepository<Scientist, Integer>, ScientistRepositoryCustom {
    Scientist findByEmail(String email);

    Optional<Scientist> findById(Integer id);
    
    List<Scientist> findByFullNameContainingIgnoreCase(String keyword);

    @Query("""
        SELECT DISTINCT s FROM Scientist s
        LEFT JOIN FETCH s.degree
        LEFT JOIN FETCH s.rank
        LEFT JOIN FETCH s.title
        LEFT JOIN FETCH s.researchField
        WHERE s.id = :id
    """)
    Optional<Scientist> findByIdWithRelations(@Param("id") Integer id);

    @Query("SELECT s FROM Scientist s WHERE s.account.id = :accountId")
    Scientist findScientistByAccountId(@Param("accountId") Integer accountId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Scientist s WHERE s.id = :id")
    void deleteScientistById(@Param("id") Integer id);


    @Query("SELECT s FROM Scientist s WHERE " +
            "LOWER(s.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.gender) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(s.id AS string) LIKE CONCAT('%', :keyword, '%')")
    List<Scientist> search(String keyword);
}
