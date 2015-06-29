/**
 * 
 */
package cn.zhucongqi.oauth2.parameters;

import java.util.Map;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.kit.OAuthKit;
import cn.zhucongqi.oauth2.message.OAuthMessage;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class QueryParameterApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {

        String messageUrl = message.getLocationUri();
        if (messageUrl != null) {
            boolean containsQuestionMark = messageUrl.contains("?");
            StringBuffer url = new StringBuffer(messageUrl);
 
            StringBuffer query = new StringBuffer(OAuthKit.format(params.entrySet(), "UTF-8"));
  
            if (StrKit.notBlank(query.toString())) {
                if (containsQuestionMark) {
                    url.append("&").append(query);
                } else {
                    url.append("?").append(query);
                }
            }   
            message.setLocationUri(url.toString());
        }
        return message;
    }
}
