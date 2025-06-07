package ntu.edu.quangnm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntu.edu.quangnm.entity.ResearchField;
import ntu.edu.quangnm.repositories.ResearchFieldRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResearchFieldService {

    @Autowired
    private ResearchFieldRepository researchFieldRepository;

    public List<ResearchField> getAllResearchFields() {
        return researchFieldRepository.findAll();
    }

    public Optional<ResearchField> getResearchFieldById(Integer id) {
        return researchFieldRepository.findById(id);
    }

    public ResearchField saveResearchField(ResearchField researchField) {
        return researchFieldRepository.save(researchField);
    }

    public void deleteResearchField(Integer id) {
        researchFieldRepository.deleteById(id);
    }

    public List<ResearchField> findAll() {
        return researchFieldRepository.findAll();
    }
}
