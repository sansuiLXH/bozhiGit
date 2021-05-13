package com.sansui.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 11:37
 * @modified By  西西里_SanSui in 2021/5/10 11:37
 * @description AddDescriptionHere
 */
public class OnlineCountListener implements HttpSessionListener {
    //监听session创建
    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
