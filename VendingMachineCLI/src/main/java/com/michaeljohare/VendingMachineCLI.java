package com.michaeljohare;

import com.michaeljohare.view.Menu;
import com.michaeljohare.view.ProductDisplay;
import com.michaeljohare.view.PurchaseMenu;

import java.io.File;

public class VendingMachineCLI {
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };


	private Menu menu;
	private ProductDisplay productDisplay;
	private File inventory = new File("vendingmachine.csv");
	private PurchaseMenu purchaseMenu;
	private FeedMoney feedMoney;
	private SelectProduct selectProduct;
	private FinishTransaction finishTransaction;
	private int[] quantity = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };

	public VendingMachineCLI(Menu menu, ProductDisplay productDisplay, FeedMoney feedMoney, SelectProduct selectProduct,
							 FinishTransaction finishTransaction, PurchaseMenu purchaseMenu) {
		this.menu = menu;
		this.productDisplay = productDisplay;
		this.feedMoney = feedMoney;
		this.selectProduct = selectProduct;
		this.finishTransaction = finishTransaction;
		this.purchaseMenu = purchaseMenu;
	}

	public void run() {
		productDisplay.setInventory(inventory);
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			double placeholderPrice  = 0.0;

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				productDisplay.displayItems(quantity);
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				boolean isValidInput = false;
				while (!isValidInput) {
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {

						String moneyProvided = feedMoney.feedMoneyMessage();
						feedMoney.feedMoney(placeholderPrice, feedMoney, moneyProvided);
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

						productDisplay.displayItems(quantity);
						Item userSelection = selectProduct.selectProduct(productDisplay);
						placeholderPrice = selectProduct.attemptPurchase(userSelection, feedMoney, productDisplay,
								 quantity, placeholderPrice);
					}
					purchaseMenu.purchaseMenu(feedMoney, placeholderPrice);
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

						finishTransaction.finishTransaction(placeholderPrice, feedMoney);
						finishTransaction.getMessage(placeholderPrice, feedMoney);
						feedMoney.finishTransaction();
						isValidInput = true;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		ProductDisplay productDisplay = new ProductDisplay();
		PurchaseMenu purchaseMenu = new PurchaseMenu();
		FeedMoney feedMoney = new FeedMoney();
		SelectProduct selectProduct = new SelectProduct();
		FinishTransaction finishTransaction = new FinishTransaction();
		VendingMachineCLI cli = new VendingMachineCLI(menu, productDisplay, feedMoney, selectProduct, finishTransaction,
				purchaseMenu);
		cli.run();
	}

}
