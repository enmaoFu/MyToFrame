package com.yy.my.to.entitys.personal;

/**
 * 个人中心我的关注医生实体类
 * @author enmaoFu
 * @date 2016年11月15日
 */
public class PersonMyFollowDocterEntity {

    private int person_my_follow_docter_img;//医生头像

    private String person_my_follow_docter_name;//医生名字

    private String person_my_follow_docter_title;//医生职称

    private String person_my_follow_docter_address;//医生所在医院

    public PersonMyFollowDocterEntity(int person_my_follow_docter_img, String person_my_follow_docter_name, String person_my_follow_docter_title, String person_my_follow_docter_address) {
        this.person_my_follow_docter_img = person_my_follow_docter_img;
        this.person_my_follow_docter_name = person_my_follow_docter_name;
        this.person_my_follow_docter_title = person_my_follow_docter_title;
        this.person_my_follow_docter_address = person_my_follow_docter_address;
    }

    public int getPerson_my_follow_docter_img() {
        return person_my_follow_docter_img;
    }

    public void setPerson_my_follow_docter_img(int person_my_follow_docter_img) {
        this.person_my_follow_docter_img = person_my_follow_docter_img;
    }

    public String getPerson_my_follow_docter_name() {
        return person_my_follow_docter_name;
    }

    public void setPerson_my_follow_docter_name(String person_my_follow_docter_name) {
        this.person_my_follow_docter_name = person_my_follow_docter_name;
    }

    public String getPerson_my_follow_docter_title() {
        return person_my_follow_docter_title;
    }

    public void setPerson_my_follow_docter_title(String person_my_follow_docter_title) {
        this.person_my_follow_docter_title = person_my_follow_docter_title;
    }

    public String getPerson_my_follow_docter_address() {
        return person_my_follow_docter_address;
    }

    public void setPerson_my_follow_docter_address(String person_my_follow_docter_address) {
        this.person_my_follow_docter_address = person_my_follow_docter_address;
    }
}
