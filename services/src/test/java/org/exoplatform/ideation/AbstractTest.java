package org.exoplatform.ideation;
import java.sql.SQLException;

import liquibase.exception.LiquibaseException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.exoplatform.commons.persistence.impl.EntityManagerService;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.component.RequestLifeCycle;

/**
 * A base test class for all DAO tests which take responsibility to
 * initialize/clean up database.
 *
 * @author <a href="trongtt@gmail.com">Trong Tran</a>
 * @version $Revision$
 */
public class AbstractTest {

    @BeforeClass
    public static void createTable() throws SQLException,
            ClassNotFoundException, LiquibaseException {
        TestUtils.initH2DB();
    }

    @AfterClass
    public static void destroy() throws LiquibaseException, SQLException {
        TestUtils.closeDB();
    }


    @Before
    public void initializeContainerAndStartRequestLifecycle() {
        PortalContainer container = PortalContainer.getInstance();

        //
        RequestLifeCycle.begin(container);

        EntityManagerService entityMgrService = (EntityManagerService) container.getComponentInstanceOfType(EntityManagerService.class);
        entityMgrService.getEntityManager().getTransaction().begin();
    }

    @After
    public void endRequestLifecycle() {
        PortalContainer container = PortalContainer.getInstance();

        //
        EntityManagerService entityMgrService = (EntityManagerService) container.getComponentInstanceOfType(EntityManagerService.class);
        if (entityMgrService.getEntityManager() != null && entityMgrService.getEntityManager().getTransaction() != null
                && entityMgrService.getEntityManager().getTransaction().isActive()) {
            entityMgrService.getEntityManager().getTransaction().commit();
            //
            RequestLifeCycle.end();
        }

    }
}
