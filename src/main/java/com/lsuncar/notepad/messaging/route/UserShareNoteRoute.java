package com.lsuncar.notepad.messaging.route;

import com.lsuncar.notepad.messaging.RouteBuilderConfigurer;
import com.lsuncar.notepad.messaging.RouteType;
import com.lsuncar.notepad.messaging.UrlParamSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserShareNoteRoute extends RouteBuilderConfigurer {

    @Value("${rabbit.active}")
    private boolean rabbitActive;

    @Override
    protected RouteType getRouteType(RouteType routeType) {
        return null;
    }

    @Override
    protected String getRoutingKey() {
        return "shared.users"; //maybe parametric
    }

    @Override
    protected String getExchangeName() {
        return "amq.topic"; //parametric
    }

    private String getRabbitCommonPath() {
        return "spring-rabbitmq:" + getExchangeName();
    }

    @Override
    public void configure() throws Exception {
        UrlParamSerializer urlParamSerializer = getUrlParamSerializer();
        if (rabbitActive) {
            from("direct:shareNote")
                    .routeId("OUT-shareNote")
                    //convert?
                    .to(getRabbitCommonPath() + "%3F" + urlParamSerializer.serializeAsUrlPathParameters());
        }

    }
}
