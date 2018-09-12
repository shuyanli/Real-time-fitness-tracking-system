package demo.web;

import demo.model.CurrentPositionDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketApi {

    @MessageMapping("/sendMessage") //定义入口和出口, message要发出去
    @SendTo("/topic/locations")
    public CurrentPositionDto sendMessage(CurrentPositionDto message){
        return message;
    }
}
//通过访问messageMapping, 将前端发来的json转成的dto, 发送给/topic/locations, 这个simpleBroker中, 返回前端