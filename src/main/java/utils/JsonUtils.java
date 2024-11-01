package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonUtils {
    private final JsonNode jsonNode;

    public JsonUtils(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonNode = objectMapper.readTree(new File(filePath));
    }

    public String getValidUsername() {
        return jsonNode.get("validCredentials").get("username").asText();
    }

    public String getValidPassword() {
        return jsonNode.get("validCredentials").get("password").asText();
    }

    public String getInvalidUsername() {
        return jsonNode.get("invalidCredentials").get("username").asText();
    }

    public String getInvalidPassword() {
        return jsonNode.get("invalidCredentials").get("password").asText();
    }

    public String getUsernameRequiredMessage() {
        return jsonNode.get("errorMessages").get("usernameRequired").asText();
    }

    public String getPasswordRequiredMessage() {
        return jsonNode.get("errorMessages").get("passwordRequired").asText();
    }

    public String getLoginFailedMessage() {
        return jsonNode.get("errorMessages").get("loginFailed").asText();
    }
}
