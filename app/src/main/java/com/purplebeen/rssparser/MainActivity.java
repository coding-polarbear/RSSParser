package com.purplebeen.rssparser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText editText;
    public static String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        url = editText.getText().toString();
        url = editText.getText().toString() + "/rss";
        Intent i = new Intent(this, ItemListActivity.class);
        startActivity(i);

    }
}
