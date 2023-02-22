package exercise.connections;


import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{
    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
    tcpConnection.setState(new Connected(tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println("Error! It is not possible write to closed connection");
    }
    public String getStatus() {
        return "disconnected";
    }

}
// END
