package com.example.auto_setup_wifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  // WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(
  //     Context.WIFI_SERVICE);

  protected WifiManager wifiManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(
    //     Context.WIFI_SERVICE);
    // wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

    Button btn1 = (Button) findViewById(R.id.btn1);

    Button btn2 = (Button) findViewById(R.id.btn2);

    Button btn3 = (Button) findViewById(R.id.btn3);

    btn1.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "btn1", Toast.LENGTH_LONG).show();
      }
    });

    btn2.setOnClickListener(new View.OnClickListener() {

      String text = "btn2\n" + String.valueOf("");
      @Override
      public void onClick(View view) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
      }
    });

    btn3.setOnClickListener(new View.OnClickListener() {
      // boolean isWifiEnabled = wifiManager.isWifiEnabled();
      // String text = "btn3\n" + String.valueOf(isWifiEnabled);
      String text = "btn3\n";

      @Override
      public void onClick(View view) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
      }
    });
  }
}