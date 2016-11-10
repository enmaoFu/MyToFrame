package com.yy.my.to.fragments.appointment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.yy.my.to.R;
import com.yy.my.to.adapters.appointment.AppointmentServiceMapPublicAdapter;
import com.yy.my.to.base.BaseFragment;
import com.yy.my.to.entitys.appointment.AppointmentServiceMapPublicEntity;
import com.yy.my.to.utils.Preference;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约定位地点选择学校Fragment
 * @author enmaoFu
 * @data 2016年11月7日
 */
public class AppointmentServiceMapSchoolFragment extends BaseFragment implements View.OnClickListener,PoiSearch.OnPoiSearchListener{

    private TextView dq_titlel;//当前位置
    private TextView dq_address;//当前位置详细信息

    private ListView list;//定位位置ListView
    private AppointmentServiceMapPublicAdapter appointmentServiceMapPublicAdapter;//定位位置适配器
    private List<AppointmentServiceMapPublicEntity> appointmentServiceMapPublicEntityList = new ArrayList<>();//定位位置数据源

    private String deepType = "";// poi搜索类型
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    private PoiOverlay poiOverlay;// poi图层
    private List<PoiItem> poiItems;// poi数据
    private String poiSearchType = "";//搜索类型
    private String poiSearchRegion = "";//搜索区域
    private double lat = 39.9088691069;//经度
    private double lon = 116.3973823161;//纬度 默认为天安门39.9088691069,116.3973823161

    private int RESUIT_POI_SEARCH_POIITEM = 25;//每页返回poiitem条数
    private int RESUIT_POI_SEARCH_NUM_PAGE = 1;//查询页码

    private Preference pf;//SP文件数据缓存

    /**
     * 初始化视图
     * @return
     */
    @Override
    protected int bindViews() {
        return R.layout.fragment_appointment_service_map_school;
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        dq_titlel = (TextView)findViewById(R.id.dq_title);
        dq_address = (TextView)findViewById(R.id.dq_address);
        list = (ListView)findViewById(R.id.list);
        appointmentServiceMapPublicAdapter = new AppointmentServiceMapPublicAdapter(getActivity());
        list.setAdapter(appointmentServiceMapPublicAdapter);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        pf = new Preference(getActivity());
        setCurrentLocation();
        initSearch("学校");
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {

    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 设置当前位置信息
     */
    public void setCurrentLocation(){
        String poiName = pf.getString("poiName");
        if(!poiName.equals("")){
            dq_titlel.setText(poiName);
        }else{
            dq_titlel.setText("");
        }
        String address = pf.getString("address");
        if(!address.equals("")){
            dq_address.setText(address);
        }else{
            dq_address.setText("");
        }
    }

    /**
     * 初始化POI搜索
     */
    public void initSearch(String keyWord){

        //设置搜索条件
        Log.v("print","赋值的区域"+poiSearchRegion + "赋值的经度" + lat + "赋值的维度" + lon);
        String city = pf.getString("city");
        if(!city.equals("")){
            query = new PoiSearch.Query(keyWord, poiSearchType,city);
        }else{
            query = new PoiSearch.Query(keyWord, poiSearchType);
        }
        query.setPageSize(RESUIT_POI_SEARCH_POIITEM);
        query.setPageNum(RESUIT_POI_SEARCH_NUM_PAGE);

        ///构造 PoiSearch 对象，并设置监听
        poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setOnPoiSearchListener(this);

        //获取第一次进入缓存的经纬度
        String latString = pf.getString("lat");
        String lonString = pf.getString("lon");
        if(!latString.equals("") && !lonString.equals("")){
            //强制转换为double
            lat = Double.parseDouble(pf.getString("lat"));
            lon = Double.parseDouble(pf.getString("lon"));
            // 设置搜索区域为以lp点为圆心，其周围2000米范围
            LatLonPoint lp = new LatLonPoint(lat, lon);
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 2000, true));
        }else{
            // 设置搜索区域为以lp点为圆心，其周围2000米范围
            LatLonPoint lp = new LatLonPoint(lat, lon);
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 2000, true));
        }

        //调用 PoiSearch 的 searchPOIAsyn() 方法发送请求
        poiSearch.searchPOIAsyn();

    }

    @Override
    public void onPoiSearched(PoiResult result, int code) {

        if(code == 1000){//成功
            if(result != null && result.getQuery() != null){//不为空 搜索poi的结果
                if(result.getQuery().equals(query)){//是否为同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    AppointmentServiceMapPublicEntity appointmentServiceMapPublicEntity = null;
                    appointmentServiceMapPublicEntityList.clear();
                    for(PoiItem item:poiItems){
                        Log.v("print","poi检索的信息："+item.getBusinessArea() + "---" + item.getAdName() + "---" + item.getCityName() + "---" +
                                item.getProvinceName() + "---" +item.getTypeDes() + "---" + item.getTel() + "---" + item.getAdCode() + "---" + item.getPoiId()
                                + "---" + item.getDistance() + "---" + item.getTitle() + "---" + item.getSnippet() + "---" + item.getWebsite() + "---" + item.getPostcode() +
                                "---" + item.getEmail() + "---" + item.getDirection() + "---" + item.getProvinceCode() + "---" + item.getParkingType());
                        appointmentServiceMapPublicEntity = new AppointmentServiceMapPublicEntity(item.getTitle(),item.getSnippet());

                        appointmentServiceMapPublicEntityList.add(appointmentServiceMapPublicEntity);
                    }

                    appointmentServiceMapPublicAdapter.setItems(appointmentServiceMapPublicEntityList);

                }
            }
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    /**
     * 初始化定位位置列表
     */
    /*public void initListView(){

        AppointmentServiceMapPublicEntity appointmentServiceMapPublicEntity = null;

        for(int i = 0; i < 20; i++){

            appointmentServiceMapPublicEntity = new AppointmentServiceMapPublicEntity("D+M浪尖大厦","重庆市沙坪坝区大学城南路1号");

            appointmentServiceMapPublicEntityList.add(appointmentServiceMapPublicEntity);

        }

        appointmentServiceMapPublicAdapter.setItems(appointmentServiceMapPublicEntityList);

    }*/

}
