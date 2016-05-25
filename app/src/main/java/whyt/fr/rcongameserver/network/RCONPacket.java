package whyt.fr.rcongameserver.network;

/**
 * Created by Jeremy on 24/05/2016.
 */
class RCONPacket {

    private static int autoinc_id = 0;

    private int id;
    private RCONType type;
    private String body;

    private int packet_size;
    private int total_size;

    public RCONPacket(RCONType type, String body) {
        this.id = autoinc_id++;
        this.type = type;
        this.body = body;

        this.packet_size = 4 + 4 + body.length() + 1;
        this.total_size = this.packet_size + 4;
    }

    public RCONPacket(int id, RCONType type, String body) {
        this(type, body);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public RCONType getType() {
        return this.type;
    }

    public String getBody() {
        return this.body;
    }

    public int getPacketSize() {
        return this.packet_size;
    }

    public int getTotalSize() {
        return this.total_size;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSize(int size) {
        this.packet_size = size;
        this.total_size += size;
    }

}
