package com.example.demo.domain.model;

import com.example.demo.domain.dto.Template;
import com.example.demo.domain.dto.TemplateTemplateVariables;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class TemplateFactory {
    public Template create(StringPosition[] positions, Map<String, Object> contentMap) {
        Template template = new Template();

        String templateId = positions[6].getValue();
        if (templateId != null) {
            template.setTemplateId(templateId.replaceAll("\\s+$", ""));
        }

        String language = positions[7].getValue();
        if (language != null) {
            template.setLanguage(language.replaceAll("\\s+$", ""));
        }

        String title = positions[8].getValue();
        if (title != null) {
            template.setTitle(title.replaceAll("\\s+$", ""));
        }

        TemplateTemplateVariables templateVariables = new TemplateTemplateVariables();
        templateVariables.setContent(contentMap);
        template.setTemplateVariables(templateVariables);

        return template;
    }
}

