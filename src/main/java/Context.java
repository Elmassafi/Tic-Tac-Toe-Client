/**
 * @author Anas EL MASSAFI
 */
public class Context {
    private static ServerDistant server;
    private static ClientImpl client;
    private static Dashboard dashboard;
    private static GameDistant game;
    private static Board board;

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

    public static Dashboard getDashboard() {
        return dashboard;
    }

    public static void setDashboard(Dashboard dashboard) {
        Context.dashboard = dashboard;
    }

    public static GameDistant getGame() {
        return game;
    }

    public static void setGame(GameDistant game) {
        Context.game = game;
    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        Context.board = board;
    }
}
