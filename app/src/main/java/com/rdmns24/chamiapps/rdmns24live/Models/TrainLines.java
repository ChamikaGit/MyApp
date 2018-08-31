package com.rdmns24.chamiapps.rdmns24live.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fidenz on 4/4/18.
 */

public class TrainLines {


    /**
     * data : [{"train_line_id":"1","train_line":"MAIN LINE","train_line_sinhala":"ප්\u200dරධාන මාර්ගය","train_line_discription":"කොළඹ කොටුව සිට බදුල්ල දක්වා ","created_datetime":"2018-04-04 05:12:08","updated_datetime":"2018-04-04 04:10:09"},{"train_line_id":"2","train_line":"PUTTALAM LINE","train_line_sinhala":"පුත්තලම මාර්ගය","train_line_discription":"කොළඹ කොටුව සිට පුත්තලම දක්වා","created_datetime":"2018-04-04 05:12:08","updated_datetime":"2018-04-04 04:10:09"},{"train_line_id":"3","train_line":"COASTAL LINE","train_line_sinhala":"මුහුදුබඩ මාර්ගය","train_line_discription":"කොළඹ කොටුව සිට මාතර දක්වා","created_datetime":"2018-04-04 05:12:08","updated_datetime":"2018-04-04 04:10:09"},{"train_line_id":"4","train_line":"KELANI VALLEY LINE","train_line_sinhala":"කැළණිවැලි මාර්ගය","train_line_discription":"කොළඹ කොටුව සිට අවිස්සාවේල්ල දක්වා","created_datetime":"2018-04-04 05:12:08","updated_datetime":"2018-04-04 04:10:09"},{"train_line_id":"5","train_line":"OTHER LINES","train_line_sinhala":"අනෙකුත් මාර්ගයන්","train_line_discription":"මාතලේ / මඩකලපුව / ත්\u200dරිකුණාමලය / මන්නාරම / උතුරු මාර්ගය","created_datetime":"2018-04-04 05:12:08","updated_datetime":"2018-04-04 04:10:09"}]
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
         * train_line_id : 1
         * train_line : MAIN LINE
         * train_line_sinhala : ප්‍රධාන මාර්ගය
         * train_line_discription : කොළඹ කොටුව සිට බදුල්ල දක්වා
         * created_datetime : 2018-04-04 05:12:08
         * updated_datetime : 2018-04-04 04:10:09
         */

        @SerializedName("train_line_id")
        private String trainLineId;
        @SerializedName("train_line")
        private String trainLine;
        @SerializedName("train_line_sinhala")
        private String trainLineSinhala;
        @SerializedName("train_line_discription")
        private String trainLineDiscription;
        @SerializedName("created_datetime")
        private String createdDatetime;
        @SerializedName("updated_datetime")
        private String updatedDatetime;

        public String getTrainLineId() {
            return trainLineId;
        }

        public void setTrainLineId(String trainLineId) {
            this.trainLineId = trainLineId;
        }

        public String getTrainLine() {
            return trainLine;
        }

        public void setTrainLine(String trainLine) {
            this.trainLine = trainLine;
        }

        public String getTrainLineSinhala() {
            return trainLineSinhala;
        }

        public void setTrainLineSinhala(String trainLineSinhala) {
            this.trainLineSinhala = trainLineSinhala;
        }

        public String getTrainLineDiscription() {
            return trainLineDiscription;
        }

        public void setTrainLineDiscription(String trainLineDiscription) {
            this.trainLineDiscription = trainLineDiscription;
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
