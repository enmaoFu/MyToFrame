package com.yy.my.to.entitys.appointment;

/**
 * 预约服务医疗机构选择医院实体类
 * @author enmaoFu
 * @date 2016年10月26日
 */
public class AppointmentServiceMechanismSelectHospitalEntity {

    private String hospitalTitle;//医院名字

    private String hospitaGrade;//医院等级

    private String hospitalAddress;//医院地址

    private String hospitalDistance;//医院距离

    public AppointmentServiceMechanismSelectHospitalEntity(String hospitalTitle, String hospitaGrade, String hospitalAddress, String hospitalDistance) {
        this.hospitalTitle = hospitalTitle;
        this.hospitaGrade = hospitaGrade;
        this.hospitalAddress = hospitalAddress;
        this.hospitalDistance = hospitalDistance;
    }

    public String getHospitalTitle() {
        return hospitalTitle;
    }

    public void setHospitalTitle(String hospitalTitle) {
        this.hospitalTitle = hospitalTitle;
    }

    public String getHospitaGrade() {
        return hospitaGrade;
    }

    public void setHospitaGrade(String hospitaGrade) {
        this.hospitaGrade = hospitaGrade;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalDistance() {
        return hospitalDistance;
    }

    public void setHospitalDistance(String hospitalDistance) {
        this.hospitalDistance = hospitalDistance;
    }
}
