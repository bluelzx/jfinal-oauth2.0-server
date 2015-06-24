/**
 * JFinal 的配置信息
 */
package com.ikaihuo.oauth2.cfg;

import com.ikaihuo.oauth2.controllers.ViewController;
import com.ikaihuo.oauth2.data.DataUtils;
import com.ikaihuo.oauth2.db.models.User;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext2.config.JFinalConfigExt;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class Config extends JFinalConfigExt {
	
	public void afterJFinalStarted(){
		//初始化数据
		DataUtils.init();
	}

	@Override
	public void configMoreConstants(Constants me) {
		
	}

	@Override
	public void configMoreRoutes(Routes me) {
		 //指定/为用户controller
		 me.add("/", ViewController.class);		
	}

	@Override
	public void configMorePlugins(Plugins me) {
	}

	@Override
	public void configTablesMapping(ActiveRecordPlugin arp) {
		// 关联数据库表
		arp.addMapping(User.table, User.class);
	}

	@Override
	public void configMoreInterceptors(Interceptors me) {

	}

	@Override
	public void configMoreHandlers(Handlers me) {
		
	}

}
