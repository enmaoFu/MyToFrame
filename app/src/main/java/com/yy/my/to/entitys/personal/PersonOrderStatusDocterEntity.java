package com.yy.my.to.entitys.personal;

/**
 * 个人中心订单医生实体类
 * @author enmaoFu
 * @date 2016年11月14日
 */
public class PersonOrderStatusDocterEntity {

    private int person_order_img;//医生头像

    private String person_order_name;//医生名字

    private String person_order_title;//医生职称

    private String person_order_title_address;//医生所在医院

    public PersonOrderStatusDocterEntity(int person_order_img, String person_order_name, String person_order_title, String person_order_title_address) {
        this.person_order_img = person_order_img;
        this.person_order_name = person_order_name;
        this.person_order_title = person_order_title;
        this.person_order_title_address = person_order_title_address;
    }

    public int getPerson_order_img() {
        return person_order_img;
    }

    public void setPerson_order_img(int person_order_img) {
        this.person_order_img = person_order_img;
    }

    public String getPerson_order_name() {
        return person_order_name;
    }

    public void setPerson_order_name(String person_order_name) {
        this.person_order_name = person_order_name;
    }

    public String getPerson_order_title() {
        return person_order_title;
    }

    public void setPerson_order_title(String person_order_title) {
        this.person_order_title = person_order_title;
    }

    public String getPerson_order_title_address() {
        return person_order_title_address;
    }

    public void setPerson_order_title_address(String person_order_title_address) {
        this.person_order_title_address = person_order_title_address;
    }
}
