package com.alorma.github.sdk.services.login;

import com.alorma.github.sdk.bean.dto.request.RequestTokenDTO;
import com.alorma.github.sdk.bean.dto.response.Token;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by Bernat on 13/07/2014.
 */
public interface LoginService {

    @POST("/login/oauth/access_token")
    Observable<Token> requestToken(@Body RequestTokenDTO requestTokenDTO);
}
