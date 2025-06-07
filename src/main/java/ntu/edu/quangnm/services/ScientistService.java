package ntu.edu.quangnm.services;

import jakarta.transaction.Transactional;
import ntu.edu.quangnm.dto.ScientistDTO;
import ntu.edu.quangnm.entity.Account;
import ntu.edu.quangnm.entity.Degree;
import ntu.edu.quangnm.entity.Organization;
import ntu.edu.quangnm.entity.Rank;
import ntu.edu.quangnm.entity.ResearchField;
import ntu.edu.quangnm.entity.Scientist;
import ntu.edu.quangnm.entity.Title;
import ntu.edu.quangnm.entity.Account.Role;
import ntu.edu.quangnm.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Service
public class ScientistService {
    @Autowired 
    private ScientistRepository scientistRepository;
    

    @Autowired
    private DegreeRepository degreeRepository;
    
    @Autowired
    private RankRepository rankRepository;
    
    @Autowired
    private TitleRepository titleRepository;
    
    @Autowired
    private OrganizationRepository organizationRepository;
    
    
    @Autowired
    private ResearchFieldRepository researchFieldRepository;
    
    @Autowired
    private Cloudinary cloudinary;
    
    public static String UPLOAD_DIRECTORY = "/bin";

    public List<Scientist> getAllScientists() {
        return scientistRepository.findAll();
    }
    
    public Scientist getScientistByAccountId(Integer id) {
    	return scientistRepository.findScientistByAccountId(id);
    }
    public Optional<Scientist> getScientistById(Integer id) {
        return scientistRepository.findById(id);
    }

    public Scientist save(Scientist scientist) {
    	System.out.println("ser");
        return scientistRepository.save(scientist);
    }
    
    public void saveScientist(ScientistDTO scientist) throws IOException {
        Scientist sc = new Scientist();
        Account account = new Account();

        String normalized = Normalizer.normalize(scientist.getFullName(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String tenKhongDau = pattern.matcher(normalized).replaceAll("")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D");
        String tenXuLy = tenKhongDau.toLowerCase().replaceAll("\\s+", "");
        String thoiGian = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String ketQua = tenXuLy + thoiGian;

        account.setUsername(ketQua);
        account.setPassword("1");  
        account.setRole(Role.Scientist);

        sc.setAccount(account);

        sc.setFullName(scientist.getFullName());
        sc.setEmail(scientist.getEmail());
        sc.setGender(scientist.getGender());
        sc.setBirthYear(scientist.getBirthYear());
        sc.setAddress(scientist.getAddress());
        sc.setPhoneNumber(scientist.getPhone());
        sc.setMajor(scientist.getMajor());
        sc.setSubMajor(scientist.getSubMajor());
        sc.setTeachingSpecialty(scientist.getTeachingSpecialty());

        Degree de = degreeRepository.getById(Integer.parseInt(scientist.getDegree()));
        Rank ra = rankRepository.getById(Integer.parseInt(scientist.getRank()));
        Title ti = titleRepository.getById(Integer.parseInt(scientist.getTitle()));
        ResearchField re = researchFieldRepository.getById(Integer.parseInt(scientist.getResearchField()));

        sc.setDegree(de);
        sc.setRank(ra);
        sc.setTitle(ti);
        sc.setResearchField(re);

        if (scientist.getImageUrl() != null && !scientist.getImageUrl().isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(scientist.getImageUrl().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String imageUrl = (String) uploadResult.get("secure_url");
            sc.setImage(imageUrl);
        }

        scientistRepository.save(sc);
    }

    public void deleteScientist(Integer id) {
        scientistRepository.deleteById(id);
    }

    public Scientist findByEmail(String email) {
        return scientistRepository.findByEmail(email);
    }

    public List<Scientist> searchByName(String keyword) {
        return scientistRepository.findByFullNameContainingIgnoreCase(keyword);
    }

    public Scientist findByIdWithRelations(Integer id) {
        return scientistRepository.findByIdWithRelations(id)
              .orElseThrow(() -> new EntityNotFoundException("Scientist not found with id " + id));
    }

    public Scientist findById(Integer id) {
        return scientistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scientist not found with id " + id));
    }

    public List<Scientist> findAll() {
        return scientistRepository.findAll();
    }

    public boolean existsById(Integer id) {
        return scientistRepository.existsById(id);
    }

    public List<Scientist> filterScientists(String keyword, Integer degreeId, Integer titleId, Integer researchFieldId) {
        Set<Integer> researchFieldIds = null;

        if (researchFieldId != null) {
            researchFieldIds = getAllRelatedResearchFieldIds(researchFieldId);
        }

        return scientistRepository.filter(keyword, degreeId, titleId, researchFieldIds);
    }

    private Set<Integer> getAllRelatedResearchFieldIds(Integer rootId) {
        Set<Integer> ids = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(rootId);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            if (ids.add(current)) {
                List<ResearchField> children = researchFieldRepository.findByParentFieldId(current);
                for (ResearchField child : children) {
                    queue.add(child.getId());
                }
            }
        }
        return ids;
    }

    public List<Scientist> searchScientists(String keyword) {
        return scientistRepository.search(keyword);
    }

}
