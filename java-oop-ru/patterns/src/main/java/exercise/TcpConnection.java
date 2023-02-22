package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;


// BEGIN
public class TcpConnection{
    private Connection state;
    private String ip;
    private int port;
    List<String> buff = new ArrayList<>();

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public void setState(Connection state) {
        this.state = state;
    }

    public String getCurrentState(){
        return this.state.getStatus();
    }
    public void connect() {
        this.state.connect();
    }

    public void disconnect(){
        this.state.disconnect();
    }
    public void write(String data) {
        this.state.write(data);
    }
    public void writeBuff(String data){
        buff.add(data);
    }


}
// END
