package hr.samara.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * swagger configuration<br/>
 * <p>
 * defaults: swagger is not enabled and security is on
 * @see hr.samara.config.SwaggerConfig
 * @see hr.samara.config.SwaggerSecurityConfig
 */
@ConfigurationProperties("app.swagger")
@Validated
@Configuration
public class SwaggerProperties {

    /**
     * Enable swagger console.
     */
    boolean enabled = false;
    /**
     * Secure swagger console.
     */
    boolean securedGuiAccess = true;
    /**
     * Username for secured swagger console.
     */
    String username = "admin";
    /**
     * Password for secured swagger console.
     */
    String password = "pass";

    public boolean isSecuredGuiAccess() {
        return securedGuiAccess;
    }

    public void setSecuredGuiAccess(boolean securedGuiAccess) {
        this.securedGuiAccess = securedGuiAccess;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}