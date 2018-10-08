package com.zspace.mybatis.demo.service;

import com.zspace.mybatis.demo.entity.RootWebsiteEntity;

/**
 * @ClassName RootWebsiteService
 * @Description TODO
 * @Author MoreRoom
 * @Since 2018/10/5
 */
public interface RootWebsiteService {

    public boolean addNewRootWebSite(RootWebsiteEntity rootWebSite);

    public RootWebsiteEntity queryRootWebsiteById(int id);

}
