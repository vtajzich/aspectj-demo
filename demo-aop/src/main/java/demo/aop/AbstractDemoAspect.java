package demo.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by vtajzich
 */
public class AbstractDemoAspect {

    @Pointcut("execution(public * demo..*AopService.*(..))")
    public void withinDemoApp() {

    }
    
}
