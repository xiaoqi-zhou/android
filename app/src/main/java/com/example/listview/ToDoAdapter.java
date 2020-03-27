package com.example.listview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToDoAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<UserInfo> mUserInfos=new ArrayList<>();
    public ToDoAdapter(Context context, List<UserInfo> userInfos)
    {
        mContext=context;
        mUserInfos=userInfos;
        mLayoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        //有多少条数据
        return mUserInfos.size();
    }
    @Override
    public Object getItem(int position) {
        //返回某一条数据对象
        return mUserInfos.get(position);
    }
    @Override
    public long getItemId(int position) {
        //
        return position;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        //返回一个视图
        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView =mLayoutInflater.inflate(R.layout.item,null);
            viewHolder=new ViewHolder();
            //获取控件
            viewHolder.nameTextView=(TextView)convertView.findViewById(R.id.name_text_view);
            viewHolder.dateTextView=(TextView)convertView.findViewById(R.id.date_text_view);
            viewHolder.complete=(ProgressBar) convertView.findViewById(R.id.progress);
            viewHolder.btddel=(Button)convertView.findViewById(R.id.del_date_things);
            viewHolder.editBtn=(Button)convertView.findViewById(R.id.edit_date_things);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        //和数据绑定
        viewHolder.nameTextView.setText(mUserInfos.get(position).getmUserName());
        viewHolder.dateTextView.setText(mUserInfos.get(position).getmDate());
        viewHolder.complete.setProgress(Integer.parseInt(mUserInfos.get(position).getComplete()));
        //删除
        viewHolder.btddel.setTag(position);
        viewHolder.btddel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInfos.remove(position);
                notifyDataSetChanged();
            }
        });
        //编辑
        viewHolder.editBtn.setTag(position);
        viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传输数据  跳转页面
                Intent intent=new Intent(mContext,EditActivity.class);
                intent.putExtra("user",mUserInfos.get(position));
                intent.putExtra("index",position);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder
    {
        TextView nameTextView;
        TextView dateTextView;
        ProgressBar complete;
        Button editBtn;
        Button btddel;
    }
    //刷新数据

    public void refreshData(List<UserInfo> userInfos)
    {
        mUserInfos=userInfos;
        Collections.sort(mUserInfos);
        notifyDataSetChanged();
        Data.setInfos(mUserInfos);
    }



}
