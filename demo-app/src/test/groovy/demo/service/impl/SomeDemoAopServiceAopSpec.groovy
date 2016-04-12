package demo.service.impl

import demo.aop.PerformanceAspect
import demo.aop.SecurityAspect
import demo.aop.TransactionAspect
import demo.connector.SomeExternalSystemConnector
import demo.exception.TechnicalException
import demo.performance.PerformanceMonitor
import demo.security.SecurityService
import demo.tx.TransactionManager
import org.apache.commons.lang3.time.StopWatch
import spock.lang.Specification

/**
 * Created by vtajzich
 */
class SomeDemoAopServiceAopSpec extends Specification {

    SomeExternalSystemConnector externalSystemConnector
    PerformanceMonitor performanceMonitor
    SecurityService securityService
    TransactionManager transactionManager

    SomeDemoAopService someDemoService

    void setup() {

        externalSystemConnector = Mock(SomeExternalSystemConnector)
        performanceMonitor = Mock(PerformanceMonitor)
        securityService = Mock(SecurityService)
        transactionManager = Mock(TransactionManager)

        PerformanceAspect performanceAspect = PerformanceAspect.aspectOf()
        performanceAspect.performanceMonitor = performanceMonitor

        SecurityAspect securityAspect = SecurityAspect.aspectOf()
        securityAspect.securityService = securityService

        TransactionAspect transactionAspect = TransactionAspect.aspectOf()
        transactionAspect.transactionManager = transactionManager

        someDemoService = new SomeDemoAopService(externalSystemConnector)
    }

    def "should save an object"() {

        when:

        someDemoService.save("my object")

        then:

        1 * performanceMonitor.createStopWatch() >> {
            println "creating new stopwatch";
            new StopWatch()
        }

        1 * securityService.isAuthorized("save") >> {
            println "checking authorization for $it"
            true
        }

        1 * transactionManager.begin() >> {
            println "transaction begun ... "
        }

        1 * transactionManager.commit() >> {
            println "transaction commited"
        }

        0 * transactionManager.rollback()

    }

    def "should update an object"() {

        when:

        someDemoService.update("my object")

        then:

        1 * performanceMonitor.createStopWatch() >> {
            println "creating new stopwatch";
            new StopWatch()
        }

        1 * securityService.isAuthorized("update") >> {
            println "checking authorization for $it"
            true
        }

        1 * transactionManager.begin() >> {
            println "transaction begun ... "
        }

        1 * transactionManager.commit() >> {
            println "transaction commited"
        }

        0 * transactionManager.rollback()

    }

    def "should delete an object"() {

        when:

        someDemoService.delete("my object")

        then:

        1 * performanceMonitor.createStopWatch() >> {
            println "creating new stopwatch";
            new StopWatch()
        }

        1 * securityService.isAuthorized("delete") >> {
            println "checking authorization for $it"
            true
        }

        1 * transactionManager.begin() >> {
            println "transaction begun ... "
        }

        1 * transactionManager.commit() >> {
            println "transaction commited"
        }

        0 * transactionManager.rollback()

    }

    def "should rollback a transaction when exception is thrown"() {

        when:

        someDemoService.callExternalSystem()

        then:

        thrown(TechnicalException)

        1 * performanceMonitor.createStopWatch() >> {
            println "creating new stopwatch";
            new StopWatch()
        }

        1 * securityService.isAuthorized("callExternalSystem") >> {
            println "checking authorization for $it"
            true
        }

        1 * transactionManager.begin() >> {
            println "transaction begun ... "
        }

        1 * externalSystemConnector.askForRandomNumber() >> { throw new RuntimeException("Something bad happened!") }

        0 * transactionManager.commit()
        1 * transactionManager.rollback()

    }

    def "should get an object"() {

        when:

        def result = someDemoService.getObjectByName("my name")

        then:

        result == 'anObject my name'

        1 * performanceMonitor.createStopWatch() >> {
            println "creating new stopwatch";
            new StopWatch()
        }

        1 * securityService.isAuthorized("getObjectByName") >> {
            println "checking authorization for $it"
            true
        }

        0 * transactionManager.begin()
        0 * transactionManager.commit()
        0 * transactionManager.rollback()

    }

    def "should find all objects"() {

        when:

        def result = someDemoService.findAll()

        then:

        result != null

        1 * performanceMonitor.createStopWatch() >> {
            println "creating new stopwatch";
            new StopWatch()
        }

        1 * securityService.isAuthorized("findAll") >> {
            println "checking authorization for $it"
            true
        }

        0 * transactionManager.begin()
        0 * transactionManager.commit()
        0 * transactionManager.rollback()
    }
}
