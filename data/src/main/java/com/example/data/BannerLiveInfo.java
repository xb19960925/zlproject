package com.example.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 任小龙 on 2020/6/8.
 */
public class BannerLiveInfo implements Serializable {
    private static final long serialVersionUID = -5079196530172264541L;
    public List<Carousel> Carousel;
    public List<Live> live;

    public class Carousel implements Serializable {
        public String img;
        public String thumb;
        public String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Live implements Serializable {
        public Live(String pActivityName) {
            activityName = pActivityName;
        }

        public String activityName;
        public String correlative_lessons;
        public String coverImgUrl;
        public String endTime;
        public String from_type;
        public String is_liveing;
        public String lesson_id;
        public String live_id;
        public String specialty_id;
        public String startTime;
        public String teacher_name;
        public String teacher_photo;
        public String teacher_uid;
        public String url;

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getCorrelative_lessons() {
            return correlative_lessons;
        }

        public void setCorrelative_lessons(String correlative_lessons) {
            this.correlative_lessons = correlative_lessons;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getFrom_type() {
            return from_type;
        }

        public void setFrom_type(String from_type) {
            this.from_type = from_type;
        }

        public String getIs_liveing() {
            return is_liveing;
        }

        public void setIs_liveing(String is_liveing) {
            this.is_liveing = is_liveing;
        }

        public String getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(String lesson_id) {
            this.lesson_id = lesson_id;
        }

        public String getLive_id() {
            return live_id;
        }

        public void setLive_id(String live_id) {
            this.live_id = live_id;
        }

        public String getSpecialty_id() {
            return specialty_id;
        }

        public void setSpecialty_id(String specialty_id) {
            this.specialty_id = specialty_id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getTeacher_photo() {
            return teacher_photo;
        }

        public void setTeacher_photo(String teacher_photo) {
            this.teacher_photo = teacher_photo;
        }

        public String getTeacher_uid() {
            return teacher_uid;
        }

        public void setTeacher_uid(String teacher_uid) {
            this.teacher_uid = teacher_uid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
