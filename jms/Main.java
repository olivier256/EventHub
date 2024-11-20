package jms;

public class Main {
	public static void main(String[] args) {
		EventHub eventHub = new EventHub();

		Bank hsbc = new Bank("HSBC", eventHub);
		Bank soge = new Bank("SoGé", eventHub);

		Trader trader1 = new Trader("Trader1");
		Trader trader2 = new Trader("Trader2");

		eventHub.registerListener(trader1);
		eventHub.registerListener(trader2);

		hsbc.publishMessage("Price updated: EUR/USD 1.1234");
		soge.publishMessage("Price updated: GBP/USD 1.5678");

		eventHub.unregisterListener(trader1);

		hsbc.publishMessage("Price updated: USD/JPY 145.32");
	}
}
