package com.example.auto_setup_wifi;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.GroupCipher;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
// import android.net.wifi.WifiNetworkSuggestion;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  // WifiManager wifiManager = (WifiManager) this.getApplicationContext().getSystemService(
  //     Context.WIFI_SERVICE);

  private Button btn1;
  private Button btn2;
  private Button btn3;
  protected WifiManager wifiManager;
  private List<ScanResult> results;
  private final String SSID1 = "Pixel_7964";
  private final String PWD1 = "Lb8906301";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // https://developer.aliyun.com/article/12841
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btn1 = (Button) findViewById(R.id.btn1);

    btn2 = (Button) findViewById(R.id.btn2);

    btn3 = (Button) findViewById(R.id.btn3);

    wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

    btn1.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ssid = wifiInfo.getSSID();
        System.out.println("wifiInfo = " + wifiInfo);
        System.out.println("SSID = " + ssid);

        Toast.makeText(getApplicationContext(), "btn1:\nssid = " + ssid, Toast.LENGTH_LONG).show();
      }
    });

    btn2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = "\"" + SSID1 + "\"";
        config.preSharedKey = "\""+ PWD1 +"\"";

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();

        if (list != null && list.size() > 0) {

        }

        wifiManager.addNetwork(config);
        for (int i = 0; i < list.size(); i++) {
          WifiConfiguration current = list.get(i);
          System.out.println("i = " + i + "\t\tcurrent = " + current.SSID);
          if (current.SSID != null && current.SSID.equals("\"" + SSID1 + "\"")) {
            wifiManager.disconnect();
            System.out.println("-------disconnecting----------");
            wifiManager.enableNetwork(current.networkId, true);
            wifiManager.reconnect();
            System.out.println("-------re-connecting!!!!----------");

            break;
          }
        }
      }
    });

    btn3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();


        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        System.out.println("wifiInfo = " + wifiInfo);

        if (info != null && info.isConnected()) {
          String ssid = info.getExtraInfo();
          // NetworkCapabilities nc = cm.getNetworkInfo();

          System.out.println("ssid from btn3 = " + ssid);
        } else {
          System.out.println("ssid from btn3 is null");
        }
      }
    });
  }
}