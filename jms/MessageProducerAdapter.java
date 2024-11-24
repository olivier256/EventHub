package jms;

import javax.jms.CompletionListener;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

public abstract class MessageProducerAdapter implements MessageProducer {

	@Override
	public void setDisableMessageID(boolean value) throws JMSException {
	}

	@Override
	public boolean getDisableMessageID() throws JMSException {
		return false;
	}

	@Override
	public void setDisableMessageTimestamp(boolean value) throws JMSException {
	}

	@Override
	public boolean getDisableMessageTimestamp() throws JMSException {
		return false;
	}

	@Override
	public void setDeliveryMode(int deliveryMode) throws JMSException {
	}

	@Override
	public int getDeliveryMode() throws JMSException {
		return 0;
	}

	@Override
	public void setPriority(int defaultPriority) throws JMSException {
	}

	@Override
	public int getPriority() throws JMSException {
		return 0;
	}

	@Override
	public void setTimeToLive(long timeToLive) throws JMSException {
	}

	@Override
	public long getTimeToLive() throws JMSException {
		return 0;
	}

	@Override
	public void setDeliveryDelay(long deliveryDelay) throws JMSException {
	}

	@Override
	public long getDeliveryDelay() throws JMSException {
		return 0;
	}

	@Override
	public Destination getDestination() throws JMSException {
		return null;
	}

	@Override
	public void close() throws JMSException {
	}

	@Override
	public void send(Message message) throws JMSException {
	}

	@Override
	public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
	}

	@Override
	public void send(Destination destination, Message message) throws JMSException {
	}

	@Override
	public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive)
			throws JMSException {
	}

	@Override
	public void send(Message message, CompletionListener completionListener) throws JMSException {
	}

	@Override
	public void send(Message message, int deliveryMode, int priority, long timeToLive,
			CompletionListener completionListener) throws JMSException {
	}

	@Override
	public void send(Destination destination, Message message, CompletionListener completionListener)
			throws JMSException {
	}

	@Override
	public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive,
			CompletionListener completionListener) throws JMSException {
	}
}
