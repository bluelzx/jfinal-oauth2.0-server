/**
 * 
 */
package cn.zhucongqi.oauth2.parameters;

import java.util.Map;

import cn.zhucongqi.oauth2.kit.OAuthKit;
import cn.zhucongqi.oauth2.message.OAuthMessage;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class BodyURLEncodedParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {
        String body = OAuthKit.format(params.entrySet(), "UTF-8");
        message.setBody(body);
        return message;
    }
}
