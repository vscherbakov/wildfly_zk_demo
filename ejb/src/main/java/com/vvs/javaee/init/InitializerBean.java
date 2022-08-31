package com.vvs.javaee.init;

import com.vvs.javaee.dao.AddressDao;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class InitializerBean {

    private static final String STAGE = "development";
    private static final String CHANGELOG_FILE = "liquibase/db.changelog.xml";

    @Resource(lookup = "java:/jdbc/datasources/MySqlDS")
    private DataSource ds;


    @EJB
    public AddressDao addressDao;

    @PostConstruct
    protected void bootstrap() {
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
        try (Connection connection = ds.getConnection()) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

            Liquibase liquiBase = new Liquibase(CHANGELOG_FILE, resourceAccessor, db);
            liquiBase.update(STAGE);
        } catch (SQLException | LiquibaseException e) {
        }

        // createMockData();
    }


}
