package com.example.taskreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import static com.example.taskreminder.MainActivity.ADDREQUESTCODE;
import static com.example.taskreminder.MainActivity.REQUEST_DELETE;

public class DisplayAndEditActivity extends AppCompatActivity {
    ImageView imageView;
    TextView titleTv,descTv;
    Task task;
    Toolbar toolbar;
    EditText titleEt,descEt;
    Button   updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_and_edit);

        task = (Task)getIntent().getExtras().getSerializable("task");
        init();
        setSupportActionBar(toolbar);

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);
        titleTv  = findViewById(R.id.titleTv);
        descTv = findViewById(R.id.descTv);

        imageView.setImageResource(task.ImageId);
        titleTv.setText(task.getTaskName());
        descTv.setText(task.TaskDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.editmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.editBtn){

            setContentView(R.layout.activity_add_task);
            imageView = findViewById(R.id.imageView);
            titleEt    = findViewById(R.id.updateTitleEt);
            descEt     = findViewById(R.id.updateDescriptionEt);
            updateBtn  = findViewById(R.id.updateBtn);

            imageView.setImageResource(task.getImageId());
            titleEt.setText(task.getTaskName());
            descEt.setText(task.getTaskDescription());
            updateBtn.setOnClickListener(new updateTaskClickListner());
            imageView.setOnClickListener(new selectImageListner());
        }
        else if(item.getItemId() == R.id.deleteBtn){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("DELETE TASK", new AlertDialogDeleteBtn());
            builder.setNegativeButton("CANCLE", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.setTitle("Delete Task");
            alertDialog.setMessage("Are you sure you want to delete this task ?");
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    class selectImageListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(DisplayAndEditActivity.this, SelectImageActivity.class);

            startActivityForResult(intent,ADDREQUESTCODE);
        }
    }

    class updateTaskClickListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            Intent output = new Intent();
            task.setTaskName(titleEt.getText().toString());
            task.setTaskDescription(descEt.getText().toString());

            output.putExtra("updatedtask",(Serializable)task);
            setResult(RESULT_OK,output);
            finish();


        }
    }

    class AlertDialogDeleteBtn implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            //Toast.makeText(DisplayAndEditActivity.this,"DELETE BUTTON CLICKED"+task.getTaskName(),Toast.LENGTH_SHORT).show();

            Intent output = new Intent();
            output.putExtra("deleteTask", (Serializable)task);
            setResult(REQUEST_DELETE,output);
            finish();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADDREQUESTCODE && data!=null){
            int imgId = data.getIntExtra("imgid",0);
            task.setImageId(imgId);
            imageView.setImageResource(imgId);
            //Toast.makeText(DisplayAndEditActivity.this,imgId+"",Toast.LENGTH_SHORT).show();
        }
    }
}