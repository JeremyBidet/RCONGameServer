package whyt.fr.rcongameserver.network;

/**
 * Created by Jeremy on 24/05/2016.
 */
public enum RCONType {

    SERVERDATA_AUTH(3),
    SERVERDATA_AUTH_RESPONSE(2),
    SERVERDATA_EXECCOMMAND(2),
    SERVERDATA_RESPONSE_VALUE(0);

    private int id;

    RCONType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
