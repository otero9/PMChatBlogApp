package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Interface TemplateRateRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface TemplateRateRepository extends MongoRepository<TemplateRate, String> {

	List<TemplateRate> findByUser(User user);

	List<TemplateRate> findByTemplate(Template template);

	TemplateRate findByUserAndTemplate(User user, Template template);

}
