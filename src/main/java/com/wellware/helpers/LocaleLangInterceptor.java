package com.wellware.helpers;



import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;
import java.util.Objects;

@Component
public class LocaleLangInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) {

        String Locale="English";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try{
            String cookieLocal="";
            Cookie[] cookies = request.getCookies();
            //iterate each cookie
            for (Cookie cookie : cookies) {
                //display only the cookie with the name 'website'
                if (cookie.getName().equals("locale")) {
                    cookieLocal= cookie.getValue();
                }
            }
            if(!Objects.equals(cookieLocal, "")){
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                localeResolver.setLocale(request, response,getLocal(cookieLocal));
            }
            else if(auth !=null){
//                Profile profile = (Profile) auth.getPrincipal();
//                Locale= profile.getLocale();
                //Locale = Locale != null ? (Locale.equals("")?"English":Locale) :"English";
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                localeResolver.setLocale(request, response,getLocal(Locale));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private Locale getLocal(String nativeLang){
        return switch (nativeLang) {
            case "Arabic" -> new Locale("ar", "DZ");
            default -> Locale.ENGLISH;
        };

    }





}
