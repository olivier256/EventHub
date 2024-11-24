package jms;

import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Trader implements MessageListener {
	private static final Logger logger = Logger.getAnonymousLogger();
	private final String traderId;
	private Message lastReceivedMessage;

	public Trader(String traderId) {
		this.traderId = traderId;
		lastReceivedMessage = null;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage textMessage) {
			logger.info(() -> {
				try {
					return "Trader " + traderId + " received message: " + textMessage.getText();
				} catch (JMSException e) {
					e.printStackTrace();
				}
				return textMessage.toString();
			});
			lastReceivedMessage = message;
		} else {
			logger.warning(() -> "Trader " + traderId + " received a non-text message: " + message.toString());
		}
	}

	@Override
	public String toString() {
		return "Trader [traderId=" + traderId + "]";
	}

	public Message getLastReceivedMessage() {
		return lastReceivedMessage;
	}

}
