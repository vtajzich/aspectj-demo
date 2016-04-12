package demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;

/**
 * Created by vtajzich
 */
@DeclarePrecedence("LoggingAspect,SecurityAspect,PerformanceAspect,TransactionAspect")
@Aspect
public class DemoAspectPrecedence {
}
