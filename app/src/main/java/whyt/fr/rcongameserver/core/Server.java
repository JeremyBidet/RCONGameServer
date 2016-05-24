package whyt.fr.rcongameserver.core;

import java.util.UUID;

/**
 * Created by Jeremy on 24/05/2016.
 */
public class Server {

    private long id;
    private String name;
    private String ip;
    private int port;
    private int rcon_port;
    private User full_admin;

    public Server(String name, String ip, int port, int rcon_port) {
        this.id = UUID.randomUUID().getLeastSignificantBits();
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.rcon_port = rcon_port;
        this.full_admin = new User("Jeremy", GrantType.FullADMIN);
    }

    public long     getId()         { return this.id; }
    public String   getName()       { return this.name; }
    public String   getIp()         { return this.ip; }
    public int      getPort()       { return this.port; }
    public int      getRCONPort()   { return this.rcon_port; }
    public User     getFullAdmin()  { return this.full_admin; }

    public void setName(String name)        { this.name = name; }
    public void setIp(String ip)            { this.ip = ip; }
    public void setPort(int port)           { this.port = port; }
    public void setRCONPort(int rcon_port)  { this.rcon_port = rcon_port; }

}
