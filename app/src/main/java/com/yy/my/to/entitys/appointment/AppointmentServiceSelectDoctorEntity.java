package com.yy.my.to.entitys.appointment;

/**
 * 预约上门服务选择医生实体类
 * @author enmaoFu
 * @date 2016年10月19日
 */

public class AppointmentServiceSelectDoctorEntity{

    private String doctorName;//医生姓名

    private String doctorTitle;//医生职称

    private String doctorCompany;//医生单位

    private String doctorPrice;//医生价格

    private String doctorDistance;//医生距离

    public AppointmentServiceSelectDoctorEntity(String doctorName, String doctorTitle, String doctorCompany, String doctorPrice, String doctorDistance) {
        this.doctorName = doctorName;
        this.doctorTitle = doctorTitle;
        this.doctorCompany = doctorCompany;
        this.doctorPrice = doctorPrice;
        this.doctorDistance = doctorDistance;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle;
    }

    public String getDoctorCompany() {
        return doctorCompany;
    }

    public void setDoctorCompany(String doctorCompany) {
        this.doctorCompany = doctorCompany;
    }

    public String getDoctorPrice() {
        return doctorPrice;
    }

    public void setDoctorPrice(String doctorPrice) {
        this.doctorPrice = doctorPrice;
    }

    public String getDoctorDistance() {
        return doctorDistance;
    }

    public void setDoctorDistance(String doctorDistance) {
        this.doctorDistance = doctorDistance;
    }
}
