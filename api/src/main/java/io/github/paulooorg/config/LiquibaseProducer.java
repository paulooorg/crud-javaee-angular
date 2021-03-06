package io.github.paulooorg.config;

import liquibase.integration.cdi.CDILiquibaseConfig;
import liquibase.integration.cdi.annotations.LiquibaseType;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@ApplicationScoped
public class LiquibaseProducer {
	@Resource(lookup = "java:jboss/datasources/PostgreSQLDS")
    private DataSource dataSource;
	
	@Produces
    @LiquibaseType
    public CDILiquibaseConfig createConfig() {
        CDILiquibaseConfig config = new CDILiquibaseConfig();
        config.setChangeLog("db/changelog/changelog-master.xml");
        return config;
    }

    @Produces
    @LiquibaseType
    public DataSource createDataSource() {
    	return dataSource;
    }

	@Produces
	@LiquibaseType
	public ResourceAccessor create() {
	    return new ClassLoaderResourceAccessor(getClass().getClassLoader());
	}
}
