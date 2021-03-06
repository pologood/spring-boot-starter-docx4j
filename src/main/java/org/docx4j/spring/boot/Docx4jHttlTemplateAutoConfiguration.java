package org.docx4j.spring.boot;

import org.docx4j.Docx4J;
import org.docx4j.template.httl.WordprocessingMLHttlTemplate;
import org.docx4j.template.xhtml.WordprocessingMLHtmlTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import httl.Engine;

@Configuration
@AutoConfigureAfter(Docx4jXhtmlTemplateAutoConfiguration.class)
@ConditionalOnClass({ Docx4J.class, Engine.class , WordprocessingMLHttlTemplate.class })
@ConditionalOnProperty(prefix = Docx4jProperties.PREFIX, value = "enabled", havingValue = "true")
@EnableConfigurationProperties({ Docx4jProperties.class, Docx4jHttlTemplateProperties.class })
public class Docx4jHttlTemplateAutoConfiguration {

	@Bean
	public WordprocessingMLHttlTemplate wmlHttlTemplate(
			Docx4jProperties docx4jProperties,
			Docx4jHttlTemplateProperties templateProperties, 
			WordprocessingMLHtmlTemplate wmlHtmlTemplate,
			@Autowired(required = false) Engine engine) {
		WordprocessingMLHttlTemplate template = new WordprocessingMLHttlTemplate(wmlHtmlTemplate);
		template.setEngine(engine);
		return template;
	}
	
}
