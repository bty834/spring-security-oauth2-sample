package org.bty.blog.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bty
 * @date 2022/9/26
 * @since 1.8
 **/
public class JacksonUtil {

    private volatile static ObjectMapper objectMapper;

    private JacksonUtil(){}

    public static ObjectMapper getObjectMapper(){
        if(objectMapper==null){
            synchronized (JacksonUtil.class){
                if(objectMapper==null){
                    objectMapper = new ObjectMapper();
                }
            }
        }
        return objectMapper;
    }

}
