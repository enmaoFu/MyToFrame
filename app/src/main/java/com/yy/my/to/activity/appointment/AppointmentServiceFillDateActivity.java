package com.yy.my.to.activity.appointment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.yy.my.to.R;
import com.yy.my.to.activity.currency.CurrencySelectMyFamilyActivity;
import com.yy.my.to.adapters.appointment.AppointmentServiceFillDateAdapter;
import com.yy.my.to.base.BaseActivity;
import com.yy.my.to.utils.ImmersedStatusbarUtils;
import com.yy.my.to.utils.Preference;
import com.yy.my.to.utils.SpecialCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.qqtheme.framework.picker.TimePicker;

/**
 * 预约上门服务填写资料页面
 * @author enmaoFu
 * @date 2016年10月14日
 */
public class AppointmentServiceFillDateActivity extends BaseActivity implements View.OnClickListener,GestureDetector.OnGestureListener {

    private TextView center_text;//标题栏标题

    private ImageView left_img;//标题栏返回

    private ViewFlipper flipper1 = null;
    // private ViewFlipper flipper2 = null;
    private static String TAG = "ZzL";
    private GridView gridView = null;
    private GestureDetector gestureDetector = null;
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private int week_c = 0;
    private int week_num = 0;
    private String currentDate = "";
    private static int jumpWeek = 0;
    private static int jumpMonth = 0;
    private static int jumpYear = 0;
    private AppointmentServiceFillDateAdapter dateAdapter;
    private int daysOfMonth = 0; // 某月的天数
    private int dayOfWeek = 0; // 具体某一天是星期几
    private int weeksOfMonth = 0;
    private SpecialCalendar sc = null;
    private boolean isLeapyear = false; // 是否为闰年
    private int selectPostion = 0;
    private String dayNumbers[] = new String[7];
    private int currentYear;
    private int currentMonth;
    private int currentWeek;
    private int currentDay;
    private int currentNum;
    private boolean isStart;// 是否是交接的月初

    private RelativeLayout choice_patient_re;//选择问诊人
    private Intent intent = new Intent();//视图
    private RelativeLayout appointment_service_fill_date_re;//症状
    private Button appointment_service_fill_date_next;//下一步
    private TextView appointment_service_date_start;//开始时间
    private TextView appointment_service_date_end;//结束时间
    private RelativeLayout appointment_service_fill_address;//上门地址
    private RelativeLayout appointment_service_choice_date;//时间段，测试缓存暂时留用

