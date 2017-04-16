package com.freeapp.ngocdong.sqliteexample.DBS.CONTROL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.TOPIC;
import com.freeapp.ngocdong.sqliteexample.DBS.DAO.WORD;
import com.freeapp.ngocdong.sqliteexample.VIEW.FORM.Item_Word;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by NgocDong on 03/03/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public MyDatabaseHelper(Context context) {
        super(context, DBS_INFO.DATABASE_NAME(), null, DBS_INFO.DATABASE_VERSION());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Script to create table.
        String script0 = "CREATE TABLE " + DBS_INFO.TOPIC_TABLE() + "( " +
                DBS_INFO.TOPIC_STT() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBS_INFO.TOPIC_CONTENT() + " TEXT NOT NULL UNIQUE)";
        String script1 = "CREATE TABLE " + DBS_INFO.DICTIONARY_TABLE() + "( " +
                DBS_INFO.DICTIONARY_STT() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBS_INFO.DICTIONARY_CONTENT() + " TEXT NOT NULL UNIQUE," +
                DBS_INFO.TOPIC_KEY_REF() + " INTEGER, " +
                "FOREIGN KEY (" + DBS_INFO.TOPIC_KEY_REF() + ") REFERENCES " + DBS_INFO.TOPIC_TABLE() + "(" + DBS_INFO.TOPIC_STT() + ") )";
        String script2 = "CREATE TABLE " + DBS_INFO.MEANING_TABLE() + "( " +
                DBS_INFO.MEANING_STT() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBS_INFO.MEANING_TYPE() + " INTEGER, " +
                DBS_INFO.MEANING_VOICE() + " TEXT, " +
                DBS_INFO.MEANING() + " TEXT  )";
        String script3 = "CREATE TABLE " + DBS_INFO.DICT_MEANING() + "( " +
                DBS_INFO.DICT_STT() + " INTEGER, " +
                DBS_INFO.MEAN_STT() + " INTEGER, " +
                DBS_INFO.WEIGHT() + " INTEGER, " +
                "PRIMARY KEY (" + DBS_INFO.DICT_STT() + ", " + DBS_INFO.MEAN_STT() + "), " +
                "FOREIGN KEY (" + DBS_INFO.DICT_STT() + ") REFERENCES " + DBS_INFO.DICTIONARY_TABLE() + "(" + DBS_INFO.DICT_STT() + ")  ON DELETE CASCADE " +
                " ON UPDATE CASCADE, " +
                "FOREIGN KEY (" + DBS_INFO.MEAN_STT() + ") REFERENCES " + DBS_INFO.MEANING_TABLE() + "(" + DBS_INFO.MEANING_STT() + ")" + "  ON DELETE CASCADE " +
                " ON UPDATE CASCADE )";

        // Execute script.
        db.execSQL(script0);
        // Execute script.
        db.execSQL(script1);
        // Execute script.
        db.execSQL(script2);
        // Execute script.
        db.execSQL(script3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + DBS_INFO.TOPIC_TABLE());
        db.execSQL("DROP TABLE IF EXISTS " + DBS_INFO.DICTIONARY_TABLE());
        db.execSQL("DROP TABLE IF EXISTS " + DBS_INFO.MEANING_TABLE());
        // Recreate
        onCreate(db);
    }

    public int addTOPIC(TOPIC topic) {
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            if (topic.getSTT() == 0) {
                ContentValues values = new ContentValues();
                values.put(DBS_INFO.TOPIC_CONTENT(), topic.getCONTENT());
                // Chèn một dòng dữ liệu vào bảng.
                db.insert(DBS_INFO.TOPIC_TABLE(), null, values);
                Cursor Key = db.rawQuery("SELECT last_insert_rowid()", null);
                Key.moveToFirst();
                Key.close();
                db.close();
                return Key.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void addDICT(TOPIC topic, WORD dict, MEANING meaning) {

        SQLiteDatabase db = this.getWritableDatabase();
        if (topic.getSTT() == 0) {
            ContentValues values = new ContentValues();
            values.put(DBS_INFO.TOPIC_CONTENT(), topic.getCONTENT());
            // Chèn một dòng dữ liệu vào bảng.
            db.insert(DBS_INFO.TOPIC_TABLE(), null, values);
            Cursor Key = db.rawQuery("SELECT last_insert_rowid()", null);
            Key.moveToFirst();
            topic.setSTT(Key.getInt(0));
            Key.close();
        }

        if (dict.getSTT() == 0) {
            dict.setTOPIC_KEY_REF(topic.getSTT());
            //=====================
            ContentValues values = new ContentValues();
            values.put(DBS_INFO.DICTIONARY_CONTENT(), dict.getCONTENT());
            values.put(DBS_INFO.TOPIC_KEY_REF(), dict.getTOPIC_KEY_REF());
            db.insert(DBS_INFO.DICTIONARY_TABLE(), null, values);
            Cursor Key = db.rawQuery("SELECT last_insert_rowid()", null);
            Key.moveToFirst();
            Log.i("added dict: ", String.valueOf(Key.getInt(0)));
            dict.setSTT(Key.getInt(0));
            Key.close();
        }

        if (meaning.getSTT() == 0) {
            ContentValues values = new ContentValues();
            values.put(DBS_INFO.MEANING_TYPE(), meaning.getTYPE());
            values.put(DBS_INFO.MEANING_VOICE(), meaning.getVOICE());
            values.put(DBS_INFO.MEANING(), meaning.getMEANING());
            // Chèn một dòng dữ liệu vào bảng.
            long insert = db.insert(DBS_INFO.MEANING_TABLE(), null, values);
            Cursor Key = db.rawQuery("SELECT last_insert_rowid()", null);
            Key.moveToFirst();
            meaning.setSTT(Key.getInt(0));
            Key.close();
        }
        if (meaning.getSTT() > 0 && dict.getSTT() > 0) {
            ContentValues values = new ContentValues();
            values.put(DBS_INFO.MEAN_STT(), meaning.getSTT());
            values.put(DBS_INFO.DICT_STT(), dict.getSTT());
            // Chèn một dòng dữ liệu vào bảng.
            long insert = db.insert(DBS_INFO.DICT_MEANING(), null, values);
        }
        // Đóng kết nối database.
        db.close();
    }

    public List<TOPIC> getAllTOPIC() {

        List<TOPIC> topicList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DBS_INFO.TOPIC_TABLE();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                TOPIC topic = new TOPIC();
                topic.setSTT(Integer.parseInt(cursor.getString(0)));
                topic.setCONTENT(cursor.getString(1));
                // Thêm vào danh sách.
                topicList.add(topic);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        // return note list
        return topicList;
    }

    public boolean deleteTOPIC(TOPIC topic) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DBS_INFO.TOPIC_TABLE(), DBS_INFO.TOPIC_STT() + " = ?",
                    new String[]{String.valueOf(topic.getSTT())});
            db.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "DELETE ERROR ... " + e.getMessage());
            return false;
        }
    }


    public int editTOPIC(TOPIC topic_old, TOPIC topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBS_INFO.TOPIC_CONTENT(), topic.getCONTENT());
        // updating row
        int rs = db.update(DBS_INFO.TOPIC_TABLE(), values, DBS_INFO.TOPIC_STT() + " = ?",
                new String[]{String.valueOf(topic_old.getSTT())});
        db.close();
        return rs;
    }

    public List<WORD> getAllWORD(TOPIC topic) {
        List<WORD> rs = new ArrayList<>();

        String selectQuery = (topic != null) ?
                "SELECT  * FROM " + DBS_INFO.DICTIONARY_TABLE()
                        + " WHERE " + DBS_INFO.TOPIC_KEY_REF() + " = " + String.valueOf(topic.getSTT())
                : "SELECT  * FROM " + DBS_INFO.DICTIONARY_TABLE();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                WORD word = new WORD();
                word.setSTT(Integer.parseInt(cursor.getString(0)));
                word.setCONTENT(cursor.getString(1));
                word.setTOPIC_KEY_REF(cursor.getInt(2));

                // Thêm vào danh sách.
                rs.add(word);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return rs;
    }

    public List<MEANING> getAllMEANING(WORD w) {
        String
                selectQuery = "SELECT  * FROM " + DBS_INFO.MEANING_TABLE()
                + ", " + DBS_INFO.DICT_MEANING()
                + " WHERE " + DBS_INFO.DICT_MEANING() + "." + DBS_INFO.DICT_STT() + " = '" + String.valueOf(w.getSTT())
                + "' AND " + DBS_INFO.DICT_MEANING() + "." + DBS_INFO.MEAN_STT() + " = " + DBS_INFO.MEANING_TABLE() + "." + DBS_INFO.MEANING_STT();
        List<MEANING> rs = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                MEANING meaning = new MEANING();
                meaning.setSTT(Integer.parseInt(cursor.getString(0)));
                meaning.setTYPE(cursor.getInt(1));
                meaning.setVOICE(cursor.getString(2));
                meaning.setMEANING(cursor.getString(3));
                // Thêm vào danh sách.
                rs.add(meaning);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return rs;
    }

    public boolean deleteWORD(Item_Word selectedWORD) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DBS_INFO.DICTIONARY_TABLE(), DBS_INFO.DICTIONARY_STT() + " = ?",
                    new String[]{String.valueOf(selectedWORD.getWord().getSTT())});
            for (MEANING meaning : selectedWORD.getMeaning()) {
                db.delete(DBS_INFO.MEANING_TABLE(), DBS_INFO.MEANING_STT() + " = ?",
                        new String[]{String.valueOf(meaning.getSTT())});
            }
            db.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "DELETE ERROR ... " + e.getMessage());
            return false;
        }
    }

    public int editWORD(WORD word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBS_INFO.DICTIONARY_CONTENT(), word.getCONTENT());
        values.put(DBS_INFO.TOPIC_KEY_REF(), word.getTOPIC_KEY_REF());
        // updating row
        int rs = db.update(DBS_INFO.DICTIONARY_TABLE(), values, DBS_INFO.DICTIONARY_STT() + " = ?",
                new String[]{String.valueOf(word.getSTT())});
        db.close();
        return rs;
    }

    public boolean deleteMEAN(MEANING meaning) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(DBS_INFO.MEANING_TABLE(), DBS_INFO.MEANING_STT() + " = ?",
                    new String[]{String.valueOf(meaning.getSTT())});
            db.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "DELETE ERROR ... " + e.getMessage());
            return false;
        }
    }

    public int editMEAN(MEANING intentMEAN) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBS_INFO.MEANING_VOICE(), intentMEAN.getVOICE());
        values.put(DBS_INFO.MEANING(), intentMEAN.getMEANING());
        values.put(DBS_INFO.MEANING_TYPE(), intentMEAN.getTYPE());
        // updating row
        int rs = db.update(DBS_INFO.MEANING_TABLE(), values, DBS_INFO.MEANING_STT() + " = ?",
                new String[]{String.valueOf(intentMEAN.getSTT())});
        db.close();
        return rs;
    }

    public int addMEAN(MEANING intentMEAN, Item_Word intentWORD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBS_INFO.MEANING_TYPE(), intentMEAN.getTYPE());
        values.put(DBS_INFO.MEANING_VOICE(), intentMEAN.getVOICE());
        values.put(DBS_INFO.MEANING(), intentMEAN.getMEANING());
        // Chèn một dòng dữ liệu vào bảng.
        long insert = db.insert(DBS_INFO.MEANING_TABLE(), null, values);
        Cursor Key = db.rawQuery("SELECT last_insert_rowid()", null);
        Key.moveToFirst();
        int k = Key.getInt(0);
        intentMEAN.setSTT(k);
        if (intentMEAN.getSTT() > 0 && intentWORD.getWord().getSTT() > 0) {
            values = new ContentValues();
            values.put(DBS_INFO.MEAN_STT(), intentMEAN.getSTT());
            values.put(DBS_INFO.DICT_STT(), intentWORD.getWord().getSTT());
            db.insert(DBS_INFO.DICT_MEANING(), null, values);
        }
        Key.close();
        db.close();
        return k;
    }
}
