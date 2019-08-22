package com.example.byrfb.bluetoothtest.rfb;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.example.byrfb.bluetoothtest.ConnectAndSend;
import com.example.byrfb.bluetoothtest.R;

import java.util.Set;

public class AnaSayfa extends AppCompatActivity {

    static BluetoothAdapter myBluetoothAdapter;
    ConnectAndSend cas;
    BroadcastReceiver bulunanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                varolanlariListele();
            }


        }
    };
    private int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);
        //initBluetooth();
        cas = new ConnectAndSend();

    }

    private void initBluetooth() {


        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (myBluetoothAdapter == null) {

        } else {
            if (!myBluetoothAdapter.isEnabled()) {
                Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT);
            } else {
                // bluetooth açık
                bulunabilirlikAyarla();
                varolanlariListele();
                bulunanCihaziAl();
                serverBaslat();


            }
        }
    }

    private void serverBaslat() {
        BtServer server = new BtServer();
        server.start();

    }

    private void bulunabilirlikAyarla() {
        Intent bulunabilirlikIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        bulunabilirlikIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(bulunabilirlikIntent);

    }

    private void bulunanCihaziAl() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(bulunanReceiver, filter);

    }

    private void varolanlariListele() {
        Set<BluetoothDevice> devices = myBluetoothAdapter.getBondedDevices();

        for (BluetoothDevice device : devices) {
            System.out.println(device.getName());
            BtClient client = new BtClient(device);
            client.start();
        }
    }

    @Override
    protected void onActivityResult(int reqestCode, int resultCode, Intent data) {

        if (reqestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                //bluetootah açık
                varolanlariListele();
            } else if (resultCode == RESULT_CANCELED) {
                // bluetooth kapalı

            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            cas.th2.write(1);


            System.out.println("uste basiliyor");
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            cas.th2.write(2);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
