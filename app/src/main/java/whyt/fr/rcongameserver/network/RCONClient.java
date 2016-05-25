package whyt.fr.rcongameserver.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import whyt.fr.rcongameserver.core.Server;

/**
 * Created by Jeremy on 25/05/2016.
 */
public class RCONClient {

    private static Selector selector;

    public static void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    selector = Selector.open();

                    while(true) {
                        //int readyChannels = selector.select();
                        //if(readyChannels == 0) continue;
                        Set<SelectionKey> selectedKeys = selector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                        while(keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if(!key.isValid()) {
                                keyIterator.remove();
                                continue;
                            }
                            if(key.isConnectable()) {
                                // a connection was established with a remote server.
                                key.interestOps(SelectionKey.OP_READ);
                            } else if(key.isReadable()) {
                                // a channel is ready for reading
                                key.interestOps(SelectionKey.OP_WRITE);
                            } else if(key.isWritable()) {
                                // a channel is ready for writing
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static boolean connectToServer(Server server) {
        try {
            SocketChannel socket = SocketChannel.open(new InetSocketAddress(server.getIp(), server.getPort()));
            socket.configureBlocking(false);
            socket.register(selector, SelectionKey.OP_CONNECT, server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void sendRCONPacket(Server server, RCONPacket packet) {
        ByteBuffer bb = RCONProtocol.encodeRCONPacket(packet);
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
