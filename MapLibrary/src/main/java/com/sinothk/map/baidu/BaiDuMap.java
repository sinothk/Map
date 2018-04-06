package com.sinothk.map.baidu;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.sinothk.map.OMapCallback;
import com.sinothk.map.OMapEntity;

/**
 * <pre>
 *  创建:  梁玉涛 2018/4/7/007 on 0:06
 *  项目: MapLib
 *  描述:
 *  更新:
 * <pre>
 */
public class BaiDuMap {
    private static BaiDuMap instance = null;
    public static BaiDuMap getInstance() {
        if (instance == null) {
            instance = new BaiDuMap();
        }
        return instance;
    }

    private static LocationClient mLocationClient = null;

    public void stop() {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop(); //停止定位服务
            mLocationClient = null;
        }
    }

    /**
     * 基础初始化
     *
     * @param option
     */
    private static void initOption(LocationClientOption option) {
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；
        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标
        option.setScanSpan(0);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效
        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true
        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false
        option.setWifiCacheTimeOut(5 * 60 * 1000);

        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
        option.setEnableSimulateGps(false);
    }

    public static LocationClientOption getBaseOption() {

        LocationClientOption option = new LocationClientOption();

        initOption(option);// 获得经纬

        option.setIsNeedAddress(true);//可选，是否需要地址信息，默认为不需要，即参数为false。如果开发者需要获得当前点的地址信息，此处必须为true
        option.setIsNeedLocationDescribe(true); //可选，是否需要位置描述信息，默认为不需要，即参数为false，如果开发者需要获得当前点的位置信息，此处必须为true

//        option.setIsNeedLocationPoiList(true); //可选，是否需要周边POI信息，默认为不需要，即参数为false。如果开发者需要获得周边POI信息，此处必须为true

        return option;
    }

    public LocationClientOption getPoiOption() {
        LocationClientOption option = new LocationClientOption();

        initOption(option);// 获得经纬

        option.setIsNeedLocationPoiList(true); //可选，是否需要周边POI信息，默认为不需要，即参数为false。如果开发者需要获得周边POI信息，此处必须为true

        return option;
    }

    public void getLocationInfo(Context mContext, final OMapCallback oMapCallback) {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(mContext);
        }

        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location == null) {
                    return;
                }
                Log.e("logMsg", "ERR = " + location.getLocType());
                oMapCallback.onComplete(OMapEntity.getBaiDuInfo(location));
//                if (location.getLocType() != BDLocation.TypeServerError) {
//                }else{
//                }
            }
        });
        mLocationClient.setLocOption(getBaseOption());

        mLocationClient.start();// 定位SDK
    }

    public void getLocationPoiList(Context mContext, final OMapCallback oMapCallback) {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(mContext);
        }

        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location == null) {
                    return;
                }
                Log.e("logMsg", "ERR = " + location.getLocType());

                if (location.getLocType() != BDLocation.TypeServerError) {
                    oMapCallback.onComplete(OMapEntity.getBaiDuPoiInfo(location.getPoiList()));
                }
            }
        });
        mLocationClient.setLocOption(getPoiOption());

        mLocationClient.start();// 定位SDK
    }
}
