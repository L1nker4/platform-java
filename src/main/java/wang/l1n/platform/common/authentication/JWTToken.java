package wang.l1n.platform.common.authentication;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/19 10:10
 * @description： JSON Web Token
 */
@Data
public class JWTToken implements AuthenticationToken {

    private static final long serialVersionUID = 1282057025599826155L;

    private String token;

    private String exipreAt;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String exipreAt) {
        this.token = token;
        this.exipreAt = exipreAt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
