package db;

import static db.IntegrationEnvironment.postgres;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(IntegrationEnvironment.class)
public class ContainerTest {

    DatabaseMetaData makeConnection() throws SQLException {
        return DriverManager.getConnection(
            postgres.getJdbcUrl(),
            postgres.getUsername(),
            postgres.getPassword()).getMetaData();
    }

    @Test
    void isRunningTest() {
        Assertions.assertTrue(postgres.isRunning());
    }

    @Test
    void tableNamesTest() throws SQLException {
        var resultTrue = makeConnection().getTables(null, null, "chat", null);
        var resultFalse = makeConnection().getTables(null, null, "nonchat", null);
        Assertions.assertAll(
            () -> Assertions.assertTrue(resultTrue.next()),
            () -> Assertions.assertFalse(resultFalse.next()));
    }

    @Test
    void changeLogExistenseTest() throws SQLException {
        var result = makeConnection().getTables(null, null, "databasechangelog", null);
        result.next();
        Assertions.assertAll(
            () -> Assertions.assertTrue(result.next()),
            () -> Assertions.assertEquals(result.getString(0), "postgres"));
    }
}
