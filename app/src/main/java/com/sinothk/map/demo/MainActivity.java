package com.sinothk.map.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sinothk.map.OMapCallback;
import com.sinothk.map.OMapEntity;
import com.sinothk.map.OMapUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OMapUtil.getLocationInfo(this, OMapUtil.BAI_DU,new OMapCallback(){

            @Override
            public void onComplete(OMapEntity mapEntity) {
                Log.e("logMsg", "ERR = " + mapEntity.getLocCountry());
                Log.e("logMsg", "ERR = " + mapEntity.getLocProvince());
                Log.e("logMsg", "ERR = " + mapEntity.getLocCity());
                Log.e("logMsg", "ERR = " + mapEntity.getLocDistrict());
                Log.e("logMsg", "ERR = " + mapEntity.getLocStreet());
                Log.e("logMsg", "ERR = " + mapEntity.getLocAddr());
            }
        });

//        OMapUtil.getLocationPoiList(this, OMapUtil.BAI_DU, new OMapCallback() {
//
//            @Override
//            public void onComplete(OMapEntity mapEntity) {
//                for (OMapPoiEntity o : mapEntity.getPoiList()) {
//                    Log.e("logMsg", "ERR = " + o.getPoiName());
//                }
//            }
//        });
    }

    @Override
    protected void onStop() {
        OMapUtil.stop();
        super.onStop();
    }

}
