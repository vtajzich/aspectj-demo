package demo.service.impl;

import demo.connector.SomeExternalSystemConnector;
import demo.exception.AuthorizationException;
import demo.exception.TechnicalException;
import demo.performance.PerformanceMonitor;
import demo.security.SecurityService;
import demo.service.SomeService;
import demo.tx.TransactionManager;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by vtajzich
 */
public class SomeDemoService implements SomeService {

    private static final Logger log = LoggerFactory.getLogger(SomeDemoService.class);
    
    private SecurityService securityService;
    private TransactionManager transactionManager;
    private PerformanceMonitor performanceMonitor;
    private SomeExternalSystemConnector externalSystemConnector;

    public SomeDemoService(SomeExternalSystemConnector externalSystemConnector, SecurityService securityService, TransactionManager transactionManager, PerformanceMonitor performanceMonitor) {
        this.externalSystemConnector = externalSystemConnector;
        this.securityService = securityService;
        this.transactionManager = transactionManager;
        this.performanceMonitor = performanceMonitor;
    }

    @Override
    public void save(Object anObject) {

        StopWatch stopWatch = performanceMonitor.createStopWatch();
        stopWatch.start();
        
        log.info("About to start save of " + anObject);
        
        if (!securityService.isAuthorized("save")) {
            
            log.error("Not authorized to perform save of " + anObject);
            
            throw new AuthorizationException("save");
        }

        transactionManager.begin();

        try {

            System.out.println("saved " + anObject);
            
            transactionManager.commit();

        } catch (Exception ex) {
            
            log.error("Error has occured during save of " + anObject, ex);
            
            transactionManager.rollback();
        } finally {
            stopWatch.stop();
            
            log.info("Took: " + stopWatch.getTime() + "ms");
        }
    }

    @Override
    public void update(Object anObject) {

        StopWatch stopWatch = performanceMonitor.createStopWatch();
        stopWatch.start();

        log.info("About to start update of " + anObject);

        if (!securityService.isAuthorized("update")) {

            log.error("Not authorized to perform update of " + anObject);

            throw new AuthorizationException("update");
        }

        transactionManager.begin();

        try {

            System.out.println("updated " + anObject);
            
            transactionManager.commit();

        } catch (Exception ex) {

            log.error("Error has occured during update of " + anObject, ex);

            transactionManager.rollback();
        } finally {
            stopWatch.stop();

            log.info("Took: " + stopWatch.getTime() + "ms");
        }
    }

    @Override
    public void delete(Object anObject) {

        StopWatch stopWatch = performanceMonitor.createStopWatch();
        stopWatch.start();

        log.info("About to start delete of " + anObject);

        if (!securityService.isAuthorized("delete")) {

            log.error("Not authorized to perform delete of " + anObject);

            throw new AuthorizationException("delete");
        }

        transactionManager.begin();

        try {

            System.out.println("deleted " + anObject);

            transactionManager.commit();

        } catch (Exception ex) {

            log.error("Error has occured during delete of " + anObject, ex);

            transactionManager.rollback();
        } finally {
            stopWatch.stop();

            log.info("Took: " + stopWatch.getTime() + "ms");
        }
    }

    @Override
    public Object getObjectByName(String name) {

        StopWatch stopWatch = performanceMonitor.createStopWatch();
        stopWatch.start();

        log.info("About to start getObjectByName of " + name);

        if (!securityService.isAuthorized("getObjectByName")) {

            log.error("Not authorized to perform getObjectByName of " + name);

            throw new AuthorizationException("getObjectByName");
        }

        try {

            System.out.println("getObjectByName " + name);

            return "anObject " + name;

        } catch (Exception ex) {

            log.error("Error has occured during getObjectByName of " + name, ex);

            throw new TechnicalException(ex);
            
        } finally {
            stopWatch.stop();

            log.info("Took: " + stopWatch.getTime() + "ms");
        }
    }

    @Override
    public List<String> findAll() {

        StopWatch stopWatch = performanceMonitor.createStopWatch();
        stopWatch.start();

        log.info("About to start findAll");

        if (!securityService.isAuthorized("findAll")) {

            log.error("Not authorized to perform findAll");

            throw new AuthorizationException("findAll");
        }

        try {

            List<String> result = Collections.emptyList();

            return result;

        } catch (Exception ex) {

            log.error("Error has occured during findAll", ex);

            throw new TechnicalException(ex);
            
        } finally {
            stopWatch.stop();

            log.info("Took: " + stopWatch.getTime() + "ms");
        }
    }

    @Override
    public void callExternalSystem() {

        StopWatch stopWatch = performanceMonitor.createStopWatch();
        stopWatch.start();

        log.info("About to start callExternalSystem");

        if (!securityService.isAuthorized("callExternalSystem")) {

            log.error("Not authorized to perform callExternalSystem");

            throw new AuthorizationException("callExternalSystem");
        }

        transactionManager.begin();

        try {

            System.out.println("callExternalSystem");

            externalSystemConnector.askForRandomNumber();

            transactionManager.commit();

        } catch (Exception ex) {

            log.error("Error has occurred during callExternalSystem", ex);

            transactionManager.rollback();

            throw new TechnicalException(ex);
            
        } finally {
            stopWatch.stop();

            log.info("Took: " + stopWatch.getTime() + "ms");
        }
    }
}
