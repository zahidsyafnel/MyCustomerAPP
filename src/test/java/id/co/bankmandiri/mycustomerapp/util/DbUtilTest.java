package id.co.bankmandiri.mycustomerapp.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.dbutil;

import java.sql.Connection;

public class DbUtilTest {


    @Test
    void getProperty() {
        String url = dbutil.getProperty("url");
        Assertions.assertEquals("jdbc:mysql://localhost:3306/belajar java", url);
    }


@Test
void getConnection() {
    Connection connection =dbutil.getConnection();
    Assertions.assertNotNull("connection should be succesfull");
    }
}