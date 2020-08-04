/**
 * @author Anas EL MASSAFI
 */
public class Context {
    public static Loading loading = new Loading();

    private static ServerDistant server;
    private static HumanPlayer client;
    private static Home home;
    private static Dashboard dashboard;
    private static GameDistant game;
    private static Board board;

    public static ServerDistant getServer() {
        return server;
    }

    public static void setServer(ServerDistant server) {
        Context.server = server;
    }

    public static HumanPlayer getClient() {
        return client;
    }

    public static void setClient(HumanPlayer client) {
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

    public static Home getHome() {
        return home;
    }

    public static void setHome(Home home) {
        Context.home = home;
    }

}
