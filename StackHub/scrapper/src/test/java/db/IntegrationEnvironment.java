package db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
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
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class IntegrationEnvironment implements BeforeAllCallback {

    public static final PostgreSQLContainer<?> postgres;
    private static final Network backend = Network.newNetwork();

    static {
        class PropertiesLoader {

            public static Properties loadProperties(String resourceFileName) {
                Properties configuration = new Properties();
                try {
                    InputStream inputStream = PropertiesLoader.class
                        .getClassLoader()
                        .getResourceAsStream(resourceFileName);
                    configuration.load(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return configuration;
            }
        }
        var config = PropertiesLoader.loadProperties("application.yaml");
        postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:15"))
            .withNetwork(backend)
            .withExposedPorts(5432)
            .withUsername(config.getProperty("username"))
            .withPassword(config.getProperty("password"))
            .withDatabaseName(config.getProperty("database"));
        postgres.start();
    }

    private AtomicBoolean initialized = new AtomicBoolean(false);

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
