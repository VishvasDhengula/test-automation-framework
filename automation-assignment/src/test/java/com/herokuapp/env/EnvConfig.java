package com.herokuapp.env;

import org.testng.Reporter;

/**
 * This class is for configure env.
 * TODO - add QA and UAT urls
 */
public class EnvConfig {

    public static final String DEV_URL = "https://thinking-tester-contact-list.herokuapp.com";

    public static final String QA_URL = "";

    public static final String UAT_URL = "";

    enum ENV_API {
        DEV(DEV_URL),
        QA(QA_URL),
        UAT(UAT_URL);
        public final String envUrl;

        private ENV_API(String env) {
            this.envUrl = env;
        }

        public String getValue(){
            return envUrl;
        }
    }

    enum ENV_WEB {
        DEV(DEV_URL),
        QA(QA_URL),
        UAT(UAT_URL);
        public final String envUrl;

        private ENV_WEB(String env) {
            this.envUrl = env;
        }

        public String getValue(){
            return envUrl;
        }
    }

    public static String getEnv() {
        String env = System.getProperty("env");
        if (env != null && !env.isEmpty()) {
            return env;
        } else {
            throw new Error("Env is not setup correctly. setup using command line like gradle test -Denv=dev");
        }

    }

    public static String getAPIEnvBaseUrl() {
        try {
            String env = getEnv();
            Reporter.log("API Test will run in " + env + " env ");
            String url = ENV_API.valueOf(env.toUpperCase()).getValue();
            return checkUrlIsValid(url, env);
        } catch (Exception e) {
            throw new Error("Exception in getting API env. " + e);
        }

    }

    public static String getWebEnvBaseUrl() {
        try {
            String env = getEnv();
            Reporter.log("Web Test will run in " + env + " env ");
            String url = ENV_WEB.valueOf(env.toUpperCase()).getValue();
            return checkUrlIsValid(url, env);
        } catch (Exception e) {
            throw new Error("Exception in getting WEB env. " + e);
        }

    }

    public static String checkUrlIsValid(String url, String env) {
        Reporter.log(env + " ENV URL is " + url);

        if (url != null && !url.isEmpty()) {
            return url;
        } else {
            throw new Error("Url is not setup correctly for env. " + env + ". Please setup in EnvConfig.java file");
        }
    }
}
