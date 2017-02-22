package br.com.caelum.camel;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TratadorMensagemJms implements MessageListener {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onMessage(Message message) {
		logger.warn("Chegou a minha mensagem!");
		try {
			ActiveMQTextMessage txtMessage = (ActiveMQTextMessage) message;
			logger.info(txtMessage.getText());
		} catch (JMSException e) {
			logger.error("Erro ao tratar mensagem!", e);
		}
	}

}
