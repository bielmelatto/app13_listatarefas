package br.edu.ifsp.app13_listatarefas.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.app13_listatarefas.R;
import br.edu.ifsp.app13_listatarefas.mvp.TaskDetailsMVP;
import br.edu.ifsp.app13_listatarefas.presenter.TaskDetailsPresenter;

public class TasksDetailsActivity extends AppCompatActivity implements TaskDetailsMVP.View, View.OnClickListener {

    private TaskDetailsMVP.Presenter presenter;
    private EditText titleET;
    private EditText descriptionET;
    private EditText dateET;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtask);
        presenter = new TaskDetailsPresenter(this);
        findViews();
        setListener();
        setToolbar();
    }

    @Override
    protected void onStart() {
        presenter.verifyUpdate();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v == saveButton){
            presenter.saveTask(
                    titleET.getText().toString(),
                    descriptionET.getText().toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateUI(String title, String description, String creationDate) {
        titleET.setText(title);
        descriptionET.setText(description);
        dateET.setText(creationDate);
        dateET.setVisibility(View.VISIBLE);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void close() {
        presenter.deatach();
        finish();
    }

    private void findViews(){
        titleET = findViewById(R.id.et_title);
        descriptionET = findViewById(R.id.et_description);
        dateET = findViewById(R.id.et_date);
        saveButton = findViewById(R.id.btn_save);
        dateET.setVisibility(View.INVISIBLE);
    }

    private void setListener(){
        saveButton.setOnClickListener(this);
    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
