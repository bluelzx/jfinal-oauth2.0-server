/**
 * 
 */

package cn.zhucongqi.oauth2.parameters;

import java.util.Map;

import cn.zhucongqi.oauth2.message.OAuthMessage;

/**
 * Applies given parameters to the OAuth message.
 * Provided implementations include OAuth parameters in one of those:
 * <ul>
 * <li>HTTP request URI Query</li>
 * <li>HTTP request entity-body with application/x-www-form-urlencoded encoding</li>
 * <li>HTTP request entity-body with application/json encoding</li>
 * <li>HTTP request Authorization/WWW-Authenticate header</li>
 * </ul>
 * <p/>
 * Additional implementations can be provided.
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public interface OAuthParametersApplier {

    OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params);
}
