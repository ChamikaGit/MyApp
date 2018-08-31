package com.rdmns24.chamiapps.rdmns24live.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fidenz on 5/2/18.
 */

public class NewsfeedRecent {


    /**
     * data : [{"post_notification_id":"11","train_line_id":"1","notification_title":"ෆවුසි කාර්යාල දුම්රිය කාර්මික දෝශයකට ලක්වේ","notification_description":"වලකුඹුර ප්\u200dරදේශයේදී ෆවුසි කාර්යාල දුම්රිය කාර්මික දෝශයකට ලක්ව ඇත. ","notification_url":"","notification_status":"1","created_datetime":"2018-04-23 06:10:45","updated_datetime":"2018-04-23 08:27:57"},{"post_notification_id":"10","train_line_id":"2","notification_title":"බංගදෙනිය දුම්රිය සජීවීව GPS ඔස්සේ","notification_description":"GPS Location Data by - Ashen Perera\r\nClick Open Link to view GPS Data","notification_url":"https://glympse.com/012J-0A0A","notification_status":"1","created_datetime":"2018-04-20 07:35:07","updated_datetime":"2018-04-28 15:28:57"},{"post_notification_id":"9","train_line_id":"4","notification_title":"කොග්සම දුම්රිය යතා තත්වයට පත් කෙරේ","notification_description":"කාර්මික දෝෂයකට ලක්වූ කොග්සම දුම්රිය යතා තත්වයට පත් කෙරේ","notification_url":"https://www.facebook.com/groups/RDMNS/permalink/618457678499375/","notification_status":"1","created_datetime":"2018-04-10 12:37:08","updated_datetime":"2018-04-10 15:57:38"},{"post_notification_id":"8","train_line_id":"4","notification_title":"කොස්ගම දුම්රිය කාර්මික දෝෂයකට ලක්වේ","notification_description":"පාදුක්ක දුම්රිය ස්ථානයට  ආසන්නයේදී කොස්ගම කාරයාල දුම්රිය කාර්මික දෝෂයකට ලක්වේ","notification_url":"https://www.facebook.com/groups/RDMNS/permalink/618457678499375/","notification_status":"1","created_datetime":"2018-04-10 12:36:03","updated_datetime":"2018-04-10 15:57:44"},{"post_notification_id":"7","train_line_id":"5","notification_title":"උතුරු දුම්රිය මාර්ගයේ දුම්රිය පීලිපනීමක්","notification_description":"කන්කසන්තුරේ දක්වා ධාවනය වන නගරාන්තර ශීග්\u200dරඝාමී දුම්රිය පීලිපනීමකට ලක්ව ඇත.","notification_url":"https://www.facebook.com/groups/RDMNS/permalink/618457678499375/","notification_status":"1","created_datetime":"2018-04-10 12:29:50","updated_datetime":"2018-04-10 15:57:49"},{"post_notification_id":"6","train_line_id":"1","notification_title":"මහව කාර්යාල දුම්රිය අනතුරකට ලක්වේ ","notification_description":"ගම්පහ දුම්රිය ස්ථානයට යාබදව මහව කාර්යාල දුම්රිය අනතුරකට ලක් වී ඇත","notification_url":"https://www.facebook.com/groups/RDMNS/permalink/618457678499375/","notification_status":"1","created_datetime":"2018-04-10 12:28:36","updated_datetime":"2018-04-10 15:57:54"},{"post_notification_id":"5","train_line_id":"2","notification_title":"බංගදෙනිය කාර්යාල දුම්රිය ප්\u200dරමාදයි ","notification_description":"බංගදෙනිය කාර්යාල දුම්රිය මිනිත්තු 15 ක ප්\u200dරමාදයකින් යුතුව ධාවනය වේ. එම දුම්රිය  පෙ.ව. 07.30 ට ආයෝජන ප්\u200dරවර්ධන කලාපය දුම්රිය ස්ථානය වෙත ලඟා වී ඇත.","notification_url":"https://www.facebook.com/groups/RDMNS/permalink/618457678499375/","notification_status":"1","created_datetime":"2018-04-20 07:31:53","updated_datetime":"2018-04-20 15:38:34"},{"post_notification_id":"4","train_line_id":"3","notification_title":"සාගරිකා දුම්රිය කාර්මික දෝෂයකට ලක්වේ","notification_description":"දකුණු කළුතරදී සාගරිකා දුම්රිය කාර්මික දෝෂයකට ලක්වී ඇත","notification_url":"https://www.facebook.com/groups/RDMNS/permalink/618457678499375/","notification_status":"1","created_datetime":"2018-04-20 16:25:30","updated_datetime":"2018-04-20 15:26:50"},{"post_notification_id":"3","train_line_id":"4","notification_title":"දුම්රිය මාර්ගයට ගසක් කඩාවැටේ","notification_description":"පාදුක්ක දුම්රිය ස්ථානයට යාබදව දුම්රිය මාර්ගයට ගසක් පතිත වීම හේතුවෙන් කැළණිවැලි මාර්ගයේ දුම්රිය ධාවනයට බාධා ","notification_url":"https://www.facebook.com/groups/RDMNS/permalink/618457678499375/","notification_status":"1","created_datetime":"2018-04-10 12:24:12","updated_datetime":"2018-04-10 15:58:14"}]
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
         * post_notification_id : 11
         * train_line_id : 1
         * notification_title : ෆවුසි කාර්යාල දුම්රිය කාර්මික දෝශයකට ලක්වේ
         * notification_description : වලකුඹුර ප්‍රදේශයේදී ෆවුසි කාර්යාල දුම්රිය කාර්මික දෝශයකට ලක්ව ඇත.
         * notification_url :
         * notification_status : 1
         * created_datetime : 2018-04-23 06:10:45
         * updated_datetime : 2018-04-23 08:27:57
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
