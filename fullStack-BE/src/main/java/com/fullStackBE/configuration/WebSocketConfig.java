package com.fullStackBE.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

// we register a websocket endpoint that the clients will use to connect to our websocket server.
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// with sockjs
		registry.addEndpoint("/ws-message").setAllowedOriginPatterns("*").withSockJS();
		// without sockjs
		//registry.addEndpoint("/ws-message").setAllowedOriginPatterns("*");
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config){
//		destination url
		config.setApplicationDestinationPrefixes("/app");
//		line defines that the messages whose destination starts with “/topic” should be routed to the message broker
		config.enableSimpleBroker("/topic");
	}
}