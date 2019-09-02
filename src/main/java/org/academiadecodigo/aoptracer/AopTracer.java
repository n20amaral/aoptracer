package org.academiadecodigo.aoptracer;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Map;

@Aspect
public class AopAdvisor {

    private final String ENV_NAME = "DEV_MODE";

    @Before("execution(* org.academiadecodigo.*.*.*(..))")
    public void beforeAdvice() {
        Map<String, String> env = System.getenv();

        if (!env.containsKey(ENV_NAME) || !Boolean.parseBoolean(env.get(ENV_NAME))) {
            return;
        }

        //TODO: Implement log to file mechanism
        String envValue = env.get(ENV_NAME);
        System.out.printf("%s=%s\n", ENV_NAME, envValue);
        System.out.println("LOG HERE! HERE! HERE!");
    }
}
