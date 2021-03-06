package com.zipwhip.api;

import com.zipwhip.api.response.JsonResponseParser;
import com.zipwhip.api.response.ResponseParser;
import com.zipwhip.api.response.ServerResponse;
import com.zipwhip.api.response.StringServerResponse;
import com.zipwhip.concurrent.NetworkFuture;
import com.zipwhip.util.SignTool;
import com.zipwhip.util.Factory;
import com.zipwhip.util.StringUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Creates a Connection with the specified parameters.
 */
public class ApiConnectionFactory implements Factory<ApiConnection> {

    private static final Logger LOGGER = Logger.getLogger(ApiConnectionFactory.class);

    private ResponseParser responseParser = new JsonResponseParser();

    private String host = ApiConnection.DEFAULT_HOST;
    private String apiVersion = ApiConnection.DEFAULT_API_VERSION;
    private String username;
    private String password;
    private String apiKey;
    private String secret;
    private String sessionKey;

    private ApiConnection connection;

    protected ApiConnectionFactory() {

    }

    public ApiConnectionFactory(ApiConnection connection) {
        this.connection = connection;
    }

    public static ApiConnectionFactory newInstance() {
        return new ApiConnectionFactory(new HttpConnection());
    }

    public static ApiConnectionFactory newAsyncInstance() {
        return new ApiConnectionFactory(new NingHttpConnection());
    }

    public static ApiConnectionFactory newAsyncHttpsInstance() {

        ApiConnection connection = new NingHttpConnection();
        connection.setHost(ApiConnection.DEFAULT_HTTPS_HOST);

        return new ApiConnectionFactory(connection);
    }

    /**
     * Creates a generic unauthenticated ApiConnection.
     *
     * @return Connection an authenticated Connection
     */
    @Override
    public ApiConnection create() {

        try {
            if (connection == null) {
                connection = new HttpConnection();
            }

            connection.setSessionKey(sessionKey);
            connection.setApiVersion(apiVersion);
            connection.setHost(host);

            if (StringUtil.exists(apiKey) && StringUtil.exists(secret)) {
                connection.setAuthenticator(new SignTool(apiKey, secret));
            }

            // We have a username/password
            if (StringUtil.exists(username) && StringUtil.exists(password)) {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("mobileNumber", username);
                params.put("password", password);

                NetworkFuture<String> future = connection.send("user/login", params);

                future.awaitUninterruptibly();

                if (!future.isSuccess()){
                    throw new RuntimeException("Cannot create connection, login rejected");
                }

                ServerResponse serverResponse = responseParser.parse(future.getResult());

                if (serverResponse instanceof StringServerResponse) {
                    connection.setSessionKey(((StringServerResponse) serverResponse).response);
                }
            }

            return connection;

        } catch (Exception e) {

            LOGGER.error("Error creating Connection", e);

            return null;
        }
    }

    public ApiConnectionFactory responseParser(ResponseParser responseParser) {
        this.responseParser = responseParser;
        return this;
    }

    public ApiConnectionFactory username(String username) {
        this.username = username;
        return this;
    }

    public ApiConnectionFactory password(String password) {
        this.password = password;
        return this;
    }

    public ApiConnectionFactory apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public ApiConnectionFactory secret(String secret) {
        this.secret = secret;
        return this;
    }

    public ApiConnectionFactory sessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        return this;
    }

    public ApiConnectionFactory host(String host) {
        this.host = host;
        return this;
    }

    public ApiConnectionFactory apiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

}
