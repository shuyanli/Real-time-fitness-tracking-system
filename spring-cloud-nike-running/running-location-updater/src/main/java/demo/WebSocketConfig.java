package demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){ //消息的转发
        config.enableSimpleBroker("/topic/locations"); //spring本身提供一个simple broker, 在index.html中, queuename就是这个location
        config.setApplicationDestinationPrefixes("/app"); //发送给每一个task
    }

    //sockJS: 针对某些不支持socket的浏览器, 将TCP协议wrap之后基于http, 相当于websocket扩展 (底层协议)
    // 上层协议: stomp
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) { //stomp: 发送message的协议
        stompEndpointRegistry.addEndpoint("/stomp").setAllowedOrigins("*").withSockJS(); //*: 任何前端配置都能连接
    }
}
