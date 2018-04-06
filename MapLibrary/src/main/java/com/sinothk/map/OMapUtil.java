package com.sinothk.map;

import android.content.Context;

import com.sinothk.map.baidu.BaiDuMap;

/**
 * Created by 梁玉涛 on 2018/1/2.
 */

public class OMapUtil {

    public final static int BAI_DU = 0;
    public static int currLocateType = 0;

    public static void stop() {
        if (currLocateType == BAI_DU) {
            BaiDuMap.getInstance().stop();
        } else {

        }
    }

    public static void getLocationInfo(Context mContext, int locateType, OMapCallback oMapCallback) {
        currLocateType = locateType;

        if (currLocateType == BAI_DU) {
            BaiDuMap.getInstance().getLocationInfo(mContext, oMapCallback);
        } else {

        }
    }

    /**
     * 获得Poi
     *
     * @param mContext
     * @param locateType
     * @param oMapCallback
     */
    public static void getLocationPoiList(Context mContext, int locateType, OMapCallback oMapCallback) {
        currLocateType = locateType;

        if (currLocateType == BAI_DU) {
            BaiDuMap.getInstance().getLocationPoiList(mContext, oMapCallback);
        } else {

        }
    }
}
