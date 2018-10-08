package com.zspace.mybatis.demo.entity;

import com.zspace.mybatis.demo.enumtype.RootSiteStatus;

import java.util.Date;

/**
 * @ClassName RootWebsiteEntity
 * @Description TODO
 * @Author MoreRoom
 * @Since 2018/10/5
 */
public class RootWebsiteEntity {
    private int id;
    private Date createTime;
    private Date lastModifyTime;
    private int version;
    /**
     * 网站名称
     */
    private String websiteName;
    /**
     * 网站简称
     */
    private String websiteShortName;
    /**
     * 网站地址
     */
    private String websiteUrl;
    /**
     * 状态
     */
    private RootSiteStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteShortName() {
        return websiteShortName;
    }

    public void setWebsiteShortName(String websiteShortName) {
        this.websiteShortName = websiteShortName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public RootSiteStatus getStatus() {
        return status;
    }

    public void setStatus(RootSiteStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RootWebsiteEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                ", version=" + version +
                ", websiteName='" + websiteName + '\'' +
                ", websiteShortName='" + websiteShortName + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
