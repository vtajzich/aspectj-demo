package demo.aop;

import demo.performance.PerformanceMonitor;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by vtajzich
 */
@Aspect
public class PerformanceAspect extends AbstractDemoAspect {

    private PerformanceMonitor performanceMonitor;

    @Around("withinDemoApp()")
    public Object performanceAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {

        StopWatch stopWatch = performanceMonitor.createStopWatch();
        
        try {
            
            stopWatch.start();

            return pjp.proceed();

        } finally {
            stopWatch.stop();
            System.out.println("Took: " + stopWatch.getTime() + "ms");
        }
    }

    public void setPerformanceMonitor(PerformanceMonitor performanceMonitor) {
        this.performanceMonitor = performanceMonitor;
    }
}
