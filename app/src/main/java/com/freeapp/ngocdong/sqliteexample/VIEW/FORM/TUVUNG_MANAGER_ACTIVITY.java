package com.freeapp.ngocdong.sqliteexample.VIEW.FORM;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER.DBS_MANAGER;
import com.freeapp.ngocdong.sqliteexample.VIEW.MEAN_MANAGER_ACTIVITY;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;
import com.freeapp.ngocdong.sqliteexample.R;

import java.util.ArrayList;
import java.util.List;

public class TUVUNG_MANAGER_ACTIVITY extends AppCompatActivity {

    private static final int MENU_ITEM_EDIT_WORD = 111;
    private static final int MENU_ITEM_ADD_MEAN = 222;
    private static final int MENU_ITEM_DELETE_WORD = 333;
    private static final int MENU_ITEM_EDIT_MEAN = 444;
    private static final int MENU_ITEM_DELETE_MEAN = 555;

    private static final int MY_REQUEST_CODE = 1000;
    ExpandableListView expandableListView;
    private TOPIC topic;
    private CustomListAdapter customListAdapter;
    private DBS_MANAGER dbs_manager = new DBS_MANAGER(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tuvung);
        Intent intent = getIntent();
        topic = (TOPIC) intent.getSerializableExtra("TOPIC");
        final TextView textView = (TextView) findViewById(R.id.showtuvung_textView_topicname);
        if (topic != null) {
            textView.setText(topic.getCONTENT());
        } else {
            textView.setText("Tất cả từ vựng");
        }
        expandableListView = (ExpandableListView) findViewById(R.id.showtuvung_listWord);
        viewListview(dbs_manager.getDbs_dictionary_manager().ALL(topic));

        registerForContextMenu(this.expandableListView);
        //Khởi tạo và bắt sự kiện cho FloatingActionButton.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_addWord);
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thêm từ từ mới vào chủ đề của bạn", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if (topic != null) {
                    add_WORD(topic, (ViewGroup) findViewById(R.id.showtuvung_listWord), customListAdapter);
                } else {
                }
            }
        });
    }

    public void add_WORD(final TOPIC topic, final ViewGroup viewGroup, final CustomListAdapter customListAdapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("THÊM TỪ VỰNG");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.text_add_word, viewGroup, false);
        final EditText input = (EditText) viewInflated.findViewById(R.id.input_word);
        builder.setView(viewInflated);

        // Set up the buttons
        final AlertDialog dialog = builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String m_Text = input.getText().toString();
                WORD word = new WORD();
                word.setCONTENT(m_Text);
                word.setTOPIC_KEY_REF(topic.getSTT());

                MEANING meaning = new MEANING();
                meaning.setMEANING("nghĩa của từ");
                meaning.setTYPE(1);
                meaning.setVOICE("sound");
                dbs_manager.addDICT(topic, word, meaning);
                viewListviewWORD(dbs_manager.getDbs_dictionary_manager().ALL(topic));
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

    private void viewListviewWORD(List<WORD> allWORD) {
        List<Item_Word> itemdata = GET_List_Item_Word(allWORD);
        customListAdapter = new CustomListAdapter(this, itemdata);
        expandableListView.setAdapter(customListAdapter);
        customListAdapter.notifyDataSetChanged();
    }

    public List<Item_Word> GET_List_Item_Word(List<WORD> allWORD) {
        List<Item_Word> rs = new ArrayList<>();
        for (WORD w : allWORD) {
            List<MEANING> meaningList = dbs_manager.getDbs_mean_manager().ALL(w);
            Item_Word item_word = new Item_Word();
            item_word.setWord(w);
            item_word.setMeaning(meaningList);
            rs.add(item_word);
        }
        return rs;
    }

    private void viewListview(List<WORD> allWORD) {
        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.showtuvung_listWord);
        List<Item_Word> itemdata = GET_List_Item_Word(allWORD);
        customListAdapter = new CustomListAdapter(this, itemdata);
        this.expandableListView.setAdapter(customListAdapter);
        this.customListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Chọn Hành động");
        menu.setHeaderIcon(R.mipmap.add2);
