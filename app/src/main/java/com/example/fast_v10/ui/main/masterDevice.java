package com.example.fast_v10.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fast_v10.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link masterDevice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class masterDevice extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public masterDevice() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment masterDevice.
     */
    // TODO: Rename and change types and number of parameters
    public static masterDevice newInstance(String param1, String param2) {
        masterDevice fragment = new masterDevice();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    int i = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fr

        View v = inflater.inflate(R.layout.fragment_master_device, container, false);
        final TextView output = (TextView) v.findViewById(R.id.textViewData);
        final TextView temp = (TextView) v.findViewById(R.id.tempTest0);
        final TextView hum = (TextView) v.findViewById(R.id.humidity0);
        final TextView pres = (TextView) v.findViewById(R.id.pressure0);
        final TextView lux = (TextView) v.findViewById(R.id.lux0);
        final TextView soilTemp = (TextView) v.findViewById(R.id.soilTemp0);
        final TextView capread = (TextView) v.findViewById(R.id.capacity0);
        final TextView UV = (TextView) v.findViewById(R.id.UV0);
        final TextView wind = (TextView) v.findViewById(R.id.windSpeed0);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String bluetooth = null;
                bluetooth = DataHolder.getData0();
                output.setText("Collecting Data...");

                if(bluetooth != ""){
                    String[] dataList = bluetooth.split(" ", 12);
                    temp.setText("Temp: " + dataList[1] + "\u00B0" + "C");
                    hum.setText("Humidity: " + dataList[2] + "%");
                    pres.setText("Pressure: " + dataList[3] + "pHa");
                    lux.setText("Lux 0: " + dataList[4]);
                    soilTemp.setText("soil Temp: " + dataList[5] + "\u00B0" + "C");
                    capread.setText("capacitive: " + dataList[6]);
                    String UVoutput = UVIndex(dataList[7]);
                    UV.setText("Ultra Violet (UV) Index: " + UVoutput);
                    wind.setText("Wind Speed: " + dataList[8] + " Mps");
                }
                else{

                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);  //the time is in miliseconds


        return v;
    }

    String UVIndex(String UV){
        String UVIndex = UV;
        int UVint = Integer.parseInt(UV);
        if(UVint < 10){
            UVIndex = "0";
        }
        else if(UVint < 46){
            UVIndex = "1";
        }
        else if(UVint < 65){
            UVIndex = "2";
        }
        else if(UVint < 83){
            UVIndex = "3";
        }
        else if(UVint < 103){
            UVIndex = "4";
        }
        else if(UVint < 124){
            UVIndex = "5";
        }
        else if(UVint < 142){
            UVIndex = "6";
        }
        else if(UVint < 162){
            UVIndex = "7";
        }
        else if(UVint < 180){
            UVIndex = "8";
        }
        else if(UVint < 200){
            UVIndex = "9";
        }
        else if(UVint < 221){
            UVIndex = "10";
        }
        else if(UVint >= 221){
            UVIndex = "11";
        }
        return UVIndex;
    }
}