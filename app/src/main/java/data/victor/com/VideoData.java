package data.victor.com;

import java.util.List;

/**
 * Created by victor on 2016/1/22.
 */
public class VideoData {

    private int status;
    private String resCode;
    private String resError;
    private int allNum;
    private int allPages;
    private List<VideoContentData> videoContentDatas;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public void setResError(String resError) {
        this.resError = resError;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public void setVideoContentDatas(List<VideoContentData> videoContentDatas) {
        this.videoContentDatas = videoContentDatas;
    }

    public int getStatus() {
        return status;
    }

    public String getResCode() {
        return resCode;
    }

    public String getResError() {
        return resError;
    }

    public int getAllNum() {
        return allNum;
    }

    public int getAllPages() {
        return allPages;
    }

    public List<VideoContentData> getVideoContentDatas() {
        return videoContentDatas;
    }
}
