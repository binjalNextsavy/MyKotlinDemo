package com.example.myapplication;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.firebase.firestore.DocumentSnapshot;

import java.security.Permission;

public class Demoads extends AppCompatActivity {


    //Fro test ads
/*    val adRequest = AdRequest.Builder()
            //Binjal
            // .addTestDevice("83A56F210E6FA22DF2A237D408173533")  //Test For Ads
            .build()

    mInterstitialAd!!.loadAd(adRequest)*/

    //com.nextsavy.startopreneur
// E6:4D:D8:82:2B:03:B2:F2:91:5E:60:A2:EE:14:F9:7C:FB:78:60:C3

    static final int REQUEST_CODE = 121;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
            }
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        REQUEST_CODE);
            }
        }



       /* private void pairDevice(BluetoothDevice device) {
            try {
                Log.d("pairDevice()", "Start Pairing...");
                Method m = device.getClass().getMethod("createBond", (Class[]) null);
                m.invoke(device, (Object[]) null);
                Log.d("pairDevice()", "Pairing finished.");
            } catch (Exception e) {
                Log.e("pairDevice()", e.getMessage());
            }
        }


//For UnPairing
        private void unpairDevice(BluetoothDevice device) {
            try {
                Log.d("unpairDevice()", "Start Un-Pairing...");
                Method m = device.getClass().getMethod("removeBond", (Class[]) null);
                m.invoke(device, (Object[]) null);
                Log.d("unpairDevice()", "Un-Pairing finished.");
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }*/


    }



}


/*class MainActivity : AppCompatActivity() {

        lateinit var out: TextView
        lateinit var btnOn: Button
        lateinit var btnDiscover: Button
        lateinit var btnOff: Button
        lateinit var btnVisible: Button
        lateinit var btnScan: Button
        lateinit var lv: RecyclerView
        lateinit var pairedDevices: Set<BluetoothDevice>

    lateinit var mBluetoothAdapter : BluetoothAdapter
            lateinit var mReceiver : BroadcastReceiver
            lateinit var mAdapter: AdapterForBt

            val list = ArrayList<BluetoothDevice>()

        companion object {
        const val REQUEST_ENABLE_BT = 0
        const val REQUEST_DISCOVERABLE_BT = 0
        }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        out = findViewById(R.id.tvOut)
        btnOn = findViewById(R.id.btnOn)
        btnDiscover = findViewById(R.id.btnDiscover)
        btnOff = findViewById(R.id.btnOff)
        btnVisible = findViewById(R.id.btnVisible)
        btnScan = findViewById(R.id.btnScan)
        lv = findViewById(R.id.lvPairedDevice)

        mAdapter = AdapterForBt(this,list)

        lv.layoutManager = LinearLayoutManager(this)




        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        if (mBluetoothAdapter == null) {
        out.text = "Device not suppoerted"
        }


        btnOn.setOnClickListener {
        if (!mBluetoothAdapter.isEnabled) {
        val enableBT = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(enableBT, REQUEST_ENABLE_BT)
        }
        }

        btnDiscover.setOnClickListener {
        if (mBluetoothAdapter.isDiscovering) {
        mBluetoothAdapter.cancelDiscovery()
        Toast.makeText(this, "Discovery is stopped", Toast.LENGTH_LONG).show()

        } else {
        Toast.makeText(this, "Making your Device Discoverable", Toast.LENGTH_LONG).show()

        val enableDiscover = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        startActivityForResult(enableDiscover, REQUEST_DISCOVERABLE_BT)
        }
        }

        btnOff.setOnClickListener {
        mBluetoothAdapter.disable()

        Toast.makeText(this, "Turning Off Bluetooth", Toast.LENGTH_LONG).show()
        }

        btnScan.setOnClickListener {
        search()
        }

        btnVisible.setOnClickListener {

        pairedDevices = mBluetoothAdapter.bondedDevices

        if (pairedDevices.isNotEmpty()){
        // for (mBluetoothDevice in pairedDevices) list.add(mBluetoothDevice.name +"\n" + mBluetoothDevice.address)
        for (mBluetoothDevice in pairedDevices) list.add(mBluetoothDevice)

        Toast.makeText(this, "Showing Paired Devices", Toast.LENGTH_LONG).show()

        //val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)

        //val adapter = AdapterForBt(this,list)

        // lv.adapter = adapter
        } else {
        Toast.makeText(this, "Not Paired Devices", Toast.LENGTH_LONG).show()
        }

        }


        }

private fun search() {
        mBluetoothAdapter.startDiscovery()
        mReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
        var action = intent.action

        if (BluetoothDevice.ACTION_FOUND.equals(action)){
        var device: BluetoothDevice = intent!!.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!

        mAdapter.


        }
        }

        }
        }

class AdapterForBt(var mContext: Context?, val btList: ArrayList<BluetoothDevice>) : RecyclerView.Adapter<AdapterForBt.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterForBt.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_raw,parent,false)
        return ViewHolder(v)
        }

        override fun getItemCount(): Int {
        return btList.size
        }

        override fun onBindViewHolder(holder: AdapterForBt.ViewHolder, position: Int) {
        var data = btList[position]

        holder.textView.setText(data.name)

        holder.btn.setOnClickListener {
        //pairDevice(data)
        if (holder.btn.text == "Pair"){
        Log.e("NST", "Pair Called")
        pairDevice(data)
        holder.btn.setText("UnPair")


        } else {
        Log.e("NST", "UnPair Called")
        unPairDevice(data)
        holder.btn.setText("Pair")
        }
        }
        }


private fun pairDevice(device: BluetoothDevice) {

        try {

        Log.e("NST", "Start Pairing...")
        val m: Method = device.javaClass.getMethod("createBond")
        m.invoke(device)
        Log.e("NST", "Pair Finish")

        } catch (e: Exception) {
        Log.e("NST", e.message)
        }
        }



private fun unPairDevice(device: BluetoothDevice) {
        try {
        val m: Method = device.javaClass.getMethod("removeBond")
        m.invoke(device)
        Log.e("NST", "Pair Removed")

        } catch (e: Exception) {
        Log.e("NST", e.message)
        }
        }
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView :TextView = itemView.findViewById(R.id.btDevice)
        var btn : Button = itemView.findViewById(R.id.btnPair)

        }

        }
        }*/


