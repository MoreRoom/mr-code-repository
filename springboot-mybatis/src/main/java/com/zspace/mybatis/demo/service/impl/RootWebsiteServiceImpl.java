package com.zspace.mybatis.demo.service.impl;

import com.zspace.mybatis.demo.dao.RootWebsiteDao;
import com.zspace.mybatis.demo.entity.RootWebsiteEntity;
import com.zspace.mybatis.demo.service.RootWebsiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName RootWebsiteServiceImpl
 * @Description TODO
 * @Author MoreRoom
 * @Since 2018/10/5
 */
@Service
public class RootWebsiteServiceImpl implements RootWebsiteService {

    @Resource
    private RootWebsiteDao rootWebsiteDao;

    @Override
    public boolean addNewRootWebSite(RootWebsiteEntity rootWebSite) {
        return rootWebsiteDao.insertNewRootWebsite(rootWebSite) == 1;
    }

    @Override
    public RootWebsiteEntity queryRootWebsiteById(int id) {
        return rootWebsiteDao.selectRootWebsiteById(id);
    }
}
