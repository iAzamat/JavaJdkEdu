package Seminars.Seminar2.Task1;

public class Server implements ServerListener {
    private boolean serverState;
    private String serverStatus;
    private static final String serverStart = "Сервер запущен. Статус сервера: ";
    private static final String serverIsStart = "Сервер уже запущен. Статус сервера: ";
    private static final String serverStop = "Сервер остановлен. Статус сервера: ";
    private static final String serverIsStop = "Сервер уже остановлен. Статус сервера: ";

    public Server() {
        this.serverState = false;
        this.serverStatus = "";
    }

    @Override
    public boolean start() {
        if (serverState) {
            serverStatus = serverIsStart + serverState + System.lineSeparator();
            System.out.println(serverStatus);
        } else {
            serverState = true;
            serverStatus = serverStart + serverState + System.lineSeparator();
            System.out.println(serverStatus);
        }
        return serverState;
    }

    @Override
    public boolean stop() {
        if (serverState) {
            serverState = false;
            serverStatus = serverStop + serverState + System.lineSeparator();
            System.out.println(serverStatus);
        } else {
            serverStatus = serverIsStop + serverState + System.lineSeparator();
            System.out.println(serverStatus);
        }
        return serverState;
    }

    @Override
    public String getServerStatus() {
        return serverStatus;
    }

}
