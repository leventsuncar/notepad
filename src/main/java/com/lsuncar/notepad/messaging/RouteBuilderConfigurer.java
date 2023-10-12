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
        /*
         autoDelete deadLetterExchange deadLetterQueue declare durable hostname portNumber
         autoAck vhost prefetchCount concurrentConsumers password username
         */
        urlParamSerializer = new UrlParamSerializer();
        urlParamSerializer.addParameter("routingKey", getRoutingKey());
        urlParamSerializer.addParameter("exchangeType", "topic");


        urlParamSerializer.addParameter("autoDelete", false);
        urlParamSerializer.addParameter("deadLetterExchange", getExchangeName() + "-dead");
        urlParamSerializer.addParameter("deadLetterQueue", getRoutingKey() + "-dead");
        urlParamSerializer.addParameter("autoDeclare", true);
        urlParamSerializer.addParameter("durable", false);
        urlParamSerializer.addParameter("hostname", "localhost"); //do parametric
        urlParamSerializer.addParameter("portNumber", "5672");
        urlParamSerializer.addParameter("autoAck", true);
        urlParamSerializer.addParameter("prefetchCount", 5); //do parametric
        urlParamSerializer.addParameter("password", "admin"); //do parametric
        urlParamSerializer.addParameter("username", "admin"); //do parametric
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
