/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.resources;

import com.progra4.segurosbackend.logic.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author diego
 */
@ApplicationScoped
public class CustomAuthentication implements HttpAuthenticationMechanism {
    @Override
    public AuthenticationStatus validateRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMessageContext httpMsgContext)
    {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            return httpMsgContext.notifyContainerAboutLogin(
                new Principal(){@Override public String getName(){return user.getId();}},
                new HashSet<>(Arrays.asList(new String[]{String.valueOf(user.getAdministrator())})));
        } else {
            return httpMsgContext.notifyContainerAboutLogin(
                new Principal(){@Override public String getName() {return "none";}},
                new HashSet<>());
        }
    }
}
