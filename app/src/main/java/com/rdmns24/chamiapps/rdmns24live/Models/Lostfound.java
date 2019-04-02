package com.rdmns24.chamiapps.rdmns24live.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Lostfound{

    @SerializedName("data")
    private List<Datum> data = null;
    @SerializedName("status")
    private Status status;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static class Datum implements Serializable
    {
        @SerializedName("rd_item_id")
        private String rdItemId;
        @SerializedName("title")
        private String title;
        @SerializedName("description")
        private String description;
        @SerializedName("item_type")
        private String itemType;
        @SerializedName("item_date")
        private String itemDate;
        @SerializedName("datetime")
        private String datetime;

        public Datum(String rdItemId, String title, String description, String itemType, String itemDate, String datetime) {
            this.rdItemId = rdItemId;
            this.title = title;
            this.description = description;
            this.itemType = itemType;
            this.itemDate = itemDate;
            this.datetime = datetime;
        }

        public String getRdItemId() {
            return rdItemId;
        }

        public void setRdItemId(String rdItemId) {
            this.rdItemId = rdItemId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getItemDate() {
            return itemDate;
        }

        public void setItemDate(String itemDate) {
            this.itemDate = itemDate;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

    }

    public class Status implements Serializable
    {
        @SerializedName("success")
        private Boolean success;
        @SerializedName("message")
        private String message;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }



}