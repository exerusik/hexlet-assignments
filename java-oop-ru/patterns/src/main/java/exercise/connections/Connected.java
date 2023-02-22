package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection{
    TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection has established already");
    }

    @Override
    public void disconnect() {
    tcpConnection.setState(new Disconnected(tcpConnection));
    }

    @Override
    public void write(String data) {
    tcpConnection.writeBuff(data);
    }
    public String getStatus() {
        return "connected";
    }

}
// END
