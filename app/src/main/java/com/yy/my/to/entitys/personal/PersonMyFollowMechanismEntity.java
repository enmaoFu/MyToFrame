package com.yy.my.to.entitys.personal;

/**
 * 个人中心我的关注机构实体类
 * @author enmaoFu
 * @date 2016年11月15日
 */
public class PersonMyFollowMechanismEntity {

    private int person_my_follow_mechanism_img;//机构头像

    private String person_my_follow_mechanism_name;//机构名字

    private String person_my_follow_mechanism_title;//机构等级

    private String person_my_follow_mechanism_address;//机构所在地址

    public PersonMyFollowMechanismEntity(int person_my_follow_mechanism_img, String person_my_follow_mechanism_name, String person_my_follow_mechanism_title, String person_my_follow_mechanism_address) {
        this.person_my_follow_mechanism_img = person_my_follow_mechanism_img;
        this.person_my_follow_mechanism_name = person_my_follow_mechanism_name;
        this.person_my_follow_mechanism_title = person_my_follow_mechanism_title;
        this.person_my_follow_mechanism_address = person_my_follow_mechanism_address;
    }

    public int getPerson_my_follow_mechanism_img() {
        return person_my_follow_mechanism_img;
    }

    public void setPerson_my_follow_mechanism_img(int person_my_follow_mechanism_img) {
        this.person_my_follow_mechanism_img = person_my_follow_mechanism_img;
    }

    public String getPerson_my_follow_mechanism_name() {
        return person_my_follow_mechanism_name;
    }

    public void setPerson_my_follow_mechanism_name(String person_my_follow_mechanism_name) {
        this.person_my_follow_mechanism_name = person_my_follow_mechanism_name;
    }

    public String getPerson_my_follow_mechanism_title() {
        return person_my_follow_mechanism_title;
    }

    public void setPerson_my_follow_mechanism_title(String person_my_follow_mechanism_title) {
        this.person_my_follow_mechanism_title = person_my_follow_mechanism_title;
    }

    public String getPerson_my_follow_mechanism_address() {
        return person_my_follow_mechanism_address;
    }

    public void setPerson_my_follow_mechanism_address(String person_my_follow_mechanism_address) {
        this.person_my_follow_mechanism_address = person_my_follow_mechanism_address;
    }
}
