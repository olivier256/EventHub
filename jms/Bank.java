package jms;

import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;

public class Bank extends MessageProducerAdapter {
	private static final Logger logger = Logger.getAnonymousLogger();

	private final String name;
	private final MessageBus messageBus;
	private Message lastSentMessage;

	public Bank(String name, MessageBus messageBus) {
		this.name = name;
		this.messageBus = messageBus;
		lastSentMessage = null;
	}

	@Override
	public String toString() {
		return "Bank [name=" + name + "]";
	}

	@Override
	public void send(Message message) throws JMSException {
		logger.info(() -> "Bank " + name + " sending message " + message);
		boolean messageActuallySent = messageBus.push(message);
		if (messageActuallySent) {
			lastSentMessage = message;
		}
	}

	public Message getLastSentMessage() {
		return lastSentMessage;
	}

}
