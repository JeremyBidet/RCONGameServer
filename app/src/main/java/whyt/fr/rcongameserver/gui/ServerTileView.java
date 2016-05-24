package whyt.fr.rcongameserver.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import whyt.fr.rcongameserver.R;
import whyt.fr.rcongameserver.core.Server;

/**
 * TODO: document your custom view class.
 */
public class ServerTileView extends View {

    private Server server;

    public ServerTileView(Context context, Server server) {
        super(context);
        init(null, 0);
        this.server = server;
    }

    public ServerTileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ServerTileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ServerTileView, defStyle, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        TextView name = (TextView) findViewById(R.id.serverTileName);
        TextView ip = (TextView) findViewById(R.id.serverTileIp);
        TextView port = (TextView) findViewById(R.id.serverTilePort);
        TextView rcon_port = (TextView) findViewById(R.id.serverTileRCONPort);
        TextView full_admin = (TextView) findViewById(R.id.serverTileFullAdmin);

        name.setText(server.getName());
        ip.setText(server.getIp());
        port.setText(server.getPort());
        rcon_port.setText(server.getRCONPort());
        full_admin.setText(server.getFullAdmin().getUsername());
    }

}
