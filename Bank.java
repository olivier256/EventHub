package jms;

public class Bank {
	private final String name;
	private final EventHub eventHub;

	public Bank(String name, EventHub eventHub) {
		this.name = name;
		this.eventHub = eventHub;
	}

	@Override
	public String toString() {
		return "Bank [name=" + name + "]";
	}

	public void publishMessage(String event) {
		TextMessageImpl message = new TextMessageImpl(name, event);
		eventHub.publishMessage(message);
	}

}
