import { test, expect } from "@playwright/test";
import { BrowserManager } from "../core/BrowserManager";
import { LoginPage } from "../pages/LoginPage";
import { InventoryPage } from "../pages/InventoryPage";
import { CartPage } from "../pages/CartPage";
import { CheckoutPage } from "../pages/CheckoutPage";

test.beforeAll(async () => {
  await BrowserManager.start();
});

test.afterAll(async () => {
  await BrowserManager.stop();
});

test("SauceDemo add to cart and checkout", async () => {
  const page = BrowserManager.page;

  const login = new LoginPage(page);
  const inventory = new InventoryPage(page);
  const cart = new CartPage(page);
  const checkout = new CheckoutPage(page);

  await login.open();
  await login.login("standard_user", "secret_sauce");

  await inventory.addItem("Sauce Labs Backpack");
  await inventory.addItem("Sauce Labs Bike Light");
  await inventory.goToCart();

  await cart.checkout();
  await checkout.fillDetails();
  await checkout.verifySuccess();

  await expect(
    page.locator(".complete-header")
  ).toHaveText("Thank you for your order!");
});
