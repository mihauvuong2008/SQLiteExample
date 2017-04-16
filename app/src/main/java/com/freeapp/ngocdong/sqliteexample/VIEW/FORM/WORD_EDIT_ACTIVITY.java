package com.freeapp.ngocdong.sqliteexample.VIEW.FORM;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER.DBS_MANAGER;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;
import com.freeapp.ngocdong.sqliteexample.R;
import com.freeapp.ngocdong.sqliteexample.VIEW.CheckItem;

import java.util.ArrayList;
import java.util.List;

public class WORD_EDIT_ACTIVITY extends AppCompatActivity {
    WORD intentWORD;
    TOPIC intentTOPIC;
    private String m_Text;
    private DBS_MANAGER dbs_manager = new DBS_MANAGER(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentWORD = (WORD) getIntent().getSerializableExtra("WORD");
        intentTOPIC = (TOPIC) getIntent().getSerializableExtra("TOPIC");

        setContentView(R.layout.activity_edit_word);
        TextView textView_title = (TextView) findViewById(R.id.textView_EditWordtitle);

        textView_title.setText("SỬA ĐỔI TỪ VỰNG");
        EditText editText = (EditText) findViewById(R.id.input_word);
        editText.setText(intentWORD.getCONTENT());
        ((EditText) editText).setSelection(editText.getText().length());
        final ListView listview = (ListView) this.findViewById(R.id.sub_list_view_chude);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public static final String TAG = "tag";

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick: " + position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                CheckItem user = (CheckItem) listview.getItemAtPosition(position);
                user.setSelected(!currentCheck);
                intentWORD.setTOPIC_KEY_REF(((CheckItem) listview.getAdapter().getItem(position)).getTopic().getSTT());
            }
        });
        viewListviewTOPIC(intentTOPIC, dbs_manager.getDbs_topic_manager().ALL(), listview);
    }

    private void viewListviewTOPIC(TOPIC topic_selected, List<TOPIC> allTOPIC, ListView listview) {
        ArrayAdapter listViewAdapter = new ArrayAdapter<TOPIC>(this,
                android.R.layout.simple_list_item_1, allTOPIC);
        ArrayList<CheckItem> CheckItemList = new ArrayList<CheckItem>();
        for (TOPIC topic : allTOPIC) {
            boolean flag = false;
            CheckItem checkItem = new CheckItem(topic, flag);
            CheckItemList.add(checkItem);
        }
        ArrayAdapter<CheckItem> a = new ArrayAdapter<CheckItem>(this,
                android.R.layout.simple_list_item_checked, CheckItemList);
        listview.setAdapter(a);
        for (int i = 0; i < listview.getAdapter().getCount(); i++) {
            if (intentWORD.getTOPIC_KEY_REF() == ((CheckItem) listview.getAdapter().getItem(i)).getTopic().getSTT())
                listview.setItemChecked(i, true);
        }
    }

    public void save_WORD(View view) {
        EditText editText = (EditText) findViewById(R.id.input_word);
        m_Text = editText.getText().toString();
        intentWORD.setCONTENT(m_Text);
        dbs_manager.getDbs_dictionary_manager().EDIT(intentWORD);
        finish();
    }

    public void Close(View view) {
        finish();
    }

    @Override
    public void finish() {
        dbs_manager.close();
    }
}
