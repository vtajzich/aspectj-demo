package demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by vtajzich
 */
@Aspect
public class LoggingAspect extends AbstractDemoAspect {

    @Around("withinDemoApp()")
    public Object logAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());

        log.info("Before " + pjp.getSignature().toLongString() + ", args: " + Arrays.asList(pjp.getArgs()));

        try {

            Object result = pjp.proceed();

            log.info(" After " + pjp.getSignature().toLongString() + ", args: " + Arrays.asList(pjp.getArgs()));

            return result;
            
        } finally {
            log.info(" Finally " + pjp.getSignature().toLongString() + ", args: " + Arrays.asList(pjp.getArgs()));
        }
    }
}
