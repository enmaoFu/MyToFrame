package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.yy.my.to.R;
import com.yy.my.to.base.BaseFragmentActivity;
import com.yy.my.to.fragments.appointment.AppointmentServiceMapOfficeBuildingFragment;
import com.yy.my.to.fragments.appointment.AppointmentServiceMapQuartersFragment;
import com.yy.my.to.fragments.appointment.AppointmentServiceMapSchoolFragment;
import com.yy.my.to.fragments.appointment.AppointmentServiceMapWholeFragment;
import com.yy.my.to.utils.ImmersedStatusbarUtils;
import com.yy.my.to.utils.Preference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 预约定位通用页面
 * @author enmaoFu
 * @data 2016年11月2日
 */
public class AppointmenServiceMapActivity extends BaseFragmentActivity implements AMapLocationListener,LocationSource,View.OnClickListener {

    private MapView mMapView;//地图
    private AMap aMap;//定义一个地图对象
    private float zoomlevel = 17f;//地图放大级别
    private double lat = 39.9088691069;//经度
    private double lon = 116.3973823161;//纬度 默认为天安门39.9088691069,116.3973823161
    private UiSettings mUiSettings;//定义一个UiSettings对象
    public AMapLocationClient mLocationClient = null;//声明AMapLocationClient类对象
    public AMapLocationClientOption mLocationOption = null;//声明AMapLocationClientOption对象
    private String deepType = "";// poi搜索类型
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    private PoiOverlay poiOverlay;// poi图层
    private List<PoiItem> poiItems;// poi数据
    private String poiSearchType = "";//搜索类型
    private String poiSearchRegion = "";//搜索区域

    private int RESUIT_POI_SEARCH_POIITEM = 20;//每页返回poiitem条数
    private int RESUIT_POI_SEARCH_NUM_PAGE = 1;//查询页码

    private ImageView left_img;//标题栏返回
    private ImageView right_img;//标题栏搜索
    private EditText center_edx;//标题栏搜索框
    private Intent intent = new Intent();

    private RelativeLayout whole;//地图地点菜单的布局 全部
    private RelativeLayout office_building;//地图地点菜单的布局 写字楼
    private RelativeLayout quarters;//地图地点菜单的布局 小区
    private RelativeLayout school;//地图地点菜单的布局 学校

    private TextView whole_text;//地图地点菜单的字 全部
    private TextView office_building_text;//地图地点菜单的字 写字楼
    private TextView quarters_text;//地图地点菜单的字 小区
    private TextView school_text;//地图地点菜单的字 学校

    private int notClickTextColor;//底部菜单字体颜色 未点击
    private int alreadyClickTextColor;//底部菜单字体颜色 已点击

    private FrameLayout content_frame;
    private AppointmentServiceMapWholeFragment asmwFragment;//所有fragemnt
    private AppointmentServiceMapOfficeBuildingFragment asmobFragment;//写字楼fragment
    private AppointmentServiceMapQuartersFragment asmqFragment;//小区fragment
    private AppointmentServiceMapSchoolFragment asmsFragment;//学校fragment
    private FragmentManager fm;

    private Preference pf;//SP文件数据缓存

