package data.victor.com;

import java.io.Serializable;

/**
 * Created by victor on 2016/1/22.
 */
public class VideoContentData implements Serializable{
    private String createTime;
    private String hate;
    private int height;
    private int id;
    private String image3;
    private int love;
    private String name;
    private String profileImage;
    private String text;
    private int type;
    private String videoUrl;
    private int videoTime;
    private int voiceLength;
    private int voiceTime;
    private String voiceUri;
    private String weixinUrl;
    private int width;

    public void setAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    private int action;

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setHate(String hate) {
        this.hate = hate;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setVideoTime(int videoTime) {
        this.videoTime = videoTime;
    }

    public void setVoiceLength(int voiceLength) {
        this.voiceLength = voiceLength;
    }

    public void setVoiceTime(int voiceTime) {
        this.voiceTime = voiceTime;
    }

    public void setVoiceUri(String voiceUri) {
        this.voiceUri = voiceUri;
    }

    public void setWeixinUrl(String weixinUrl) {
        this.weixinUrl = weixinUrl;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getHate() {
        return hate;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public String getImage3() {
        return image3;
    }

    public int getLove() {
        return love;
    }

    public String getName() {
        return name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getText() {
        return text;
    }

    public int getType() {
        return type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getVideoTime() {
        return videoTime;
    }

    public int getVoiceLength() {
        return voiceLength;
    }

    public int getVoiceTime() {
        return voiceTime;
    }

    public String getVoiceUri() {
        return voiceUri;
    }

    public String getWeixinUrl() {
        return weixinUrl;
    }

    public int getWidth() {
        return width;
    }
}
