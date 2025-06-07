package ntu.edu.quangnm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntu.edu.quangnm.entity.Title;
import ntu.edu.quangnm.repositories.TitleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    @Autowired
    private TitleRepository titleRepository;

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Optional<Title> getTitleById(Integer id) {
        return titleRepository.findById(id);
    }

    public Title saveTitle(Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitle(Integer id) {
        titleRepository.deleteById(id);
    }

    public List<Title> findAll() {
        return titleRepository.findAll();
    }
}
