package com.example.fast_v10.ui.main;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fast_v10.MainActivity;
import com.example.fast_v10.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainMenu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    int counter;
    int j;
    String finalList;
    String[] dataList;

    final MainActivity newMainActivity = new MainActivity();

    // TextView output = (TextView) getView().findViewById(R.id.output);
    //Button blueToothOpened = (Button) getView().findViewById(R.id.bluetoothOpen);


    public mainMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mainMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static mainMenu newInstance(String param1, String param2) {
        mainMenu fragment = new mainMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  MainActivity newMain = new MainActivity();


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);
        final TextView output = (TextView) v.findViewById(R.id.output);
        Button blueToothOpened = (Button) v.findViewById(R.id.bluetoothOpen);
        Button blueToothClosed = (Button) v.findViewById(R.id.bluetoothClose);
        Button blueToothListen = (Button) v.findViewById(R.id.bluetoothListen);
       // ImageView image = (ImageView) v.findViewById(R.id.imageView2);


        blueToothOpened.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                newMainActivity.findBT();
                try {
                    newMainActivity.openBT();
                    output.setText("BlueTooth Opened");
                } catch (IOException e) {

                    e.printStackTrace();
                    output.setText("BlueTooth Not Opened");
                }
            }
        });

        blueToothClosed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    newMainActivity.closeBT();
                    output.setText("BlueTooth Closed");
                } catch (IOException e) {
                    e.printStackTrace();
                    output.setText("BlueTooth did not Close");
                }

            }
        });

        blueToothListen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                output.setText("Listening for Data..." + "\n" + "View Data on device screens");
                final Handler handler = new Handler();
                newMainActivity.beginListenForData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finalList = newMainActivity.finalList;
                        delimitDataList();
                        handler.postDelayed(this, 1000);
                    }
                }, 1000);  //the time is in miliseconds
            }
        });
        return v;
    }


    void delimitDataList() {
        dataList = finalList.split(" ", 12);
        String id0 = "0";
        String id1 = "1.00";
        String id2 = "2.00";
        String id3 = "3.00";
        if (dataList[0].equals(id0)) {
            DataHolder.setData0(finalList);
        } else if (dataList[0].equals(id1)) {
            DataHolder.setData1(finalList);
        } else if (dataList[0].equals(id2)) {
            DataHolder.setData2(finalList);
        } else if (dataList[0].equals(id3)) {
            DataHolder.setData3(finalList);
        }
    }
}
