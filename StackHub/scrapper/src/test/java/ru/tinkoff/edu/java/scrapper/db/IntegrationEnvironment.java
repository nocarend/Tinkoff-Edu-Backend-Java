package ru.tinkoff.edu.java.scrapper.db;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
@Testcontainers
public class IntegrationEnvironment implements BeforeAllCallback {

    public static final PostgreSQLContainer<?> postgres;
    private static final Network backend = Network.newNetwork();

    static {
        postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:15"))
            .withNetwork(backend)
            .withExposedPorts(5432)
            .withUsername("postgres")
            .withPassword("password")
            .withDatabaseName("scrapper");
        postgres.start();
    }

    private AtomicBoolean initialized = new AtomicBoolean(false);

    @DynamicPropertySource
    static void jdbcProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username",postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext)
        throws LiquibaseException, SQLException, FileNotFoundException {
        Path path = Path.of(System.getProperty("user.dir")).getParent()
            .resolve("scrapper/src/main/resources/migrations");
        if (initialized.compareAndSet(false, true)) {
            Connection connection = DriverManager.getConnection(
                postgres.getJdbcUrl(),
                postgres.getUsername(),
                postgres.getPassword());
            Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new liquibase.Liquibase("master.xml",
                new DirectoryResourceAccessor(path), database);
            liquibase.update(new Contexts(), new LabelExpression());
        }
    }
}
