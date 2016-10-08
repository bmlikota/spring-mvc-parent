package hr.bm.web.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hr.bm.dto.ChatMessage;
import hr.bm.dto.OutputChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public OutputChatMessage send(ChatMessage message) throws Exception {
		String time = new SimpleDateFormat("HH:mm").format(new Date());
		return new OutputChatMessage(message.getFrom(), message.getText(), time);
	}

	@RequestMapping(value = "/chatHome", method = RequestMethod.GET)
	public String goHome() {
		return "chat/chatPage";
	}

}
