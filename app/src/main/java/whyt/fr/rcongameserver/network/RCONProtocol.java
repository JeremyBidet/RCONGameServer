package whyt.fr.rcongameserver.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import whyt.fr.rcongameserver.core.Server;

/**
 * Created by Jeremy on 24/05/2016.
 */
public class RCONProtocol {

    private static final Charset US_ASCII_CHARSET = Charset.forName("US-ASCII");
    private static final ByteBuffer bb = ByteBuffer.allocate(4096);

    public static RCONPacket createRCONPacket(RCONType type, String body) {
        return new RCONPacket(type, body);
    }

    private static ByteBuffer encodeRCONPacket(RCONPacket packet) {
        bb.clear();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putInt(packet.getId());
        bb.putInt(packet.getPacketSize());
        bb.putInt(packet.getType().getId());
        bb.put(packet.getBody().getBytes(US_ASCII_CHARSET));
        bb.put("\0".getBytes(US_ASCII_CHARSET));
        bb.flip();
        return bb;
    }

    public static boolean connectToServer() {
        return false;
    }

    public static void sendRCONPacket(Server server, RCONPacket packet) {
        ByteBuffer bb = encodeRCONPacket(packet);
        PrintWriter out;
        BufferedReader in;
        try {
            InetAddress serverAddr = InetAddress.getByName(server.getIp());
            Log.e("TCP Client", "C: Connecting...");
            //create a socket to make the connection with the server
            Socket socket = new Socket(serverAddr, server.getRCONPort());

            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
