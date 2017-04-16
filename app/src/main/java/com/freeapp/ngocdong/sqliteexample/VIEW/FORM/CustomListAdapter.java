package com.freeapp.ngocdong.sqliteexample.VIEW.FORM;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeapp.ngocdong.sqliteexample.DBS.DAO.MEANING;
import com.freeapp.ngocdong.sqliteexample.R;

import java.util.List;

/**
 * Created by NgocDong on 06/03/2017.
 */

public class CustomListAdapter extends BaseExpandableListAdapter {

    private List<Item_Word> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public List<Item_Word> getListData() {
        return listData;
    }

    public CustomListAdapter(Context aContext, List<Item_Word> listData) {
        this.context = aContext;
        this.listData = listData;
//        layoutInflater = LayoutInflater.from(aContext);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return listData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listData.get(groupPosition).getMeaning().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listData.get(groupPosition).getMeaning().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewParentItem holder;
        isExpanded = false;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.parent_item_tuvung, null);
            holder = new ViewParentItem();
            holder.icon = (ImageView) convertView.findViewById(R.id.imageView_wordIcon);
            holder.word = (TextView) convertView.findViewById(R.id.textView_word);
            holder.mean = (TextView) convertView.findViewById(R.id.textView_mean_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewParentItem) convertView.getTag();
        }

        Item_Word parentItem = (Item_Word) getGroup(groupPosition);
        if (parentItem != null) {
            holder.word.setText(parentItem.getWord().getCONTENT());
            if (parentItem.getMeaning() != null) {
                if (parentItem.getMeaning().size() > 0) {
                    holder.mean.setText(parentItem.getMeaning().get(0).getMEANING());
                }
            }
            int imageId = R.drawable.idea;
            holder.icon.setImageResource(imageId);
        }
        return convertView;
    }

    static class ViewParentItem {
        ImageView icon;
        TextView word;
        TextView mean;
    }

    static class ViewChildItem {
        TextView sound;
        TextView type;
        TextView mean;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewChildItem holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.child_item_tuvung, null);
            holder = new ViewChildItem();
            holder.sound = (TextView) convertView.findViewById(R.id.textView_phienam);
            holder.type = (TextView) convertView.findViewById(R.id.textView_tuloai);
            holder.mean = (TextView) convertView.findViewById(R.id.textView_mean);
            convertView.setTag(holder);
        } else {
            holder = (ViewChildItem) convertView.getTag();
        }

        MEANING meanItem = (MEANING) getChild(groupPosition, childPosition);
        if (meanItem != null) {
            holder.sound.setText(meanItem.getVOICE().toString());
            holder.type.setText(String.valueOf(meanItem.getTYPE()));
            holder.mean.setText(meanItem.getMEANING().toString());
            int imageId = 0;
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
