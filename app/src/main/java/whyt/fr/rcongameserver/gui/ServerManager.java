package whyt.fr.rcongameserver.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

import whyt.fr.rcongameserver.R;
import whyt.fr.rcongameserver.core.GrantType;
import whyt.fr.rcongameserver.core.Server;
import whyt.fr.rcongameserver.core.User;

public class ServerManager extends AppCompatActivity {

    private User current_user;
    private GridView server_tiles_container;
    private ArrayList<Server> servers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton addServerButton = (FloatingActionButton) findViewById(R.id.addServer);
        addServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        current_user = new User("username", GrantType.FullADMIN);

        int port = 27015;
        int rcon_port = 32330;
        for(int i=1; i<10; i++) {
            servers.add(new Server("Server."+i, "68.215.30."+i, port+i, rcon_port+i));
        }

        server_tiles_container = (GridView) findViewById(R.id.serverTilesGrid);

        server_tiles_container.setAdapter(new ServerTileAdapter(this, servers));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
