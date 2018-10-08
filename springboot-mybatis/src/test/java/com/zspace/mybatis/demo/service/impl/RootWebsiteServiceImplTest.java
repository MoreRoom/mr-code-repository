package com.zspace.mybatis.demo.service.impl;

import com.zspace.mybatis.demo.entity.RootWebsiteEntity;
import com.zspace.mybatis.demo.enumtype.RootSiteStatus;
import com.zspace.mybatis.demo.service.RootWebsiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RootWebsiteServiceImplTest {

    @Resource
    private RootWebsiteService rootWebsiteService;

    @Test
    public void addNewRootWebSite() {
        RootWebsiteEntity webSite = new RootWebsiteEntity();
        webSite.setCreateTime(new Date());
        webSite.setLastModifyTime(new Date());
        webSite.setWebsiteName("测试名称");
        webSite.setWebsiteShortName("测试简称");
        webSite.setWebsiteUrl("www.baidu.com");
        webSite.setStatus(RootSiteStatus.UNKNOWN);
        rootWebsiteService.addNewRootWebSite(webSite);
    }

    @Test
    public void queryRootWebsiteById() {
        RootWebsiteEntity result = rootWebsiteService.queryRootWebsiteById(2);
        System.out.println(result.toString());
    }
}