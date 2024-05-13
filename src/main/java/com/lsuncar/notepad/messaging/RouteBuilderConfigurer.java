package com.lsuncar.notepad.messaging;

import jakarta.annotation.PostConstruct;
import org.apache.camel.builder.RouteBuilder;

public abstract class RouteBuilderConfigurer extends RouteBuilder {

    protected abstract RouteType getRouteType(RouteType routeType); //Later

    protected abstract String getRoutingKey();

    protected abstract String getExchangeName();

    private UrlParamSerializer urlParamSerializer;

    /**
     * https://camel.apache.org/components/3.21.x/rabbitmq-component.html
     */
    @PostConstruct
    private void doInit() {
        System.out.println( getRoutingKey() );
        urlParamSerializer = new UrlParamSerializer();
        urlParamSerializer.addParameter("exchangeType", "topic");
        urlParamSerializer.addParameter("routingKey", getRoutingKey());
        urlParamSerializer.addParameter("deadLetterRoutingKey", getRoutingKey() + "-dead");
        urlParamSerializer.addParameter("deadLetterExchange", getExchangeName());
        urlParamSerializer.addParameter("deadLetterExchangeType", "topic");
        urlParamSerializer.addParameter("deadLetterQueue", getRoutingKey() + "-dead");
        urlParamSerializer.addParameter("autoDeclare", true);
        urlParamSerializer.addParameter("prefetchCount", 5);
        urlParamSerializer.addParameter("concurrentConsumers", 1); //do parametric

        setUrlParamSerializer( urlParamSerializer );
    }

    public UrlParamSerializer getUrlParamSerializer() {
        return urlParamSerializer;
    }

    public void setUrlParamSerializer(UrlParamSerializer urlParamSerializer) {
        this.urlParamSerializer = urlParamSerializer;
    }
}