//        menu.setHeaderView(view)
        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_EDIT_WORD, 0, "Sửa Từ vựng");
        menu.add(0, MENU_ITEM_DELETE_WORD, 1, "Xóa Từ vựng");
        menu.add(0, MENU_ITEM_ADD_MEAN, 2, "Thêm Bản dịch");
        menu.add(0, MENU_ITEM_EDIT_MEAN, 4, "Thay đổi Bản dịch");
        menu.add(0, MENU_ITEM_DELETE_MEAN, 5, "Xóa Bản dịch");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo
                info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
        viewListview(dbs_manager.getDbs_dictionary_manager().ALL(topic));
        final Item_Word selectedWORD = (Item_Word) this.customListAdapter.getGroup(groupPos);

        MEANING meaning = null;
        if (childPos >= 0)
            meaning = (MEANING) this.customListAdapter.getChild(groupPos, childPos);
        if (item.getItemId() == MENU_ITEM_EDIT_WORD) {
            // show text
            Toast.makeText(getApplicationContext(), selectedWORD.getWord().getCONTENT(), Toast.LENGTH_LONG).show();
            editWORD(topic, selectedWORD.getWord());
        } else if (item.getItemId() == MENU_ITEM_DELETE_WORD) {
            new AlertDialog.Builder(this)
                    .setMessage(selectedWORD.getWord().getCONTENT() + ". Bạn muốn xóa Nội dung này?")
                    .setCancelable(false)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteWORD(selectedWORD);
                        }
                    })
                    .setNegativeButton("Đóng", null)
                    .show();
        } else if (item.getItemId() == MENU_ITEM_ADD_MEAN) {
            addMEAN(selectedWORD);
        } else if (item.getItemId() == MENU_ITEM_EDIT_MEAN) {
            final MEANING finalMeaning = meaning;
            if (finalMeaning != null) {
                editMEAN(selectedWORD, finalMeaning);
            }

        } else if (item.getItemId() == MENU_ITEM_DELETE_MEAN) {
            // Hỏi trước khi xóa.
            final MEANING finalMeaning = meaning;
            new AlertDialog.Builder(this)
                    .setMessage(selectedWORD.getWord().getCONTENT() + ". Bạn muốn xóa Nội dung này?")
                    .setCancelable(false)
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (finalMeaning != null)
                                deleteMEAN(finalMeaning);
                        }
                    })
                    .setNegativeButton("Đóng", null)
                    .show();
        } else {
            return false;
        }
        return true;
    }

    private void addMEAN(Item_Word selectedWORD) {
        Intent intent = new Intent(this, MEAN_MANAGER_ACTIVITY.class);
        intent.putExtra("Item_Word", selectedWORD);
        this.startActivity(intent);
    }

    private void editMEAN(Item_Word selectedWORD, MEANING finalMeaning) {
        Intent intent = new Intent(this, MEAN_MANAGER_ACTIVITY.class);
        intent.putExtra("Item_Word", selectedWORD);
        intent.putExtra("MEAN", finalMeaning);
        this.startActivity(intent);
    }

    private void editWORD(TOPIC topic, WORD word) {
        Intent editWORDIntent = new Intent(this, WORD_EDIT_ACTIVITY.class);
        editWORDIntent.putExtra("WORD", word);
        editWORDIntent.putExtra("TOPIC", topic);
        this.startActivity(editWORDIntent);
    }

    private void deleteMEAN(MEANING meaning) {
        dbs_manager.getDbs_mean_manager().DEL(meaning);
        viewListview(dbs_manager.getDbs_dictionary_manager().ALL(topic));
    }

    private void deleteWORD(Item_Word selectedWORD) {
        dbs_manager.getDbs_dictionary_manager().DEL(selectedWORD);
        this.customListAdapter.getListData().remove(selectedWORD);
        this.customListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewListview(dbs_manager.getDbs_dictionary_manager().ALL(topic));
    }
}
