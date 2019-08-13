package org.academiadecodigo.aoptracer;

import java.util.Map;

public class AopAdvisor {

    public void beforeAdvice() {

        String envVariable = "DEV_MODE";
        Map<String, String> env = System.getenv();
        String envValue = env.get(envVariable);

        if (!env.containsKey(envVariable) || !Boolean.parseBoolean(envValue)) {
            return;
        }

        System.out.printf("%s=%s\n", envVariable, envValue);

        System.out.println("LOG HERE! HERE! HERE!");
    }
}
