package org.academiadecodigo.aoptracer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;

import java.util.Arrays;

@Aspect
public class AopTracer {

    private final String ENV_NAME = "DEV_MODE";
    private boolean tracerEnabled;

    public AopTracer() {
        tracerEnabled = System.getenv().containsKey(ENV_NAME) && Boolean.parseBoolean(System.getenv().get(ENV_NAME));
    }

    public void init() {
        System.err.printf("%s=%b\n", ENV_NAME, tracerEnabled);
        new AnnotationAwareAspectJAutoProxyCreator();
    }

    @Before("execution(* org.academiadecodigo.*.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {

        if (!tracerEnabled) {
            return;
        }

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs()).replaceAll("[\\[\\]]","");

        System.err.printf("AOP-TRACER: %s.%s(%s)\n", className, methodName, args);
    }
}
