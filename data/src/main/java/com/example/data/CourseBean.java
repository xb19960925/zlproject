package com.example.data;

import java.util.List;

public class CourseBean {


    /**
     * errNo : 0
     * result : {"lists":[{"id":"26831","lesson_id":"4915","lesson_name":"建筑施工图设计实操训练营","type":"1","price":"1999.00","vip_price":"1799.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202001/17/58/225958oc1ou3ph7afzefso.jpg","specialty_id":"1","studentnum":"2147","m_specialty_id":"1","rank":"4.9","comment_html":["full_star","full_star","full_star","full_star","half_star"],"comment_htmls":"","rate":"97%","vip_tag_status":0},{"id":"68485","lesson_id":"8949","lesson_name":"2020年二级注册建筑师备考训练营","type":"1","price":"4199.00","vip_price":"3779.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202002/06/3/105103rmkjtym5a61bhj9g.jpg","specialty_id":"1,2,3","studentnum":"1323","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"63608","lesson_id":"8948","lesson_name":"建筑多软件全能训练营","type":"1","price":"1999.00","vip_price":"1799.10","show_vip_tag":9,"thumb":"http://newoss.zhulong.com/edu/202004/14/0/1818002ubgaf2kr7hg1hus.jpg","specialty_id":"1","studentnum":"1061","m_specialty_id":"1","rank":"4.2","comment_html":["full_star","full_star","full_star","full_star","half_star"],"comment_htmls":"","rate":"99%","vip_tag_status":0},{"id":"65109","lesson_id":"9210","lesson_name":"2020注册消防工程师协议签约班","type":"1","price":"3999.00","vip_price":"3599.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202001/16/48/1847480hbcqkc5mawbmjlx.jpg","specialty_id":"5,8,7,6,1","studentnum":"347","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"69636","lesson_id":"8767","lesson_name":"建筑专业负责人强化训练营","type":"1","price":"1999.00","vip_price":"1799.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/201906/18/29/1624296azostajrz6bu2ug.jpg","specialty_id":"1","studentnum":"427","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"70004","lesson_id":"9268","lesson_name":"2020年二级注册建筑师考前冲刺营","type":"1","price":"2699.00","vip_price":"2429.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202005/12/4/152104anudkquq5fsniguu.jpg","specialty_id":"1,2,3","studentnum":"1010","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"69640","lesson_id":"8965","lesson_name":"建筑施工图设计全能训练营|实操班+强化班","type":"1","price":"3498.00","vip_price":"3148.20","show_vip_tag":9,"thumb":"http://newoss.zhulong.com/edu/202001/08/30/1852309rpng8sm4typhkgg.jpg","specialty_id":"1","studentnum":"195","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"69642","lesson_id":"9185","lesson_name":"建筑多软件+施工图实操｜暴爽套餐","type":"1","price":"3498.00","vip_price":"3148.20","show_vip_tag":9,"thumb":"http://newoss.zhulong.com/edu/202002/11/46/144946bifvigmfoa8hx320.jpg","specialty_id":"1","studentnum":"157","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"40556","lesson_id":"2601","lesson_name":"CAD（天正）建筑施工图设计制图详解","type":"1","price":"199.00","vip_price":"179.10","show_vip_tag":9,"thumb":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1uZK_B5dT1RCvBVdK.jpg","specialty_id":"1","studentnum":"13994","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"68528","lesson_id":"9228","lesson_name":"建筑方案设计训练营","type":"1","price":"1999.00","vip_price":"1799.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/201911/21/50/180250m5mlt6rzwh3yu7mz.jpg","specialty_id":"1","studentnum":"258","m_specialty_id":"1","rank":"4.0","comment_html":["full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"97%","vip_tag_status":0}]}
     * exeTime : 0
     */

    private int errNo;
    private ResultBean result;
    private int exeTime;

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    public static class ResultBean {
        private List<ListsBean> lists;

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * id : 26831
             * lesson_id : 4915
             * lesson_name : 建筑施工图设计实操训练营
             * type : 1
             * price : 1999.00
             * vip_price : 1799.10
             * show_vip_tag : 9
             * thumb : https://newoss.zhulong.com/edu/202001/17/58/225958oc1ou3ph7afzefso.jpg
             * specialty_id : 1
             * studentnum : 2147
             * m_specialty_id : 1
             * rank : 4.9
             * comment_html : ["full_star","full_star","full_star","full_star","half_star"]
             * comment_htmls :
             * rate : 97%
             * vip_tag_status : 0
             */

            private String id;
            private String lesson_id;
            private String lesson_name;
            private String type;
            private String price;
            private String vip_price;
            private int show_vip_tag;
            private String thumb;
            private String specialty_id;
            private String studentnum;
            private String m_specialty_id;
            private String rank;
            private String comment_htmls;
            private String rate;
            private int vip_tag_status;
            private List<String> comment_html;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLesson_id() {
                return lesson_id;
            }

            public void setLesson_id(String lesson_id) {
                this.lesson_id = lesson_id;
            }

            public String getLesson_name() {
                return lesson_name;
            }

            public void setLesson_name(String lesson_name) {
                this.lesson_name = lesson_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public int getShow_vip_tag() {
                return show_vip_tag;
            }

            public void setShow_vip_tag(int show_vip_tag) {
                this.show_vip_tag = show_vip_tag;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(String specialty_id) {
                this.specialty_id = specialty_id;
            }

            public String getStudentnum() {
                return studentnum;
            }

            public void setStudentnum(String studentnum) {
                this.studentnum = studentnum;
            }

            public String getM_specialty_id() {
                return m_specialty_id;
            }

            public void setM_specialty_id(String m_specialty_id) {
                this.m_specialty_id = m_specialty_id;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getComment_htmls() {
                return comment_htmls;
            }

            public void setComment_htmls(String comment_htmls) {
                this.comment_htmls = comment_htmls;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public int getVip_tag_status() {
                return vip_tag_status;
            }

            public void setVip_tag_status(int vip_tag_status) {
                this.vip_tag_status = vip_tag_status;
            }

            public List<String> getComment_html() {
                return comment_html;
            }

            public void setComment_html(List<String> comment_html) {
                this.comment_html = comment_html;
            }
        }
    }
}
