package com.freeapp.ngocdong.sqliteexample.VIEW;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER.DBS_MANAGER;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.R;

public class TOPIC_MANAGER_ACTIVITY extends AppCompatActivity {

    private TOPIC topic_old;
    private TextView textView;
    private DBS_MANAGER dbs_manager = new DBS_MANAGER(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_topic);
        topic_old = (TOPIC) getIntent().getSerializableExtra("TOPIC");
        textView = (TextView) findViewById(R.id.input_chude);
        if (topic_old != null) {
            textView.setText(topic_old.getCONTENT());
            ((EditText) textView).setSelection(textView.getText().length());
        }
    }

    public void addTOPIC(View view) {
        TOPIC topic = new TOPIC();
        final EditText input = (EditText) findViewById(R.id.input_chude);
        String text = input.getText().toString();
        topic.setCONTENT(text);
        if (topic_old != null) {
            dbs_manager.getDbs_topic_manager().EDIT(topic_old, topic);
        } else {
            dbs_manager.getDbs_topic_manager().ADD(topic);
        }
        dbs_manager.close();
        finish();
    }

    public void Close(View view) {
        dbs_manager.close();
        finish();
    }

}
