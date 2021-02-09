package com.example.taskreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView taskRecyclerView;
    TaskAdapter taskAdapter;
    ArrayList<Task> taskArrayList;
    private int SelectedTask;
    static int UPDATEREQUESTCODE = 1,ADDREQUESTCODE=2,REQUEST_DELETE=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setSupportActionBar(toolbar);

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task(1,R.drawable.ic_launcher_background,"title1","desc"));
        taskArrayList.add(new Task(2,R.drawable.ic_launcher_background,"title2","desc2"));
        taskArrayList.add(new Task(3,R.drawable.ic_launcher_background,"title3","desc3"));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        taskRecyclerView.setLayoutManager(linearLayoutManager);

        taskAdapter = new TaskAdapter(taskArrayList);
        taskRecyclerView.setAdapter(taskAdapter);

        taskAdapter.setOnTaskClickListner(new RVItemCllickListner());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addTaskMenu){
            Intent addTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);

            startActivityForResult(addTaskIntent,ADDREQUESTCODE);
        }
        return super.onOptionsItemSelected(item);
    }


    public class RVItemCllickListner implements TaskAdapter.OnTaskClickListner{

        @Override
        public void onTaskClicked(TaskAdapter taskAdapter, Task task, int position, View view) {
            Snackbar.make(view, task.getImageId(), Snackbar.LENGTH_SHORT).show();

            Intent UpdateTaskintent = new Intent(MainActivity.this, DisplayAndEditActivity.class);
            Task updateTask = taskArrayList.get(position);
            SelectedTask = updateTask.getId();
            UpdateTaskintent.putExtra("task",(Serializable) updateTask);

            startActivityForResult(UpdateTaskintent,UPDATEREQUESTCODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(MainActivity.this, "TASK UPDATING", Toast.LENGTH_SHORT).show();

        if(requestCode == ADDREQUESTCODE && data!=null) {

            //Toast.makeText(MainActivity.this, "ON result ", Toast.LENGTH_SHORT).show();
            Task newTask = (Task)data.getExtras().getSerializable("newTask");
            Log.d("DATA: ",newTask.TaskName);
            taskArrayList.add(newTask);
            taskAdapter.notifyDataSetChanged();

        }else if(requestCode == UPDATEREQUESTCODE && data!=null) {

            if(resultCode==REQUEST_DELETE){

                Task deleteTask = (Task)data.getExtras().getSerializable("deleteTask");
                int index=0;
                for(Task t : taskArrayList){
                    if (t.getId() == deleteTask.getId())
                        break;
                    else
                        index++;
                }
                taskArrayList.remove(index);
                Log.d("index ", index+"");
                taskAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this,"DELETE "+deleteTask.getTaskName(),Toast.LENGTH_SHORT).show();

            }else if(resultCode == RESULT_OK) {

                //Toast.makeText(MainActivity.this,"UPDATE "+resultCode,Toast.LENGTH_SHORT).show();
                Task updatedTask = (Task) data.getExtras().getSerializable("updatedtask");
                Log.d("task: ", updatedTask.getId() + " " + updatedTask.getTaskName());
                taskArrayList.set(updatedTask.getId() - 1, updatedTask);

                taskAdapter.notifyDataSetChanged();

            }
        }




    }
}