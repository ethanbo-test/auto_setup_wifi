package com.example.auto_setup_wifi;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btn1 = (Button) findViewById(R.id.btn1);

    btn1.setOnClickListener(new View.OnClickListener() {


      @Override
      public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "this is context", Toast.LENGTH_LONG).show();
      }
    });
  }
}