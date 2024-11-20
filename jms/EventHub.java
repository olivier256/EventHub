package jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;

public class EventHub {
	private final List<MessageListener> listeners = new ArrayList<>();

	public void registerListener(MessageListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void unregisterListener(MessageListener listener) {
		listeners.remove(listener);
	}

	public void publishMessage(Message message) {
		for (MessageListener listener : listeners) {
			listener.onMessage(message);
		}
	}
}
