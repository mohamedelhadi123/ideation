
package org.exoplatform.ideation;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.exoplatform.ideation.entities.domain.IdeaEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;



public class TestUtils {

    private static Connection conn;
    private static Liquibase liquibase;

    public static long EXISTING_idea_ID = 1;

    public static void initH2DB() throws SQLException,
            ClassNotFoundException, LiquibaseException {

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/phpmyadmin", "root", "");

        initDB();
    }

    public static void initHSQLDB() throws LiquibaseException, SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/phpmyadmin", "root", "");

        initDB();
    }

    private static void initDB() throws LiquibaseException {
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(conn));

        liquibase = new Liquibase("db/changelog/idea.db.changelog-1.0.0.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update((String)null);
    }

    public static void closeDB() throws LiquibaseException, SQLException {
        liquibase.rollback(1000, null);
        conn.close();
    }

    public static IdeaEntity getDefaultIdea() {
        IdeaEntity idea = new IdeaEntity();
        idea.setId(EXISTING_idea_ID);
        idea.setTitle("Default idea");
        idea.setCreatedBy("root");
        idea.setCreatedTime(new Date());
        System.out.println(idea);
        return idea;
    }


}
