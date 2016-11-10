package com.yy.my.to.entitys.appointment;

/**
 * 预约上门服务选择问诊人实体类
 * @author enmaoFu
 * @date 2016年10月17日
 */
public class AppointmentServiceChoicePatientEntity {

    private String name;//姓名

    private String sex;//性别

    private String age;//年龄

    private String number;//身份证号

    private String falg;//是否默认

    private String phone;//电话号码

    public AppointmentServiceChoicePatientEntity(String name, String sex, String age, String number, String falg, String phone) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.number = number;
        this.falg = falg;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFalg() {
        return falg;
    }

    public void setFalg(String falg) {
        this.falg = falg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
