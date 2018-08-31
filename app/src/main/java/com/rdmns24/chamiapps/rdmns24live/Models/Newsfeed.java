package com.rdmns24.chamiapps.rdmns24live.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fidenz on 3/30/18.
 */

public class Newsfeed {


    /**
     * data : [{"post_id":"2","post_title":"Testing3","post_description":"3ewewe","post_image":"abc.jpg","post_status":"1","created_datetime":"2018-01-17 00:00:00","updated_datetime":"2018-01-17 00:00:00"},{"post_id":"1","post_title":"Testing","post_description":"hhhhhh","post_image":"abc.jpg","post_status":"1","created_datetime":"0000-00-00 00:00:00","updated_datetime":"0000-00-00 00:00:00"}]
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
         * post_id : 2
         * post_title : Testing3
         * post_description : 3ewewe
         * post_image : abc.jpg
         * post_status : 1
         * created_datetime : 2018-01-17 00:00:00
         * updated_datetime : 2018-01-17 00:00:00
         */

        @SerializedName("post_id")
        private String postId;
        @SerializedName("post_title")
        private String postTitle;
        @SerializedName("post_description")
        private String postDescription;
        @SerializedName("post_image")
        private String postImage;
        @SerializedName("post_url")
        private String postUrl;
        @SerializedName("post_status")
        private String postStatus;
        @SerializedName("created_datetime")
        private String createdDatetime;
        @SerializedName("updated_datetime")
        private String updatedDatetime;

        public String getPostId() {
            return postId;
        }

        public void setPostId(String postId) {
            this.postId = postId;
        }

        public String getPostTitle() {
            return postTitle;
        }

        public void setPostTitle(String postTitle) {
            this.postTitle = postTitle;
        }

        public String getPostDescription() {
            return postDescription;
        }

        public void setPostDescription(String postDescription) {
            this.postDescription = postDescription;
        }

        public String getPostImage() {
            return postImage;
        }

        public void setPostImage(String postImage) {
            this.postImage = postImage;
        }

        public String getPostStatus() {
            return postStatus;
        }

        public void setPostStatus(String postStatus) {
            this.postStatus = postStatus;
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
        public String getPostUrl() {
            return postUrl;
        }

        public void setPostUrl(String postUrl) {
            this.postUrl = postUrl;
        }
    }
}
