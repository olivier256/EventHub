package jms;

import java.util.logging.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessageBusTest {
	private static final Logger logger = Logger.getAnonymousLogger();

	@java.lang.SuppressWarnings({ "java:S1121", "java:S2583" })
	private static void checkEA() throws AssertionError {
		boolean eaEnabled = false;
		assert eaEnabled = true;
		if (!eaEnabled) {
			throw new AssertionError("Launch with VM parameter \"-ea\"");
		}
	}

	public static void main(String[] args) {
		checkEA();
		new MessageBusTest().test();
	}

	private void test() {
		testCapacity();
		testSend();
		logger.info("OK!");
	}

	private void testCapacity() {
		String priceUpdate1 = "Price updated: EUR/USD 1.1234";
		String priceUpdate2 = "Price updated: GBP/USD 1.5678";

		MessageBus bus = new MessageBus(2);
		bus.start();

		try (Bank hsbc = new Bank("HSBC", bus)) {
			bus.setDeliver(false);
			send(hsbc, priceUpdate1);
			send(hsbc, priceUpdate2);
			send(hsbc, priceUpdate1);
			Utils.sleep(10);
			checkLastSentMessage(hsbc, priceUpdate2);
			bus.setDeliver(true);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		bus.setRunning(false);
	}

	private void testSend() {
		MessageBus bus = new MessageBus();
		bus.start();

		try (Bank hsbc = new Bank("HSBC", bus); Bank soge = new Bank("SoGé", bus)) {

			Trader trader1 = new Trader("Trader1");
			Trader trader2 = new Trader("Trader2");

			bus.registerListener(trader1);
			bus.registerListener(trader2);

			String priceUpdate1 = "Price updated: EUR/USD 1.1234";
			send(hsbc, priceUpdate1);
			Utils.sleep(10);
			checkLastReceivedMessage(trader1, priceUpdate1);
			checkLastReceivedMessage(trader2, priceUpdate1);

			String priceUpdate2 = "Price updated: GBP/USD 1.5678";
			send(soge, priceUpdate2);
			Utils.sleep(10);
			checkLastReceivedMessage(trader1, priceUpdate2);
			checkLastReceivedMessage(trader2, priceUpdate2);

			Utils.sleep(40);

			bus.unregisterListener(trader1);

			String priceUpdate3 = "Price updated: USD/JPY 145.32";
			send(hsbc, priceUpdate3);
			Utils.sleep(10);
			checkLastReceivedMessage(trader1, priceUpdate2);
			checkLastReceivedMessage(trader2, priceUpdate3);

			Utils.sleep(1000);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		bus.setRunning(false);
	}

	private void checkLastSentMessage(Bank bank, String priceUpdate) throws JMSException {
		Message lastSentMessage = bank.getLastSentMessage();
		assert lastSentMessage instanceof TextMessage;
		assert priceUpdate.equals(((TextMessage) lastSentMessage).getText());
	}

	private void checkLastReceivedMessage(Trader trader, String priceUpdate) throws JMSException {
		Message lastReceivedMessage = trader.getLastReceivedMessage();
		assert lastReceivedMessage instanceof TextMessage;
		assert priceUpdate.equals(((TextMessage) lastReceivedMessage).getText());
	}

	private static void send(Bank bank, String text) {
		try {
			bank.send(new TextMessageImpl(text));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
