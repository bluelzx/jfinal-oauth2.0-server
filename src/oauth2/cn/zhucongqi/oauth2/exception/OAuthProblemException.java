/**
 * 
 */

package cn.zhucongqi.oauth2.exception;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.StrKit;

/**
 * 
 * @author BruceZCQ [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthProblemException extends IllegalArgumentException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1536483571040822380L;
	private String error;
    private String description;
    private String uri;
    private String state;
    private String scope;
    private String redirectUri;

    private int responseStatus;

    private Map<String, String> parameters = new HashMap<String, String>();

    protected OAuthProblemException(String error) {
        this(error, "");
    }

    protected OAuthProblemException(String error, String description) {
        super(error + " " + description);
        this.description = description;
        this.error = error;
    }


    public static OAuthProblemException error(String error) {
        return new OAuthProblemException(error);
    }

    public static OAuthProblemException error(String error, String description) {
        return new OAuthProblemException(error, description);
    }

    public OAuthProblemException description(String description) {
        this.description = description;
        return this;
    }

    public OAuthProblemException uri(String uri) {
        this.uri = uri;
        return this;
    }

    public OAuthProblemException state(String state) {
        this.state = state;
        return this;
    }

    public OAuthProblemException scope(String scope) {
        this.scope = scope;
        return this;
    }

    public OAuthProblemException responseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
        return this;
    }

    public OAuthProblemException setParameter(String name, String value) {
        parameters.put(name, value);
        return this;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }

    public String getUri() {
        return uri;
    }

    public String getState() {
        return state;
    }

    public String getScope() {
        return scope;
    }

    public int getResponseStatus() {
        return responseStatus == 0 ? 400 : responseStatus;
    }

    public String get(String name) {
        return parameters.get(name);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public String getMessage() {
        StringBuilder b = new StringBuilder();
        if (StrKit.notBlank(error)) {
            b.append(error);
        }

        if (StrKit.notBlank(description)) {
            b.append(", ").append(description);
        }


        if (StrKit.notBlank(uri)) {
            b.append(", ").append(uri);
        }


        if (StrKit.notBlank(state)) {
            b.append(", ").append(state);
        }

        if (StrKit.notBlank(scope)) {
            b.append(", ").append(scope);
        }

        return b.toString();
    }

    @Override
    public String toString() {
        return "OAuthProblemException{" +
                "error='" + error + '\'' +
                ", description='" + description + '\'' +
                ", uri='" + uri + '\'' +
                ", state='" + state + '\'' +
                ", scope='" + scope + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", responseStatus=" + responseStatus +
                ", parameters=" + parameters +
                '}';
    }
}
