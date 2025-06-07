package ntu.edu.quangnm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class cloudinary {
	@Bean
	public Cloudinary cloudi() {
		Cloudinary c=new Cloudinary(ObjectUtils.asMap(
					"cloud_name","dlh7hbsrk",
					"api_key","564511922527684",
					"api_secret","hOsBJkTy-SFLX39OcpVKPm7NRjc",
					"secure",true
				));
		return c;
	}
}
