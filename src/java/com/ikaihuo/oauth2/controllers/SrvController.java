/**
 * 用于一些公共的服务管理，比如验证码等
 */
package com.ikaihuo.oauth2.controllers;

import com.ikaihuo.oauth2.consts.Consts;
import com.jfinal.aop.Clear;
import com.jfinal.ext.render.CaptchaRender;
import com.jfinal.ext2.core.ControllerExt;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version 1.0
 */
public class SrvController extends ControllerExt {

	/**
	 * 生成图像验证码
	 */
	@Clear
	public void captcha(){
		this.render(new CaptchaRender(Consts.CAPTCHA_RDNDOM_KEY));
	}

}
