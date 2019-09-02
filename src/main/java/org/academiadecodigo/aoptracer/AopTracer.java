package org.academiadecodigo.aoptracer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class AopTracer {

    private static final String ENV_NAME = "DEV_MODE";
    private boolean tracerEnabled;

    public AopTracer() {
        tracerEnabled = System.getenv().containsKey(ENV_NAME) && Boolean.parseBoolean(System.getenv().get(ENV_NAME));
    }

    public void init() {
        System.err.printf("%s=%b\n", ENV_NAME, tracerEnabled);
    }

    @Before(value = "execution(* org.academiadecodigo..*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {

        if (!tracerEnabled) {
            return;
        }

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()).replaceAll("[\\[\\]]","");

        System.err.printf("AOP-TRACER: %s.%s(%s)\n", className, methodName, args);

    }

    @AfterReturning(value = "execution(int org.academiadecodigo..*(..) )", returning = "retVal")
    public void afterReturningIntAdvice(int retVal) {
        System.err.printf("AOP-TRACER: Last method has returned: %d\n", retVal);
    }
}
