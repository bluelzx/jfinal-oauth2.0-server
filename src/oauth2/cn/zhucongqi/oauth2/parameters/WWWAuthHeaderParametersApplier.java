/**
 * 
 */

package cn.zhucongqi.oauth2.parameters;

import java.util.Map;

import cn.zhucongqi.oauth2.consts.OAuth;
import cn.zhucongqi.oauth2.kit.OAuthKit;
import cn.zhucongqi.oauth2.message.OAuthMessage;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class WWWAuthHeaderParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {
        String header = OAuthKit.encodeOAuthHeader(params);
        message.addHeader(OAuth.HeaderType.WWW_AUTHENTICATE, header);
        return message;
    }
}
