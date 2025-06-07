package ntu.edu.quangnm.repositories.custom;

import java.util.List;
import java.util.Set;

import ntu.edu.quangnm.entity.Scientist;

public interface ScientistRepositoryCustom {
    List<Scientist> filter(String keyword, Integer degreeId, Integer titleId, Set<Integer> researchFieldIds);
}
