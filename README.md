# MapUtil
一个集成高德（百度）常用功能的依赖库
# 1 配置权限：
    <!-- 这个权限用于进行网络定位-->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <!-- 这个权限用于访问GPS定位-->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <!-- 用于访问wifi网络信息，wifi信息会用于进行网络定位-->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <!-- 获取运营商信息，用于支持提供运营商信息相关的接口-->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <!-- 这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
    <!-- 用于读取手机当前的状态-->
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <!-- 写入扩展存储，向扩展卡写入数据，用于写入离线定位数据-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <!-- 访问网络，网络定位需要上网-->
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- SD卡读取权限，用户写入离线定位数据-->
    
# 2 填写Key:
    <!-- 声明service组件 -->
    <service
        android:name="com.baidu.location.f"
        android:enabled="true"
        android:process=":remote" >
    </service>

    <!-- AK鉴权 -->
    <!-- meta-data需要写在application中 -->
    <meta-data
        android:name="com.baidu.lbsapi.API_KEY"
        android:value="N1wFB9KQnR3jdTKre0o9LTu29F0uVxrr" />  <!-- http://lbsyun.baidu.com/apiconsole/key -->
        
# 3 获取位置信息：
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
        
# 4 获得 POI:
    OMapUtil.getLocationPoiList(this, OMapUtil.BAI_DU, new OMapCallback() {
            @Override
            public void onComplete(OMapEntity mapEntity) {
                for (OMapPoiEntity o : mapEntity.getPoiList()) {
                    Log.e("logMsg", "ERR = " + o.getPoiName());
                }
            }
    });
