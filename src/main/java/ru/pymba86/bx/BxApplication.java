package ru.pymba86.bx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import ru.pymba86.bx.config.DefaultProfileUtil;
import ru.pymba86.bx.config.Constants;

@SpringBootApplication
public class BxApplication {

    private static final Logger log = LoggerFactory.getLogger(BxApplication.class);

    private final Environment env;

    @Inject
    public BxApplication(Environment env) {
        this.env = env;
    }

    /**
     * Инициализирует приложение.
     * Профили Spring можно настроить с помощью аргументов программы
     * -- spring.profiles.active=выбранный профиль
     */
    @PostConstruct
    public void initApplication() {
        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}",
                    Arrays.toString(env.getActiveProfiles()));
            Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
            if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT)
                    && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
                log.error("You have misconfigured your application! It should not run " +
                        "with both the 'dev' and 'prod' profiles at the same time.");
            }
            if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT)
                    && activeProfiles.contains(Constants.SPRING_PROFILE_FAST)) {
                log.error("You have misconfigured your application! It should not " +
                        "run with both the 'dev' and 'cloud' profiles at the same time.");
            }
        }
    }

    /**
     * Главный метод, используется для запуска spring приложени
     *
     * @param args the аргументы командной строки
     * @throws UnknownHostException Если вы используете локальный адресс
     */
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(BxApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
}
