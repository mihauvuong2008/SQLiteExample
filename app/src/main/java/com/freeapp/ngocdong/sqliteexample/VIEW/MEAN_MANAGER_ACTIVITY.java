package com.freeapp.ngocdong.sqliteexample.VIEW;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.freeapp.ngocdong.sqliteexample.DBS.CONTROL.DBS_MANAGER.DBS_MANAGER;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.R;
import com.freeapp.ngocdong.sqliteexample.VIEW.FORM.Item_Word;

public class MEAN_MANAGER_ACTIVITY extends AppCompatActivity {

    Item_Word intentWORD;
    MEANING intentMEAN;
    private RadioGroup radioGroupDiffLevel;
    private EditText sound;
    private EditText mean;
    private DBS_MANAGER dbs_manager = new DBS_MANAGER(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mean_manager);
        intentWORD = (Item_Word) getIntent().getSerializableExtra("Item_Word");
        intentMEAN = (MEANING) getIntent().getSerializableExtra("MEAN");
        if (intentMEAN == null) intentMEAN = new MEANING();
        TextView title = (TextView) findViewById(R.id.textView_edit_mean_worddata);
        sound = (EditText) findViewById(R.id.edit_sound);
        mean = (EditText) findViewById(R.id.edit_mean);
        sound.setText(intentMEAN.getVOICE());
        mean.setText(intentMEAN.getMEANING());
        title.setText(intentWORD.getWord().getCONTENT());
        setRadioItem(intentMEAN.getTYPE());
        radioGroupDiffLevel = (RadioGroup) findViewById(R.id.radio_word_type);
        this.radioGroupDiffLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnDifficultyLevelChanged(group, checkedId);
            }
        });
    }

    private void doOnDifficultyLevelChanged(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();
        if (checkedRadioId == R.id.radioButton_type_Danhtu) {
            intentMEAN.setTYPE(1);
        } else if (checkedRadioId == R.id.radioButton_type_Daitu) {
            intentMEAN.setTYPE(2);
        } else if (checkedRadioId == R.id.radioButton_type_Tinhtu) {
            intentMEAN.setTYPE(3);
        } else if (checkedRadioId == R.id.radioButton_type_Dongtu) {
            intentMEAN.setTYPE(4);
        } else if (checkedRadioId == R.id.radioButton_type_Trangtu) {
            intentMEAN.setTYPE(5);
        } else if (checkedRadioId == R.id.radioButton_type_Gioitu) {
            intentMEAN.setTYPE(6);
        } else if (checkedRadioId == R.id.radioButton_type_Lientu) {
            intentMEAN.setTYPE(7);
        } else if (checkedRadioId == R.id.radioButton_type_Thantu) {
            intentMEAN.setTYPE(8);
        }
    }

    private void setRadioItem(int type) {
        switch (type) {
            case 1:
                RadioButton rdo_Danhtu = (RadioButton) findViewById(R.id.radioButton_type_Danhtu);
                rdo_Danhtu.setChecked(true);
                break;
            case 2:
                RadioButton rdo_Daitu = (RadioButton) findViewById(R.id.radioButton_type_Daitu);
                rdo_Daitu.setChecked(true);
                break;
            case 3:
                RadioButton rdo_Tinhtu = (RadioButton) findViewById(R.id.radioButton_type_Tinhtu);
                rdo_Tinhtu.setChecked(true);
                break;
            case 4:
                RadioButton rdo_Dongtu = (RadioButton) findViewById(R.id.radioButton_type_Dongtu);
                rdo_Dongtu.setChecked(true);
                break;
            case 5:
                RadioButton rdo_Trangtu = (RadioButton) findViewById(R.id.radioButton_type_Trangtu);
                rdo_Trangtu.setChecked(true);
                break;
            case 6:
                RadioButton rdo_Gioitu = (RadioButton) findViewById(R.id.radioButton_type_Gioitu);
                rdo_Gioitu.setChecked(true);
                break;
            case 7:
                RadioButton rdo_Lientu = (RadioButton) findViewById(R.id.radioButton_type_Lientu);
                rdo_Lientu.setChecked(true);
                break;
            case 8:
                RadioButton rdo_Thantu = (RadioButton) findViewById(R.id.radioButton_type_Thantu);
                rdo_Thantu.setChecked(true);
                break;
        }
    }

    public void saveMEAN(View view) {
        intentMEAN.setVOICE(sound.getText().toString());
        intentMEAN.setMEANING(mean.getText().toString());
        if (intentMEAN.getSTT() != 0) {
            dbs_manager.getDbs_mean_manager().editMEAN(intentMEAN);
        } else {
            int key = dbs_manager.getDbs_mean_manager().ADD(intentMEAN, intentWORD);
        }
        finish();
    }

    public void Close(View view) {
        dbs_manager.close();
        finish();
    }

}
