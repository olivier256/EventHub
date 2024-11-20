package jms;

public class TextMessageImpl extends TextMessageAdapter {
	private final String name;
	private final String event;

	public TextMessageImpl(String name, String event) {
		this.name = name;
		this.event = event;
	}

	@Override
	public String getText() {
		return "TextMessageImpl: {name: \"" + name + "\", event: \"" + event + "\"}";
	}

}
