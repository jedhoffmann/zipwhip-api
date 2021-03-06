package com.zipwhip.api.dto;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Date: Jul 19, 2009
 * Time: 2:37:47 PM
 * <p/>
 * Represents a user.
 */
public class DeviceToken implements Serializable {

    private static final long serialVersionUID = 5874121954372365L;

    Device device;
    String sessionKey;
    String apiKey;
    String secret;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder("==> DeviceToken details:");
        toStringBuilder.append("\nDevice: ").append(device.toString());
        toStringBuilder.append("\nSessionKey: ").append(sessionKey);
        toStringBuilder.append("\nApiKey: ").append(apiKey);

        return toStringBuilder.toString();
    }

}
