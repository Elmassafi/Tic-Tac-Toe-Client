/**
 * @author Anas EL MASSAFI
 */
public class Context {
    private static ServerDistant server;
    private static ClientImpl client;
    public static ServerDistant getServer() {
        return server;
    }

    public static void setServer(ServerDistant server) {
        Context.server = server;
    }

    public static ClientImpl getClient() {
        return client;
    }

    public static void setClient(ClientImpl client) {
        Context.client = client;
    }
}
