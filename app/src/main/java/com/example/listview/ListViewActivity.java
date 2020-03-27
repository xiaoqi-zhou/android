package com.example.listview;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.List;

public class ListViewActivity extends Activity {


    private Button btnadd;
    private Button test_add;
    private EditText editThing;
    private EditText editDate;
    private EditText complete;
    private ListView mphoneBookListView;
    private List<UserInfo> muserInfos;
    private View.OnClickListener onDelItem;
    ToDoAdapter mphoneBookAdapter;
    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mphoneBookListView = (ListView)findViewById(R.id.list_view);

        muserInfos = Data.getInfo();

        btnadd=findViewById(R.id.btd1);
        test_add=findViewById(R.id.test_add);
        editThing=findViewById(R.id.edit_text1);
        editDate=findViewById(R.id.edit_text2);
        complete=findViewById(R.id.edit_text3);
        final ToDoAdapter mphoneBookAdapter=new ToDoAdapter(ListViewActivity.this, muserInfos);
        mphoneBookListView.setAdapter(mphoneBookAdapter);
        mphoneBookAdapter.refreshData(muserInfos);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editDate.getText().toString().equals("")&&!editThing.getText().equals(""))
                {
                    muserInfos.add(new UserInfo(editDate.getText().toString(),editThing.getText().toString(),complete.getText().toString()));
                    mphoneBookAdapter.refreshData(muserInfos);
                }
            }
        });

        //显示时间控件
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickDialog();
            }
        });
        editDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    showDatePickDialog();
            }
        });


        test_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(ListViewActivity.this,AddActivity.class);
               startActivity(intent);
            }
        });

        //接受数据
        Intent intent = getIntent();
        UserInfo addUser = (UserInfo)intent.getSerializableExtra("addUser");
        UserInfo editUser = (UserInfo)intent.getSerializableExtra("editUser");
        int index = intent.getIntExtra("index", -1);
        if(addUser!=null){
            //添加用户
            muserInfos.add(addUser);
            mphoneBookAdapter.refreshData(muserInfos);
        }

        if(editUser!=null && index!=-1){
            //修改用户
            muserInfos.set(index,editUser);
            mphoneBookAdapter.refreshData(muserInfos);
        }
    }
    private void showDatePickDialog()
    {

        new DatePickerDialog(ListViewActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                editDate.setText(year+"/"+(month+1)+"/"+dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}

