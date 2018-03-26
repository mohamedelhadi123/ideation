
package org.exoplatform.ideation;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.exoplatform.ideation.entities.domain.IdeaEntity;
import org.exoplatform.ideation.storage.dao.IdeaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class TestUtils {

    private static Connection conn;
    private static Liquibase liquibase;

    public static long EXISTING_IDEA_ID = 1;

    public static void initH2DB() throws SQLException,
            ClassNotFoundException, LiquibaseException {


        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:target/h2-db", "sa", "");

        initDB();
    }
    public static void initHSQLDB() throws LiquibaseException, SQLException,
            ClassNotFoundException {

        Class.forName("org.hsqldb.jdbcDriver");
        conn = DriverManager.getConnection("jdbc:hsqldb:file:target/hsql-db", "sa", "");

        initDB();
    }
    private static void initDB() throws LiquibaseException {
        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(conn));

        liquibase = new Liquibase("db/changelog/ideations.db.changelog-1.0.0.xml", new ClassLoaderResourceAccessor(), database);
        liquibase.update((String)null);
    }

    public static void closeDB() throws LiquibaseException, SQLException {
        liquibase.rollback(1000, null);
        conn.close();
    }
    public static List <IdeaEntity> getDefaultIdea(List <IdeaEntity>title) {
        IdeaEntity ideaEntity = new IdeaEntity() ;
        IdeaDAO ideaDAO = new IdeaDAO();
        ideaEntity.setId(1);
        ideaEntity.setTitle("hello");
        ideaEntity.setStatus("status");
        ideaEntity.setCreatedBy("adel");
        title = ideaDAO.getAllIdeas();
        return title;

    }
    public static void main(String[] args){}
}
