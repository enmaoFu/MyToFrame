package com.yy.my.to.entitys.personal;

/**
 * 个人中心订单医疗机构实体类
 * @author enmaoFu
 * @date 2016年11月14日
 */
public class PersonOrderStatusDocterMechanismEntity {

    private int person_order_mechanism_img;//医生头像

    private String person_order_mechanism_name;//医生名字

    private String person_order_mechanism_title;//医生职称

    private String person_order_mechanism_title_address;//医生所在医院

    public PersonOrderStatusDocterMechanismEntity(int person_order_mechanism_img, String person_order_mechanism_name, String person_order_mechanism_title, String person_order_mechanism_title_address) {
        this.person_order_mechanism_img = person_order_mechanism_img;
        this.person_order_mechanism_name = person_order_mechanism_name;
        this.person_order_mechanism_title = person_order_mechanism_title;
        this.person_order_mechanism_title_address = person_order_mechanism_title_address;
    }

    public int getPerson_order_mechanism_img() {
        return person_order_mechanism_img;
    }

    public void setPerson_order_mechanism_img(int person_order_mechanism_img) {
        this.person_order_mechanism_img = person_order_mechanism_img;
    }

    public String getPerson_order_mechanism_name() {
        return person_order_mechanism_name;
    }

    public void setPerson_order_mechanism_name(String person_order_mechanism_name) {
        this.person_order_mechanism_name = person_order_mechanism_name;
    }

    public String getPerson_order_mechanism_title() {
        return person_order_mechanism_title;
    }

    public void setPerson_order_mechanism_title(String person_order_mechanism_title) {
        this.person_order_mechanism_title = person_order_mechanism_title;
    }

    public String getPerson_order_mechanism_title_address() {
        return person_order_mechanism_title_address;
    }

    public void setPerson_order_mechanism_title_address(String person_order_mechanism_title_address) {
        this.person_order_mechanism_title_address = person_order_mechanism_title_address;
    }
}
