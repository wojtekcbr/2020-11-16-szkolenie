package pl.jsystems.qa.qaapi.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ApiConfig {

    private static final Config CONFIG = ConfigFactory.load("qaapiconfig.conf");
    private static final String ENVIRONMENT = CONFIG.getString("environment");
    private static final Config ENV = CONFIG.getConfig("environments").getConfig(ENVIRONMENT);

    public static final String BASE_URL = ENV.getString("baseUrl");
    public static final String API_KEY = ENV.getString("apikey");

}