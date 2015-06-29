/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.issuer.MD5Generator;
import cn.zhucongqi.oauth2.issuer.OAuthIssuer;
import cn.zhucongqi.oauth2.issuer.OAuthIssuerKit;
import cn.zhucongqi.oauth2.issuer.ValueGenerator;
import cn.zhucongqi.oauth2.message.OAuthResponse;
import cn.zhucongqi.oauth2.request.OAuthGrantRequest;
import cn.zhucongqi.oauth2.request.OAuthRequest;
import cn.zhucongqi.oauth2.request.RequestType;
import cn.zhucongqi.oauth2.response.OAuthASResponse;

import com.jfinal.ext2.core.Service;

/**
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class OAuth2Service extends Service {

	private RequestType reqType = RequestType.GRANT_REQUEST;
	private ValueGenerator valGenerator = new MD5Generator();
	
	public OAuth2Service setReqType(RequestType reqType) {
		this.reqType = reqType;
		return this;
	}
	
	public OAuth2Service setValGenerator(ValueGenerator valGenerator) {
		this.valGenerator = valGenerator;
		return this;
	}
	
	/**
	 * processing 
	 */
	@SuppressWarnings("unused")
	public void doOAuthAction() {
		try {
			OAuthIssuer oauthIssuer = new OAuthIssuerKit(this.valGenerator);
			switch (reqType) {
			case CODE_TOKEN_REQUEST: {
				OAuthRequest req = new OAuthRequest(
						this.controller.getRequest());
				String authzCode = oauthIssuer.authorizationCode();
			}
				break;
			case GRANT_REQUEST: {
				OAuthGrantRequest req = new OAuthGrantRequest(
						this.controller.getRequest());
				req.getCode();
			}
				break;
			}

			String accessToken = oauthIssuer.accessToken();
			String refreshToken = oauthIssuer.refreshToken();

			//TODO db store token data
			
			OAuthResponse r = OAuthASResponse
					.tokenResponse(HttpServletResponse.SC_OK)
					.setAccessToken(accessToken).setExpiresIn("3600")
					.setRefreshToken(refreshToken).buildJSONMessage();

			this.controller.getResponse().setStatus(r.getResponseStatus());
			this.controller.renderText(r.getBody());
		} catch (OAuthProblemException ex) {
			OAuthResponse r = OAuthResponse
					.errorResponse(HttpServletResponse.SC_MOVED_TEMPORARILY)
					.error(ex).buildJSONMessage();
			this.controller.getResponse().setStatus(r.getResponseStatus());
			this.controller.renderText(r.getBody());
		}
	}
	
	/**
	 * 检查是否完成授权
	 * @return
	 */
    public  boolean isAuthorizationed() {
        boolean authOK = false;
        String Authorization = this.controller.getRequest().getHeader("Authorization");
        if(null == Authorization || Authorization.trim().isEmpty()){
    		this.needAuth();
            return authOK;
        }
        String[] basicArray = Authorization.split("\\s+");
        if(null == basicArray || 2 != basicArray.length){
    		this.needAuth();
            return authOK;
        }
        String base64 = basicArray[1];
        try {
            byte[] buf = new BASE64Decoder().decodeBuffer(base64);
            String idpass = new String(buf, "UTF-8");
            if(null == idpass || idpass.trim().isEmpty()){
                return authOK;
            }
            String[] idpassArray = idpass.split(":");
            if(null == idpassArray || 2 != idpassArray.length){
                return authOK;
            }
            
            String _id = idpassArray[0];
            String _pass = idpassArray[1];
            authOK = this.validate(_id, _pass);
        } catch (IOException e) {
        } finally {
        	if (!authOK) {
        		this.needAuth();
			}
        }
        return authOK;
    }
    
    private void needAuth() {
		this.controller.getResponse().setStatus(401); 
		this.controller.getResponse().addHeader("WWW-Authenticate","Basic realm=tt"); 
		this.controller.renderNull();
    }
	
    /**
     * @param id
     * @param password
     * @return
     * TODO db 校验
     */
    private boolean validate(String id, String password) {
    	return true;
    }
}
