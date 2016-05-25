package whyt.fr.rcongameserver.network;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Created by Jeremy on 24/05/2016.
 */
public class RCONProtocol {

    private static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");
    private static final ByteBuffer buffer = ByteBuffer.allocate(4096);

    public static ByteBuffer encodeRCONPacket(RCONPacket packet) {
        buffer.clear();
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(packet.getId());
        buffer.putInt(packet.getPacketSize());
        buffer.putInt(packet.getType().getId());
        buffer.put(packet.getBody().getBytes(US_ASCII_CHARSET));
        buffer.put("\0".getBytes(US_ASCII_CHARSET));
        buffer.flip();
        return buffer;
    }

    public static RCONPacket decodeRCONPacket(ByteBuffer buffer) {
        int size = buffer.getInt();
        int id = buffer.getInt();
        RCONType type = RCONType.get(buffer.getInt());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size-4-4-1; i++) {
            sb.append(buffer.get());
        }
        String body = sb.toString();

        return new RCONPacket(id, type, body);
    }

    public static void appendRCONPacket(RCONPacket packet, ByteBuffer buffer) {
        RCONPacket tmp = decodeRCONPacket(buffer);
        assert tmp.getId() == packet.getId();
        assert tmp.getType().equals(packet.getType());
        packet.setBody(packet.getBody() + tmp.getBody());
        packet.setSize(packet.getPacketSize() + tmp.getBody().length());
    }

}
