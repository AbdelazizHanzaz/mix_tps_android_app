package com.example.makeg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.makeg.R;

import java.util.ArrayList;
import java.util.List;

public class CustomNamesAdapter extends BaseAdapter {

    private List<String> names;
    private final Context mContext;

    public CustomNamesAdapter(Context context){
        mContext = context;
        names = new ArrayList<>();
        names.add("Nassim");
        names.add("Abdelaziz");
        names.add("Ismail");
        names.add("Ahmed");
    }

    public void setNames(List<String> namesList){
        names = namesList;
    }

    public List<String> getNames(){
        return names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public String getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_name, null, false);
        }

        TextView tv_name = v.findViewById(R.id.tv_name);
        tv_name.setText(names.get(i));
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onNameClickListener != null){
                    onNameClickListener.onNameClick(names.get(i));
                }
            }
        });
        tv_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(onNameClickListener != null){
                    onNameClickListener.onNameLongClick(names.get(i));
                }
                return true;
            }
        });

        return v;

    }

    private void makeToastMessage(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private OnNameClickListener onNameClickListener;
    public interface OnNameClickListener{
        void onNameClick(String name);
        void onNameLongClick(String name);
    }

    public void setOnNameClickListener(OnNameClickListener onNameClickListener) {
        this.onNameClickListener = onNameClickListener;
    }
}
