package com.example.taskreminder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.Serializable;
import java.util.Random;

import static com.example.taskreminder.MainActivity.ADDREQUESTCODE;

public class AddTaskActivity extends AppCompatActivity {
    ImageView imageView;
    EditText updateTitleEt, updateDescriptionEt;
    Button updateBtn;

    int     imgId;
    String  Title;
    String  Desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setTitle("Add Task");
        init();

    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        updateTitleEt = findViewById(R.id.updateTitleEt);
        updateDescriptionEt = findViewById(R.id.updateDescriptionEt);
        updateBtn = findViewById(R.id.updateBtn);
        imgId    = R.drawable.ic_launcher_background;
        updateBtn.setText("ADD TASK");

        imageView.setOnClickListener(new selectImageListner());
        updateBtn.setOnClickListener(new updateBtnClickListner());
    }

    class updateBtnClickListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Title    = updateTitleEt.getText().toString();
            Desc     = updateDescriptionEt.getText().toString();

            Intent output = new Intent();
            Task newTask = new Task(new Random().nextInt(),imgId,Title,Desc);
            output.putExtra("newTask",(Serializable)newTask);
            setResult(RESULT_OK,output);
            finish();
        }
    }

    class selectImageListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddTaskActivity.this, SelectImageActivity.class);

            startActivityForResult(intent,ADDREQUESTCODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADDREQUESTCODE && data!=null){
                imgId = data.getIntExtra("imgid",0);
                imageView.setImageResource(imgId);
                //Toast.makeText(AddTaskActivity.this,imgId+"",Toast.LENGTH_SHORT).show();
        }

    }
}