    /**
     * 初始化视图
     */
    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_appointment_service_fill_data);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        center_text = (TextView)findViewById(R.id.center_text);
        left_img = (ImageView)findViewById(R.id.left_img);
        flipper1 = (ViewFlipper) findViewById(R.id.flipper1);
        choice_patient_re = (RelativeLayout)findViewById(R.id.choice_patient_re);
        appointment_service_fill_date_re = (RelativeLayout)findViewById(R.id.appointment_service_fill_date_re);
        appointment_service_fill_date_next = (Button)findViewById(R.id.appointment_service_fill_date_next);
        appointment_service_date_start = (TextView)findViewById(R.id.appointment_service_date_start);
        appointment_service_date_end = (TextView)findViewById(R.id.appointment_service_date_end);
        appointment_service_fill_address = (RelativeLayout)findViewById(R.id.appointment_service_fill_address);
        appointment_service_choice_date = (RelativeLayout)findViewById(R.id.appointment_service_choice_date);//测试缓存暂时留用
        appointment_service_choice_date.setOnClickListener(this);//测试缓存暂时留用
        appointment_service_fill_date_re.setOnClickListener(this);
        choice_patient_re.setOnClickListener(this);
        left_img.setOnClickListener(this);
        appointment_service_fill_date_next.setOnClickListener(this);
        appointment_service_date_start.setOnClickListener(this);
        appointment_service_date_end.setOnClickListener(this);
        appointment_service_fill_address.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        gestureDetector = new GestureDetector(this);
        dateAdapter = new AppointmentServiceFillDateAdapter(this, getResources(), currentYear,
                currentMonth, currentWeek, currentNum, selectPostion,
                currentWeek == 1 ? true : false);
        addGridView();
        dayNumbers = dateAdapter.getDayNumbers();
        gridView.setAdapter(dateAdapter);
        selectPostion = dateAdapter.getTodayPosition();
        gridView.setSelection(selectPostion);
        flipper1.addView(gridView, 0);
    }

    /**
     * 初始化标题栏
     */
    @Override
    protected void initActionBar() {
        View topView = findViewById(R.id.lin);
        ImmersedStatusbarUtils.initAfterSetContentView(this, topView);
        center_text.setVisibility(View.VISIBLE);
        left_img.setVisibility(View.VISIBLE);
        center_text.setText("填写资料");
        left_img.setImageResource(R.mipmap.ic_back);
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
            case R.id.choice_patient_re:
                intent.setClass(AppointmentServiceFillDateActivity.this,CurrencySelectMyFamilyActivity.class);
                startActivity(intent);
                break;
            case R.id.appointment_service_fill_date_re:
                intent.setClass(AppointmentServiceFillDateActivity.this,AppointmentServiceSymptomLableActivity.class);
                startActivity(intent);
                break;
            case R.id.appointment_service_fill_date_next:
                intent.setClass(AppointmentServiceFillDateActivity.this, AppointmentServiceSelectDoctorActivity.class);
                startActivity(intent);
                break;
            case R.id.appointment_service_date_start:
                TimePicker pickerStart = new TimePicker(this, TimePicker.HOUR_24);
                pickerStart.setRangeStart(0, 0);//00:00
                pickerStart.setRangeEnd(23, 00);//23:00
                pickerStart.setTopLineVisible(false);
                pickerStart.setOnTimePickListener(new TimePicker.OnTimePickListener() {
                    @Override
                    public void onTimePicked(String hour, String minute) {
                        //showToast(hour + ":" + minute);
                        appointment_service_date_start.setText(hour + ":" + minute);
                    }
                });
                pickerStart.show();
                break;
            case R.id.appointment_service_date_end:
                TimePicker pickerEnd = new TimePicker(this, TimePicker.HOUR_24);
                pickerEnd.setRangeStart(0, 0);//09:00
                pickerEnd.setRangeEnd(23, 00);//23:00
                pickerEnd.setTopLineVisible(false);
                pickerEnd.setOnTimePickListener(new TimePicker.OnTimePickListener() {
                    @Override
                    public void onTimePicked(String hour, String minute) {
                        //showToast(hour + ":" + minute);
                        appointment_service_date_end.setText(hour + ":" + minute);
                    }
                });
                pickerEnd.show();
                break;
            case R.id.appointment_service_fill_address:
                intent.setClass(AppointmentServiceFillDateActivity.this, AppointmenServiceMapActivity.class);
                startActivity(intent);
                break;
            //测试缓存，暂时留用
            case R.id.appointment_service_choice_date:
                Preference pf = new Preference(this);
                String a = pf.getString("poiName");
                String a1 = pf.getString("address");
                String a2 = pf.getString("lat");
                String a3 = pf.getString("lon");
                String a4 = pf.getString("city");
                Log.v("print","测试缓存是否清除"+a+a1+a2+a3+a4);
                break;
        }
    }

    /***********************************************************************************************************************/

    public AppointmentServiceFillDateActivity() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
        currentYear = year_c;
        currentMonth = month_c;
        currentDay = day_c;
        sc = new SpecialCalendar();
        getCalendar(year_c, month_c);
        week_num = getWeeksOfMonth();
        currentNum = week_num;
        if (dayOfWeek == 7) {
            week_c = day_c / 7 + 1;
        } else {
            if (day_c <= (7 - dayOfWeek)) {
                week_c = 1;
            } else {
                if ((day_c - (7 - dayOfWeek)) % 7 == 0) {
                    week_c = (day_c - (7 - dayOfWeek)) / 7 + 1;
                } else {
                    week_c = (day_c - (7 - dayOfWeek)) / 7 + 2;
                }
            }
        }
        currentWeek = week_c;
        getCurrent();

    }

    /**
     * 判断某年某月所有的星期数
     *
     * @param year
     * @param month
     */
    public int getWeeksOfMonth(int year, int month) {
        // 先判断某月的第一天为星期几
        int preMonthRelax = 0;
        int dayFirst = getWhichDayOfWeek(year, month);
        int days = sc.getDaysOfMonth(sc.isLeapYear(year), month);
        if (dayFirst != 7) {
            preMonthRelax = dayFirst;
        }
        if ((days + preMonthRelax) % 7 == 0) {
            weeksOfMonth = (days + preMonthRelax) / 7;
        } else {
            weeksOfMonth = (days + preMonthRelax) / 7 + 1;
        }
        return weeksOfMonth;

    }

    /**
     * 判断某年某月的第一天为星期几
     *
     * @param year
     * @param month
     * @return
     */
    public int getWhichDayOfWeek(int year, int month) {
        return sc.getWeekdayOfMonth(year, month);

    }

    /**
     *
     * @param year
     * @param month
     */
    public int getLastDayOfWeek(int year, int month) {
        return sc.getWeekDayOfLastMonth(year, month,
                sc.getDaysOfMonth(isLeapyear, month));
    }

    public void getCalendar(int year, int month) {
        isLeapyear = sc.isLeapYear(year); // 是否为闰年
        daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
        dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几
    }

    public int getWeeksOfMonth() {
        // getCalendar(year, month);
        int preMonthRelax = 0;
        if (dayOfWeek != 7) {
            preMonthRelax = dayOfWeek;
        }
        if ((daysOfMonth + preMonthRelax) % 7 == 0) {
            weeksOfMonth = (daysOfMonth + preMonthRelax) / 7;
        } else {
            weeksOfMonth = (daysOfMonth + preMonthRelax) / 7 + 1;
        }
        return weeksOfMonth;
    }

    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        gridView = new GridView(this);
        gridView.setNumColumns(7);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return AppointmentServiceFillDateActivity.this.gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i(TAG, "day:" + dayNumbers[position]);
                selectPostion = position;
                dateAdapter.setSeclection(position);
                dateAdapter.notifyDataSetChanged();
                /*s.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
                        + dateAdapter.getCurrentMonth(selectPostion) + "月"
                        + dayNumbers[position] + "日");*/
            }
        });
        gridView.setLayoutParams(params);
    }

    @Override
    protected void onPause() {
        super.onPause();
        jumpWeek = 0;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    /**
     * 重新计算当前的年月
     */
    public void getCurrent() {
        if (currentWeek > currentNum) {
            if (currentMonth + 1 <= 12) {
                currentMonth++;
            } else {
                currentMonth = 1;
                currentYear++;
            }
            currentWeek = 1;
            currentNum = getWeeksOfMonth(currentYear, currentMonth);
        } else if (currentWeek == currentNum) {
            if (getLastDayOfWeek(currentYear, currentMonth) == 6) {
            } else {
                if (currentMonth + 1 <= 12) {
                    currentMonth++;
                } else {
                    currentMonth = 1;
                    currentYear++;
                }
                currentWeek = 1;
                currentNum = getWeeksOfMonth(currentYear, currentMonth);
            }

        } else if (currentWeek < 1) {
            if (currentMonth - 1 >= 1) {
                currentMonth--;
            } else {
                currentMonth = 12;
                currentYear--;
            }
            currentNum = getWeeksOfMonth(currentYear, currentMonth);
            currentWeek = currentNum - 1;
        }
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        int gvFlag = 0;
        if (e1.getX() - e2.getX() > 80) {
            // 向左滑
            addGridView();
            currentWeek++;
            getCurrent();
            dateAdapter = new AppointmentServiceFillDateAdapter(this, getResources(), currentYear,
                    currentMonth, currentWeek, currentNum, selectPostion,
                    currentWeek == 1 ? true : false);
            dayNumbers = dateAdapter.getDayNumbers();
            gridView.setAdapter(dateAdapter);
            /*s.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
                    + dateAdapter.getCurrentMonth(selectPostion) + "月"
                    + dayNumbers[selectPostion] + "日");*/
            gvFlag++;
            flipper1.addView(gridView, gvFlag);
            dateAdapter.setSeclection(selectPostion);
            this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_left_in));
            this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_left_out));
            this.flipper1.showNext();
            flipper1.removeViewAt(0);
            return true;

        } else if (e1.getX() - e2.getX() < -80) {
            addGridView();
            currentWeek--;
            getCurrent();
            dateAdapter = new AppointmentServiceFillDateAdapter(this, getResources(), currentYear,
                    currentMonth, currentWeek, currentNum, selectPostion,
                    currentWeek == 1 ? true : false);
            dayNumbers = dateAdapter.getDayNumbers();
            gridView.setAdapter(dateAdapter);
            /*s.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
                    + dateAdapter.getCurrentMonth(selectPostion) + "月"
                    + dayNumbers[selectPostion] + "日");*/
            gvFlag++;
            flipper1.addView(gridView, gvFlag);
            dateAdapter.setSeclection(selectPostion);
            this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_right_in));
            this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this,
                    R.anim.push_right_out));
            this.flipper1.showPrevious();
            flipper1.removeViewAt(0);
            return true;
            // }
        }
        return false;
    }

    /**
     * 整个页面都可以滑动 暂时禁用
     * @param event
     * @return
     */
   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.gestureDetector.onTouchEvent(event);
    }*/

}
