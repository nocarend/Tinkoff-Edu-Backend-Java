package ru.tinkoff.edu.java.scrapper.db;

import static ru.tinkoff.edu.java.scrapper.db.IntegrationEnvironment.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testcontainers.junit.jupiter.Testcontainers;

@ExtendWith(IntegrationEnvironment.class)
@Testcontainers
public class ContainerTest {

    Connection makeConnection() throws SQLException {
        return DriverManager.getConnection(
            postgres.getJdbcUrl(),
            postgres.getUsername(),
            postgres.getPassword()).getMetaData().getConnection();
    }

    @Test
    void isRunningTest() {
        Assertions.assertTrue(postgres.isRunning());
    }

    @Test
    void tableNamesTest() throws SQLException {
        try (var resultTrue = makeConnection().getMetaData().getTables(null, null, "chat", null)) {
            var resultFalse = makeConnection().getMetaData().getTables(null, null, "nonchat", null);
            var anotherExistedTableCheck = makeConnection().getMetaData()
                .getTables(null, null, "link", null);
            Assertions.assertAll(
                () -> Assertions.assertTrue(resultTrue.next()),
                () -> Assertions.assertTrue(anotherExistedTableCheck.next()),
                () -> Assertions.assertFalse(resultFalse.next()));
        }
    }

    @Test
    void changeLogExistenceTest() throws SQLException {
        try (var result = makeConnection().getMetaData()
            .getTables(null, null, "databasechangelog", null)) {
            Assertions.assertAll(
                () -> Assertions.assertTrue(result.next()));
        }
    }

    @Test
    void changeLogSucceedRecordsTest() throws SQLException {
        Statement statement;
        try (var connection = makeConnection()) {
            statement = connection.createStatement();
            var result = statement.executeQuery("SELECT exectype FROM databasechangelog");
            Assertions.assertAll(
                () -> {
                    while (result.next()) {
                        Assertions.assertEquals("EXECUTED", result.getString("exectype"));
                    }
                }
            );
        }
    }
}
