package demo.aop;

import demo.exception.TechnicalException;
import demo.tx.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by vtajzich
 */
@Aspect
public class TransactionAspect extends AbstractDemoAspect {

    private TransactionManager transactionManager;

    @Pointcut("execution(* *.get*(..))")
    public void getMethod() {

    }

    @Pointcut("execution(* *.find*(..))")
    public void findMehtod() {

    }

    @Around("withinDemoApp() && !(getMethod() || findMehtod())")
    public Object transactionAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        try {

            transactionManager.begin();
            
            Object result = pjp.proceed();
            
            transactionManager.commit();

            return result;
            
        } catch (Exception e) {
            
            transactionManager.rollback();

            throw new TechnicalException("shit happens!", e);
        }
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
