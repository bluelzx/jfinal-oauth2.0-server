/**
 * 
 */
package cn.zhucongqi.oauth2.parameters;

import java.util.Map;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.consts.OAuth;
import cn.zhucongqi.oauth2.kit.OAuthKit;
import cn.zhucongqi.oauth2.message.OAuthMessage;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class FragmentParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {

        String messageUrl = message.getLocationUri();
        if (messageUrl != null) {
            StringBuilder url = new StringBuilder(messageUrl);

            if (params.containsKey(OAuth.OAUTH_REFRESH_TOKEN)) {
                params.remove(OAuth.OAUTH_REFRESH_TOKEN);
            }

            String fragmentQuery = OAuthKit.format(params.entrySet(), "UTF-8");

            if (StrKit.notBlank(fragmentQuery)) {
                if (params.size() > 0) {
                        url.append("#").append(fragmentQuery);
                }
            }
            message.setLocationUri(url.toString());
        }
        return message;
    }
}
