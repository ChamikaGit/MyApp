package com.rdmns24.chamiapps.rdmns24live.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Lostfound{

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private Status status;
    private final static long serialVersionUID = 5492540833280680179L;

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

    public class Datum implements Serializable
    {
        @SerializedName("rd_item_id")
        @Expose
        private String rdItemId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("item_type")
        @Expose
        private String itemType;
        @SerializedName("item_date")
        @Expose
        private String itemDate;
        @SerializedName("datetime")
        @Expose
        private String datetime;
        private final static long serialVersionUID = -7038737966199534583L;

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
        @Expose
        private Boolean success;
        @SerializedName("message")
        @Expose
        private String message;
        private final static long serialVersionUID = -2328662451467252628L;

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