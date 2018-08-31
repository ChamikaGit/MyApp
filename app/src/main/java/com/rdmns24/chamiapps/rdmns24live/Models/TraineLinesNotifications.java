package com.rdmns24.chamiapps.rdmns24live.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fidenz on 4/10/18.
 */

public class TraineLinesNotifications {


    /**
     * data : [{"post_notification_id":"1","train_line_id":"1","notification_title":"Testing main line","notification_description":"sdsdsd","notification_url":"","notification_status":"1","created_datetime":"0000-00-00 00:00:00","updated_datetime":"0000-00-00 00:00:00"}]
     * status : {"success":true,"message":"SUCCESS"}
     */

    @SerializedName("status")
    private StatusBean status;
    @SerializedName("data")
    private List<DataBean> data;

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class StatusBean {
        /**
         * success : true
         * message : SUCCESS
         */

        @SerializedName("success")
        private boolean success;
        @SerializedName("message")
        private String message;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * post_notification_id : 1
         * train_line_id : 1
         * notification_title : Testing main line
         * notification_description : sdsdsd
         * notification_url :
         * notification_status : 1
         * created_datetime : 0000-00-00 00:00:00
         * updated_datetime : 0000-00-00 00:00:00
         */

        @SerializedName("post_notification_id")
        private String postNotificationId;
        @SerializedName("train_line_id")
        private String trainLineId;
        @SerializedName("notification_title")
        private String notificationTitle;
        @SerializedName("notification_description")
        private String notificationDescription;
        @SerializedName("notification_url")
        private String notificationUrl;
        @SerializedName("notification_status")
        private String notificationStatus;
        @SerializedName("created_datetime")
        private String createdDatetime;
        @SerializedName("updated_datetime")
        private String updatedDatetime;

        public String getPostNotificationId() {
            return postNotificationId;
        }

        public void setPostNotificationId(String postNotificationId) {
            this.postNotificationId = postNotificationId;
        }

        public String getTrainLineId() {
            return trainLineId;
        }

        public void setTrainLineId(String trainLineId) {
            this.trainLineId = trainLineId;
        }

        public String getNotificationTitle() {
            return notificationTitle;
        }

        public void setNotificationTitle(String notificationTitle) {
            this.notificationTitle = notificationTitle;
        }

        public String getNotificationDescription() {
            return notificationDescription;
        }

        public void setNotificationDescription(String notificationDescription) {
            this.notificationDescription = notificationDescription;
        }

        public String getNotificationUrl() {
            return notificationUrl;
        }

        public void setNotificationUrl(String notificationUrl) {
            this.notificationUrl = notificationUrl;
        }

        public String getNotificationStatus() {
            return notificationStatus;
        }

        public void setNotificationStatus(String notificationStatus) {
            this.notificationStatus = notificationStatus;
        }

        public String getCreatedDatetime() {
            return createdDatetime;
        }

        public void setCreatedDatetime(String createdDatetime) {
            this.createdDatetime = createdDatetime;
        }

        public String getUpdatedDatetime() {
            return updatedDatetime;
        }

        public void setUpdatedDatetime(String updatedDatetime) {
            this.updatedDatetime = updatedDatetime;
        }
    }
}