    /*private String poiName;//当前位置
    private String address;//当前位置详细信息
    private Bundle bundle = new Bundle();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
    }

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_map);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        mMapView = (MapView)findViewById(R.id.map);//初始化地图组件
        left_img = (ImageView)findViewById(R.id.left_img);
        right_img = (ImageView)findViewById(R.id.right_img);
        center_edx = (EditText)findViewById(R.id.center_edx);
        whole = (RelativeLayout)findViewById(R.id.whole);
        office_building = (RelativeLayout)findViewById(R.id.office_building);
        quarters = (RelativeLayout)findViewById(R.id.quarters);
        school = (RelativeLayout)findViewById(R.id.school);
        whole_text = (TextView)findViewById(R.id.whole_text);
        office_building_text = (TextView)findViewById(R.id.office_building_text);
        quarters_text = (TextView)findViewById(R.id.quarters_text);
        school_text = (TextView)findViewById(R.id.school_text);
        content_frame = (FrameLayout)findViewById(R.id.content_frame);
        fm = getSupportFragmentManager();
        left_img.setOnClickListener(this);
        whole.setOnClickListener(this);
        office_building.setOnClickListener(this);
        quarters.setOnClickListener(this);
        school.setOnClickListener(this);
        right_img.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        showDialog();
        pf = new Preference(AppointmenServiceMapActivity.this);
        notClickTextColor = Color.parseColor("#777777");
        alreadyClickTextColor = Color.parseColor("#3391E8");
        initMap();
        initLocation();
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        View topView = findViewById(R.id.lin);
        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        left_img.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        center_edx.setVisibility(View.VISIBLE);
        left_img.setImageResource(R.mipmap.ic_back);
        right_img.setImageResource(R.mipmap.ic_appointment_doctor_search);
    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_img:
                backView();
                break;
            case R.id.whole:
                selectTab(0);
                break;

            case R.id.office_building:
                selectTab(1);
                break;

            case R.id.quarters:
                selectTab(2);
                break;

            case R.id.school:
                selectTab(3);
                break;

            //测试缓存是否清除，暂时留用
            case R.id.right_img:
                deBUGData();
                break;
        }
    }

    /**
     * 地图与定位
     */
    /*******************************************************************************************************************************/
    /**
     * 初始化地图变量
     */
    public void initMap(){
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 设置卫星地图模式
            mUiSettings = aMap.getUiSettings();//实例化UiSettings类
            mUiSettings.setZoomControlsEnabled(true);//设置显示缩放控件
            mUiSettings.setMyLocationButtonEnabled(true); //是否显示定位按钮
            mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);//设置logo位置，左下，底部居中，右下
            mUiSettings.setScaleControlsEnabled(true); //设置显示地图的默认比例尺
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
            aMap.setLocationSource(this);// 设置定位监听
        }
    }

    /**
     * 初始化定位变量
     */
    public void initLocation(){
        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        // mLocationOption.setInterval(2000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    /**
     * 初始化POI搜索
     */
    /*public void initSearch(String keyWord){

        aMap.setOnMapClickListener(null);// 进行poi搜索时清除掉地图点击事件

        //设置搜索条件
        Log.v("print","赋值的区域"+poiSearchRegion + "赋值的经度" + lat + "赋值的维度" + lon);
        query = new PoiSearch.Query(keyWord, poiSearchType, poiSearchRegion);
        query.setPageSize(RESUIT_POI_SEARCH_POIITEM);
        query.setPageNum(RESUIT_POI_SEARCH_NUM_PAGE);

        ///构造 PoiSearch 对象，并设置监听
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);

        // 设置搜索区域为以lp点为圆心，其周围2000米范围
        LatLonPoint lp = new LatLonPoint(lat, lon);
        poiSearch.setBound(new PoiSearch.SearchBound(lp, 2000, true));

        //调用 PoiSearch 的 searchPOIAsyn() 方法发送请求
        poiSearch.searchPOIAsyn();

    }*/

    private void startLocation(double latNew,double lonNew){

        if(mLocationClient != null){
            //19f代表地图放大的级别
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latNew,lonNew),zoomlevel));
        }

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                lat = aMapLocation.getLatitude();//获取纬度
                lon = aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                poiSearchRegion = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                aMapLocation.getAoiName();//获取当前定位点的AOI信息
                aMapLocation.getPoiName();
                aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                //获取定位时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);
                Log.v("print","定位来源："+aMapLocation.getLocationType());
                Log.v("print","维度："+aMapLocation.getLatitude());
                Log.v("print","经度："+aMapLocation.getLongitude());
                Log.v("print","精确信息："+aMapLocation.getAccuracy());
                Log.v("print","地址信息："+aMapLocation.getAddress());
                Log.v("print","国家信息："+aMapLocation.getCountry());
                Log.v("print","省信息："+aMapLocation.getProvince());
                Log.v("print","城市信息："+aMapLocation.getCity());
                Log.v("print","城区信息："+aMapLocation.getDistrict());
                Log.v("print","街道信息："+aMapLocation.getStreet());
                Log.v("print","街道门牌号信息："+aMapLocation.getStreetNum());
                Log.v("print","城市编码："+aMapLocation.getCityCode());
                Log.v("print","地区编码："+aMapLocation.getAdCode());
                Log.v("print","当前定位AOI信息："+aMapLocation.getAoiName());
                Log.v("print","当前GPS状态："+aMapLocation.getGpsAccuracyStatus());
                Log.v("print","定位时间："+df.format(date));
                startLocation(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                LatLng latLng = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                final Marker marker = aMap.addMarker(new MarkerOptions().
                        position(latLng).
                        title("北京").
                        snippet("DefaultMarker"));
               /* poiName = aMapLocation.getPoiName();
                address = aMapLocation.getAddress();*/
                //initSearch("");
                /*dq_title.setText(aMapLocation.getPoiName());
                dq_address.setText(aMapLocation.getAddress());*/
                /**
                 * 把当前位置以及POI搜索需要的数据缓存进SP文件
                 */
                pf.setString("poiName",aMapLocation.getPoiName());//当前位置
                pf.setString("address",aMapLocation.getAddress());//当前位置详细信息
                pf.setString("lat",aMapLocation.getLongitude()+"");//当前位置经度
                pf.setString("lon",aMapLocation.getLatitude()+"");//当前位置维度
                pf.setString("city",aMapLocation.getCity());//当前城市
                selectTab(0);
                hideDialog();
            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.v("error","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    /**
     * 点击默认定位按钮 激活
     * @param onLocationChangedListener
     */
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mLocationClient.startLocation();
    }

    /**
     * 点击默认定位按钮 停止
     */
    @Override
    public void deactivate() {
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    /*@Override
    public void onPoiSearched(PoiResult result, int code) {

        if(code == 1000){//成功
            if(result != null && result.getQuery() != null){//不为空 搜索poi的结果
                if(result.getQuery().equals(query)){//是否为同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    for(PoiItem item:poiItems){
                        Log.v("print","poi检索的信息："+item.getBusinessArea() + "---" + item.getAdName() + "---" + item.getCityName() + "---" +
                                item.getProvinceName() + "---" +item.getTypeDes() + "---" + item.getTel() + "---" + item.getAdCode() + "---" + item.getPoiId()
                                + "---" + item.getDistance() + "---" + item.getTitle() + "---" + item.getSnippet() + "---" + item.getWebsite() + "---" + item.getPostcode() +
                                "---" + item.getEmail() + "---" + item.getDirection() + "---" + item.getProvinceCode() + "---" + item.getParkingType());
                    }
                }
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }*/

    /*******************************************************************************************************************************/
    /**
     * Fragment菜单切换
     * @param postion 选择了第几个fragement
     */
    public void selectTab(int postion){

        FragmentTransaction ft = fm.beginTransaction();

        switch (postion) {
            case 0:
                if(asmwFragment == null){
                    asmwFragment = new AppointmentServiceMapWholeFragment();
                    //Activity传递数据到指定的Fragment
                    /*asmwFragment.setArguments(bundle);
                    bundle.putString("poiName", poiName);
                    bundle.putString("address",address);*/
                    ft.replace(R.id.content_frame, asmwFragment);
                }else{
                    ft.replace(R.id.content_frame, asmwFragment);
                }

                whole_text.setTextColor(alreadyClickTextColor);
                office_building_text.setTextColor(notClickTextColor);
                quarters_text.setTextColor(notClickTextColor);
                school_text.setTextColor(notClickTextColor);

                break;

            case 1:
                if(asmobFragment == null){
                    asmobFragment = new AppointmentServiceMapOfficeBuildingFragment();
                    ft.replace(R.id.content_frame, asmobFragment);
                }else{
                    ft.replace(R.id.content_frame, asmobFragment);
                }

                whole_text.setTextColor(notClickTextColor);
                office_building_text.setTextColor(alreadyClickTextColor);
                quarters_text.setTextColor(notClickTextColor);
                school_text.setTextColor(notClickTextColor);

                break;

            case 2:

                if(asmqFragment == null){
                    asmqFragment = new AppointmentServiceMapQuartersFragment();
                    ft.replace(R.id.content_frame, asmqFragment);
                }else{
                    ft.replace(R.id.content_frame, asmqFragment);
                }

                whole_text.setTextColor(notClickTextColor);
                office_building_text.setTextColor(notClickTextColor);
                quarters_text.setTextColor(alreadyClickTextColor);
                school_text.setTextColor(notClickTextColor);

                break;

            case 3:

                if(asmsFragment == null){
                    asmsFragment = new AppointmentServiceMapSchoolFragment();
                    ft.replace(R.id.content_frame, asmsFragment);
                }else{
                    ft.replace(R.id.content_frame, asmsFragment);
                }

                whole_text.setTextColor(notClickTextColor);
                office_building_text.setTextColor(notClickTextColor);
                quarters_text.setTextColor(notClickTextColor);
                school_text.setTextColor(alreadyClickTextColor);

                break;
        }

        ft.commit();

    }

    /**
     * 测试缓存是否清楚，暂时留用
     */
    public void deBUGData(){
        String poiName = pf.getString("poiName");
        String address = pf.getString("address");
        String lat = pf.getString("lat");
        String lon = pf.getString("lon");
        String city = pf.getString("city");
        Log.v("print","测试缓存是否清除"+poiName+address+lat+lon+city);
    }

    /**
     * 地图定位与生命周期
     */
    /*******************************************************************************************************************************/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        pf.removeData("poiName");
        pf.removeData("address");
        pf.removeData("lat");
        pf.removeData("lon");
        pf.removeData("city");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    /**
     * Activity被系统杀死时调用
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }

}
