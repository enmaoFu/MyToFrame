package com.yy.my.to.entitys.appointment;

/**
 * 预约定位列表地址实体类
 * @author enmaoFu
 * @date 2016年11月3日
 */

public class AppointmentServiceMapPublicEntity{

    private String title;//标题

    private String address;//详细地址

    public AppointmentServiceMapPublicEntity(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
