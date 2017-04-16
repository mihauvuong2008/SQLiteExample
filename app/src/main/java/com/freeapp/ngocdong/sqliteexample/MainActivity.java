package com.freeapp.ngocdong.sqliteexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER.DBS_MANAGER;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.GAME_START_Activity;
import com.freeapp.ngocdong.sqliteexample.VIEW.TOPIC_MANAGER_ACTIVITY;
import com.freeapp.ngocdong.sqliteexample.VIEW.FORM.TUVUNG_MANAGER_ACTIVITY;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private ListView listView;
    private ArrayAdapter<TOPIC> listViewAdapter;
    private static final int MY_REQUEST_CODE = 1000;
    private DBS_MANAGER dbs_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load Listview Chu de
        dbs_manager = new DBS_MANAGER(this);
        viewListview(dbs_manager.getDbs_topic_manager().ALL());
//        Khởi tạo 1 toolbar mới thay cho ActionBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Khởi tạo và bắt sự kiện cho FloatingActionButton.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thêm từ dữ liệu vào từ điển của bạn", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                ADDTOPIC((ViewGroup) findViewById(R.id.listview_chude));
            }
        });
        //Khởi tạo Navigation
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void ADDTOPIC(final ViewGroup viewById) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("THÊM CHỦ ĐỀ");
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.text_add_chude, viewById, false);
        // Set up the input
        final EditText input = (EditText) viewInflated.findViewById(R.id.input_chude2);
        builder.setView(viewInflated);
        // Set up the buttons
        final AlertDialog dialog = builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String m_Text = input.getText().toString();
                TOPIC topic = new TOPIC();
                topic.setCONTENT(m_Text);
                topic.setSTT(dbs_manager.getDbs_topic_manager().ADD(topic));
                refreshData(dbs_manager.getDbs_topic_manager().ALL(), (ListView) viewById);
            }
        }).create();
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.parseColor("#4d80b3"));
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.parseColor("#4d80b3"));
    }

    public void refreshData(List<TOPIC> list, ListView listView) {
        ArrayAdapter<TOPIC> adapter = new ArrayAdapter<TOPIC>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    private void viewListview(List<TOPIC> allTOPIC) {
        this.listViewAdapter = new ArrayAdapter<TOPIC>(this,
                android.R.layout.simple_list_item_1, allTOPIC);
        listView = (ListView) findViewById(R.id.listview_chude);
        this.listView.setAdapter(this.listViewAdapter);
        // Đăng ký Context menu cho ListView.
        registerForContextMenu(this.listView);
    }

    //Sự kiện khi nhấn vào nút back trên thiết bị
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    // Bắt sự kiện khi click vào mỗi item trong NavigationView
    //Ở đây đang là không làm gì cả, chỉ đóng NavigationView lại
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_topic) {
        } else if (id == R.id.nav_dictionary) {
            Intent intent = new Intent(this, TUVUNG_MANAGER_ACTIVITY.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_training) {
            Intent intent = new Intent(this, GAME_START_Activity.class);
            this.startActivity(intent);
        } else if (id == R.id.nav_addword) {

        } else if (id == R.id.nav_hightscore) {

        } else if (id == R.id.nav_myfavcollection) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Chọn Hành động");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_on_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final TOPIC selectedTOPIC = (TOPIC) this.listView.getItemAtPosition(info.position);

        if (item.getItemId() == R.id.xem_chude_item_contextmenu) {
            // show text
            Toast.makeText(getApplicationContext(), selectedTOPIC.getCONTENT(), Toast.LENGTH_LONG).show();
            // mo intent
            Intent intent = new Intent(this, TUVUNG_MANAGER_ACTIVITY.class);
            intent.putExtra("TOPIC", selectedTOPIC);
            this.startActivityForResult(intent, MY_REQUEST_CODE);
        } else if (item.getItemId() == R.id.them_chude_item_contextmenu) {
            Intent intent = new Intent(this, TOPIC_MANAGER_ACTIVITY.class);
            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent, MY_REQUEST_CODE);
            viewListview(dbs_manager.getDbs_topic_manager().ALL());
        } else if (item.getItemId() == R.id.thay_doi_chude_item_contextmenu) {
            Intent intent = new Intent(this, TOPIC_MANAGER_ACTIVITY.class);
            intent.putExtra("TOPIC", selectedTOPIC);
            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent, MY_REQUEST_CODE);
            viewListview(dbs_manager.getDbs_topic_manager().ALL());
        } else if (item.getItemId() == R.id.xoa_chude_item_contextmenu) {
            // Hỏi trước khi xóa.
            new AlertDialog.Builder(this)
                    .setMessage(selectedTOPIC.getCONTENT() + ". Bạn muốn xóa chủ đề này?")
                    .setCancelable(false)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteTOPIC(selectedTOPIC);
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
        } else {
            return false;
        }
        return true;
    }

    private void deleteTOPIC(TOPIC selectedTOPIC) {
        dbs_manager.getDbs_topic_manager().DEL(selectedTOPIC);
        this.listViewAdapter.remove(selectedTOPIC);
        this.listViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
//        viewListview(dbs_manager.getDbs_topic_manager().ALL());
    }
}