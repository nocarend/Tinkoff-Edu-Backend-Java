package ru.tinkoff.edu.java.scrapper.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(IntegrationEnvironment.class)
public class ContainerTest {

    @Test
    void isRunningTest() {
        Assertions.assertTrue(IntegrationEnvironment.postgres.isRunning());
    }

    @Test
    void tableNamesTest() throws SQLException {
        var connection = DriverManager.getConnection(
            IntegrationEnvironment.postgres.getJdbcUrl(),
            IntegrationEnvironment.postgres.getUsername(),
            IntegrationEnvironment.postgres.getPassword()).getMetaData();
        var resultTrue = connection.getTables(null, null, "chat", null);
        var resultFalse = connection.getTables(null, null, "nonchat", null);
        Assertions.assertAll("",
            () -> Assertions.assertTrue(resultTrue.next()),
            () -> Assertions.assertFalse(resultFalse.next()));
    }
}
