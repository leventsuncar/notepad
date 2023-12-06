package com.lsuncar.notepad.messaging.route;

import com.lsuncar.notepad.messaging.RouteBuilderConfigurer;
import com.lsuncar.notepad.messaging.RouteType;
import com.lsuncar.notepad.messaging.UrlParamSerializer;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserShareNoteRoute extends RouteBuilderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger( UserShareNoteRoute.class );

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
                    .convertBodyTo( String.class, "UTF-8" )
                    .to(getRabbitCommonPath() + "%3F" + urlParamSerializer.serializeAsUrlPathParameters());

            from( getRabbitCommonPath() + "%3F" + urlParamSerializer.serializeAsUrlPathParameters() )
                    .routeId( "IN-shareNote" )
                    .process(new Processor() {
                        @Override
                        public void process(Exchange exchange) throws Exception {
                            logger.info( "SENT MESSAGE:\n" + exchange.getIn().getBody( String.class ) );
                        }
                    });
        }

    }
}
