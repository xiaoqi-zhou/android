package com.example.listview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    private Button btnadd;
    private EditText editThing;
    private EditText editDate;
    private EditText complete;
    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btnadd=findViewById(R.id.add_btd1);
        editThing=findViewById(R.id.add_edit_text1);
        editDate=findViewById(R.id.add_edit_text2);
        complete=findViewById(R.id.add_edit_text3);
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

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editDate.getText().toString().equals("")&&!editThing.getText().equals("")&&!complete.getText().equals(""))
                {
                    UserInfo info = new UserInfo(editDate.getText().toString(),editThing.getText().toString(),complete.getText().toString());
                    Intent intent = new Intent(AddActivity.this, ListViewActivity.class);
                    //传输数据跳转页面
                    intent.putExtra("addUser",info);
                    startActivity(intent);
                }
            }
        });
    }

    private void showDatePickDialog()
    {

        new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                editDate.setText(year+"/"+(month+1)+"/"+dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
