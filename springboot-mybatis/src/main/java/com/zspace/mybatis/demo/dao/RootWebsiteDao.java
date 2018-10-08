package com.zspace.mybatis.demo.dao;

import com.zspace.mybatis.demo.entity.RootWebsiteEntity;

/**
 * @ClassName RootWebsiteDao
 * @Description TODO
 * @Author MoreRoom
 * @Since 2018/10/5
 */
public interface RootWebsiteDao {
    /**
     * 添加新的网站
     *
     * @param rootWebsite
     * @return
     */
    public int insertNewRootWebsite(RootWebsiteEntity rootWebsite);

    public RootWebsiteEntity selectRootWebsiteById(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(RootWebsiteEntity record);

    int updateByPrimaryKeySelective(RootWebsiteEntity record);

    int updateByPrimaryKey(RootWebsiteEntity record);

}
