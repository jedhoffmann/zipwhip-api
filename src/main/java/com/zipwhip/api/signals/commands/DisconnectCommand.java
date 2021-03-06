package com.zipwhip.api.signals.commands;

/**
 * @author jed
 * 
 */
public class DisconnectCommand extends Command {

    public static final String ACTION = "disconnect";

    private String host;
    private int port;
    private int reconnectDelay;
    private boolean stop;
    private boolean ban;

    /**
     * Create a new DisconnectCommand
     * 
     * @param host
     *        Host to reconnect to. Empty string indicates no change.
     * @param port
     *        Port to reconnect to. Empty string indicates no change.
     * @param reconnectDelay
     *        Milliseconds to wait before reconnecting.
     * @param stop
     *        If true do not reconnect.
     * @param ban
     *        If true the server will refuse reconnect attempts with the same clientId.
     */
    public DisconnectCommand(String host, int port, int reconnectDelay, boolean stop, boolean ban) {
        this.host = host;
        this.port = port;
        this.reconnectDelay = reconnectDelay;
        this.stop = stop;
        this.ban = ban;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getReconnectDelay() {
        return reconnectDelay;
    }

    public boolean isStop() {
        return stop;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

}
