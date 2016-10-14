package hr.bm.context.mapper;

import org.springframework.stereotype.Component;

import hr.bm.dto.ChatMessage;
import hr.bm.dto.OutputChatMessage;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

@Component
public class ChatMessageMapper extends CustomMapper<ChatMessage, OutputChatMessage> {

	@Override
	public void mapAtoB(final ChatMessage chatMsg, final OutputChatMessage outputMsg, final MappingContext context) {
		outputMsg.setFrom(chatMsg.getFrom());
		outputMsg.setText(chatMsg.getText());
		outputMsg.setTime(chatMsg.getTime());
	}

}
