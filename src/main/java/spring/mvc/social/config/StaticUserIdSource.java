package spring.mvc.social.config;

import org.springframework.social.UserIdSource;

public class StaticUserIdSource implements UserIdSource {
    @Override
    public String getUserId() {
        return "anonymous";
    }
}