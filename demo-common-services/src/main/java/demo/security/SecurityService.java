package demo.security;

/**
 * Created by vtajzich
 */
public interface SecurityService {

    boolean isAuthenticated();

    boolean hasAnyRole(String... roles);

    boolean isAuthorized(String action);
}
