package io.github.paulooorg.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.github.paulooorg.exceptions.ApiExceptionMapper;
import io.github.paulooorg.exceptions.ValidationExceptionMapper;
import io.github.paulooorg.infra.CorsFilter;
import io.github.paulooorg.infra.CustomObjectMapper;
import io.github.paulooorg.resources.CommentResource;
import io.github.paulooorg.resources.TaskResource;
import io.github.paulooorg.resources.TaskStatusResource;

@ApplicationPath("api/v1")
public class JAXRSConfiguration extends Application {
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.addAll(getResources());
        classes.addAll(getProviders());
        return classes;
    }
    
    private Set<Class<?>> getResources() {
    	Set<Class<?>> classes = new HashSet<>();
    	classes.add(TaskResource.class);
    	classes.add(CommentResource.class);
    	classes.add(TaskStatusResource.class);
        return classes;
    }
    
    private Set<Class<?>> getProviders() {
    	Set<Class<?>> providers = new HashSet<>();
    	providers.add(CustomObjectMapper.class);
    	providers.add(ApiExceptionMapper.class);
    	providers.add(ValidationExceptionMapper.class);
    	providers.add(CorsFilter.class);
        return providers;
    }
}
