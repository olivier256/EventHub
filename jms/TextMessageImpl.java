package jms;

public class TextMessageImpl extends TextMessageAdapter {
	private final String text;

	public TextMessageImpl(String text) {
		this.text = text;
	}

	@Override
	public String getText() {
		return text;
	}

}
