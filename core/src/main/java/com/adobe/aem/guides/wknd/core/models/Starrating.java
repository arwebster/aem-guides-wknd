package com.adobe.aem.guides.wknd.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.xfa.Int;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.Optional;

@Model(adaptables = Resource.class,
     defaultInjectionStrategy =  DefaultInjectionStrategy.OPTIONAL)
public class Starrating {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE)
    @Default(values="No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private int myselect;
    
    private String message;
    
    @PostConstruct
    protected void init() {
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        String currentPagePath = Optional.ofNullable(pageManager)
        .map(pm -> pm.getContainingPage(currentResource))
        .map(Page::getPath).orElse("");
        
        message = "Hello World!\n"
        + "Resource type is: " + resourceType + "\n"
        + "Current page is:  " + currentPagePath + "\n";
    }

    public String getStars() {
        return "★".repeat(myselect);
    }

    public String getStarsGrey() {
        return "★".repeat(5 - myselect);
    }
}