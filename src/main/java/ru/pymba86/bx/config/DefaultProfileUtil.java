package ru.pymba86.bx.config;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import java.util.HashMap;
import java.util.Map;
import static ru.pymba86.bx.config.Constants.SPRING_PROFILE_DEVELOPMENT;

/**
 * Служебный класс для загрузки профиля Spring, используемого по умолчанию
 * Когда нет <code> spring.profiles.active </code> устанавливается в среде
 * или в качестве аргумента командной строки.
 *
 * Если значение недоступно в приложении <code>.yml </code> профиль
 * <code>dev</code> будет использоваться по умолчанию.
 */
public class DefaultProfileUtil {
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    /**
     * Установить профиль по умолчанию
     * @param app Spring application
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        defProperties.put(SPRING_PROFILE_DEFAULT, SPRING_PROFILE_DEVELOPMENT);
        app.setDefaultProperties(defProperties);
    }

    /**
     * Получить профили, которые применены.
     * Если нет профилей, возвращает выбранный по умолчанию
     * @param env environment
     * @return profiles
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
