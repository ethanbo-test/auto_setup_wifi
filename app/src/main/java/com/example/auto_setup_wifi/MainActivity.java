package com.example.auto_setup_wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSuggestion;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

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
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
          System.out.println("wifi status = " + wifiManager.isWifiEnabled());
          wifiManager.setWifiEnabled(false);
        } else {
          System.out.println("wifi status = " + wifiManager.isWifiEnabled());
          wifiManager.setWifiEnabled(true);
        }
        Toast.makeText(getApplicationContext(), "btn1", Toast.LENGTH_LONG).show();
      }
    });

    btn2.setOnClickListener(new View.OnClickListener() {

      String text = "btn2\n" + String.valueOf("");
      @Override
      public void onClick(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        // wifiManager.setWifiEnabled(false);
        // boolean isWifiEnabled = wifiManager.isWifiEnabled();

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
      }
    });

    btn3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String ssid1 = "ggg";
        String pass1 = "sss";

        String ssid2 = "Pixel_7964";
        String pass2 = "Lb890630";

        final WifiNetworkSuggestion suggestion1 = new WifiNetworkSuggestion.Builder()
            .setSsid(ssid1)
            .setWpa2Passphrase(pass1)
            .build();

        final WifiNetworkSuggestion suggestion2 = new WifiNetworkSuggestion.Builder()
            .setSsid(ssid2)
            .setWpa2Passphrase(pass2)
            .build();

        final List<WifiNetworkSuggestion> lst = new ArrayList<>();
        lst.add(suggestion1);
        lst.add(suggestion2);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int status = wifiManager.addNetworkSuggestions(lst);

        System.out.println("status = " + status);

        boolean isFound = false;
        for (WifiNetworkSuggestion wifiNS : lst) {
          System.out.println("wifiNS = " + wifiNS);
          if (wifiNS != null && wifiNS.getSsid() != null && wifiNS.getSsid() != null) {
            if (wifiNS.getSsid().equals(ssid2) && wifiNS.getPassphrase().equals(pass2)) {
              isFound = true;
              wifiManager.reconnect();
            }
          }
        }

        if (isFound) {
          Toast.makeText(getApplicationContext(), "connected to hotspot", Toast.LENGTH_LONG).show();
          System.out.println("is Found already");
        } else {
          String text = "btn3\n";
          Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
          System.out.println("not found yet");
        }
      }
    });
  }
}