package ntu.edu.quangnm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntu.edu.quangnm.entity.Degree;
import ntu.edu.quangnm.repositories.DegreeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeService {

    @Autowired
    private DegreeRepository degreeRepository;

    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    public Optional<Degree> getDegreeById(Integer id) {
        return degreeRepository.findById(id);
    }

    public Degree saveDegree(Degree degree) {
        return degreeRepository.save(degree);
    }

    public void deleteDegree(Integer id) {
        degreeRepository.deleteById(id);
    }

    public List<Degree> findAll() {
        return degreeRepository.findAll();
    }
}
