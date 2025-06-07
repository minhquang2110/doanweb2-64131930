package ntu.edu.quangnm.mapper;

import ntu.edu.quangnm.dto.ScientistDTO;
import ntu.edu.quangnm.entity.Scientist;

public class ScientMapper {
		public static ScientistDTO fromEntity(Scientist scientist) {
		    ScientistDTO dto = new ScientistDTO();
	
		    dto.setId(scientist.getId());
	
		    dto.setFullName(scientist.getFullName());
		    dto.setEmail(scientist.getEmail());
		    dto.setGender(scientist.getGender());
		    dto.setBirthYear(scientist.getBirthYear());
		    dto.setAddress(scientist.getAddress());
		    dto.setPhone(scientist.getPhoneNumber());
		    dto.setMajor(scientist.getMajor());
		    dto.setSubMajor(scientist.getSubMajor());
		    dto.setTeachingSpecialty(scientist.getTeachingSpecialty());
	
		    dto.setDegree(scientist.getDegree() != null ? scientist.getDegree().getName() : null);
		    dto.setRank(scientist.getRank() != null ? scientist.getRank().getName() : null);
		    dto.setTitle(scientist.getTitle() != null ? scientist.getTitle().getName() : null);
		    dto.setResearchField(scientist.getResearchField() != null ? scientist.getResearchField().getName() : null);
		    dto.setOrganization(scientist.getOrganization() != null ? scientist.getOrganization().getName() : null);
	
		    dto.setImageUrl(null);
	
		    return dto;
		}

        public static Scientist toEntity(ScientistDTO dto, Scientist scientist) {
            if (scientist == null) {
                scientist = new Scientist();
            }
            scientist.setFullName(dto.getFullName());
            scientist.setEmail(dto.getEmail());
            scientist.setGender(dto.getGender());
            scientist.setBirthYear(dto.getBirthYear());
            scientist.setAddress(dto.getAddress());
            scientist.setPhoneNumber(dto.getPhone());
            scientist.setMajor(dto.getMajor());
            scientist.setSubMajor(dto.getSubMajor());
            scientist.setTeachingSpecialty(dto.getTeachingSpecialty());

            scientist.setDegree(null);
            scientist.setRank(null);
            scientist.setTitle(null);
            scientist.setResearchField(null);
            scientist.setOrganization(null);


            return scientist;
        }

    }


