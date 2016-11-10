package com.yy.my.to.entitys.appointment;

/**
 * 预约上门服务医生详情评价实体类
 * @author enmaoFu
 * @date 2016年10月24日
 */
public class AppointmentServiceDoctorDetailsEntity {

    private String phone;//电话号码;

    private String text;//评价内容

    private String date;//评价时间

    public AppointmentServiceDoctorDetailsEntity(String phone, String text, String date) {
        this.phone = phone;
        this.text = text;
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
