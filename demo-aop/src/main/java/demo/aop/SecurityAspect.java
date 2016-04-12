package demo.aop;

import demo.security.SecurityService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by vtajzich
 */
@Aspect
public class SecurityAspect extends AbstractDemoAspect {

    private SecurityService securityService;
    
    @Before("withinDemoApp()")
    public void authorizationAdvice(JoinPoint joinPoint) {
        securityService.isAuthorized(joinPoint.getSignature().getName());
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
