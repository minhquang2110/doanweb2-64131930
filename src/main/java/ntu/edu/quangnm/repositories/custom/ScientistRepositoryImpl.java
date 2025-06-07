package ntu.edu.quangnm.repositories.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import ntu.edu.quangnm.entity.Scientist;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ScientistRepositoryImpl implements ScientistRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Scientist> filter(String keyword, Integer degreeId, Integer titleId, Set<Integer> researchFieldIds) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Scientist> cq = cb.createQuery(Scientist.class);
        Root<Scientist> root = cq.from(Scientist.class);

        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("fullName")), "%" + keyword.trim().toLowerCase() + "%"));
        }

        if (degreeId != null) {
            predicates.add(cb.equal(root.get("degree").get("id"), degreeId));
        }

        if (titleId != null) {
            predicates.add(cb.equal(root.get("title").get("id"), titleId));
        }

        if (researchFieldIds != null && !researchFieldIds.isEmpty()) {
            predicates.add(root.get("researchField").get("id").in(researchFieldIds));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.asc(root.get("fullName")));

        return em.createQuery(cq).getResultList();
    }
}
