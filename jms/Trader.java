package jms;

import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import logger.SystemOutLogger;

public class Trader implements MessageListener {
	private static final Logger logger = new SystemOutLogger("");
	private final String traderId;

	public Trader(String traderId) {
		this.traderId = traderId;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage textMessage) {
			try {
				logger.info("Trader " + traderId + " received message: " + textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("Trader " + traderId + " received a non-text message: " + message.toString());
		}
	}
}
