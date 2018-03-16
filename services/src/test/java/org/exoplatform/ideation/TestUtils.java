
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

    public static void initOrclDB() throws SQLException,
            ClassNotFoundException, LiquibaseException {

        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "adelbase", "gtn");

        initDB();
    }
    private static void initDB() throws LiquibaseException {
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(conn));

        liquibase = new Liquibase("db/changelog/idea.db.changelog-1.0.0.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update((String)null);
    }


    public static IdeaEntity getDefaultIdea() {
        IdeaEntity idea = new IdeaEntity();
        idea.setId(EXISTING_idea_ID);
        idea.setTitle("Default idea");
        idea.setCreatedBy("root");
        idea.setCreatedTime(new Date());
        System.out.println(idea.getTitle());
        return idea;
    }
}
