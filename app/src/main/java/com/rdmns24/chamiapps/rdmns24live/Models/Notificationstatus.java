package com.rdmns24.chamiapps.rdmns24live.Models;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notificationstatus implements Serializable
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("player_id")
    @Expose
    private String playerId;
    @SerializedName("one_line")
    @Expose
    private String oneLine;
    @SerializedName("two_line")
    @Expose
    private String twoLine;
    @SerializedName("three_line")
    @Expose
    private String threeLine;
    @SerializedName("four_line")
    @Expose
    private String fourLine;
    @SerializedName("five_line")
    @Expose
    private String fiveLine;
    private final static long serialVersionUID = -4543080372123734363L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getOneLine() {
        return oneLine;
    }

    public void setOneLine(String oneLine) {
        this.oneLine = oneLine;
    }

    public String getTwoLine() {
        return twoLine;
    }

    public void setTwoLine(String twoLine) {
        this.twoLine = twoLine;
    }

    public String getThreeLine() {
        return threeLine;
    }

    public void setThreeLine(String threeLine) {
        this.threeLine = threeLine;
    }

    public String getFourLine() {
        return fourLine;
    }

    public void setFourLine(String fourLine) {
        this.fourLine = fourLine;
    }

    public String getFiveLine() {
        return fiveLine;
    }

    public void setFiveLine(String fiveLine) {
        this.fiveLine = fiveLine;
    }

}
