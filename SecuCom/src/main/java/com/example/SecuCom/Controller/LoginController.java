package com.example.SecuCom.Controller;

import com.example.SecuCom.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Map;

@RestController
public class LoginController  {

    @Autowired
    private  final OAuth2AuthorizedClientService authorizedClientService;

    public LoginController(OAuth2AuthorizedClientService auth2AuthorizedClientService){
        this.authorizedClientService = auth2AuthorizedClientService;
    }

    @RequestMapping("/**")
    @RolesAllowed("USER")
    public  String getUser(){
        return  "welcome, user";
    }

    @RequestMapping("/admin")
    @RolesAllowed("ADMIN")
    public String getAdmin(){
        return  "Welcome Admin";
    }

    @RequestMapping("/*")
    public  String getUserInfo(Principal user){
        //instancier user info
        StringBuffer userInfo = new StringBuffer();
        if(user instanceof UsernamePasswordAuthenticationToken){

        } else  if (user instanceof OAuth2AuthenticationToken) {
            userInfo.append(getOauth2LoginInfo(user));

        }
        return  userInfo.toString();


    }

    //Connexion avec github

    private StringBuffer getOauth2LoginInfo(Principal user){

        StringBuffer protectedInfo = new StringBuffer();

        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
        OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
        OAuth2User principal = ((OAuth2AuthenticationToken)user).getPrincipal();
            Map<String,Object> userDetails = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();

            String userToken = authClient.getAccessToken().getTokenValue();
            protectedInfo.append("Welcome, " + userDetails.get("name")+"<br><br>");
            protectedInfo.append("E-mail: " + userDetails.get("email")+"<br><br>");
            protectedInfo.append("Access Token: " + userToken+"<br><br>");
        OidcIdToken idToken = getIdToken(principal);
        if (idToken != null){
            protectedInfo.append("idToken value: "+ idToken.getTokenValue() + "<br><br>");
            protectedInfo.append(" Token mapped values <br><br>");
            Map<String, Object> claims = idToken.getClaims();
//            for (String key : claims.keySet()){
//                protectedInfo.append("  " + key +":  " + claims.get(key)+"<br>");
//            }

        }
        return protectedInfo;
    }

    private StringBuffer getUsernamePasswordLoginInfo(Principal user)
    {
        StringBuffer usernameInfo = new StringBuffer();

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
        if(token.isAuthenticated()){
            User u = (User) token.getPrincipal();
            usernameInfo.append("Welcome, " + u.getUsername());
        }
        else{
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }



    //Connexion avec googl
    private OidcIdToken getIdToken(OAuth2User principal){
        if(principal instanceof DefaultOidcUser) {
            DefaultOidcUser oidcUser = (DefaultOidcUser)principal;
            return oidcUser.getIdToken();
        }
        return null;
    }


}
