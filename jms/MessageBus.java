package jms;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MessageBus implements Runnable {
	private static final int CAPACITY = 10;
	private static final long SLEEP = 1;
	private final Thread t;
	private final List<MessageListener> listeners = new ArrayList<>();
	private final Deque<Message> messages;
	private boolean running;
	private boolean deliver;

	public MessageBus(int capacity) {
		messages = new LinkedBlockingDeque<>(capacity);
		t = new Thread(this);
		deliver = true;
	}

	public MessageBus() {
		this(CAPACITY);
	}

	public void start() {
		running = true;
		t.start();
	}

	public void registerListener(MessageListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void unregisterListener(MessageListener listener) {
		listeners.remove(listener);
	}

	public boolean push(Message message) {
		return messages.offerLast(message);
	}

	@Override
	public void run() {
		while (running) {
			if (deliver) {
				Message message = messages.pollFirst();
				if (message != null) {
					for (MessageListener listener : listeners) {
						listener.onMessage(message);
					}

				}
			}
			Utils.sleep(SLEEP);
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isDeliver() {
		return deliver;
	}

	public void setDeliver(boolean deliver) {
		this.deliver = deliver;
	}

}
