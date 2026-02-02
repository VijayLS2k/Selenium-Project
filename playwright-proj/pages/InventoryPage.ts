import { Page } from "playwright";

export class InventoryPage {
  constructor(private page: Page) {}

  async addItem(itemName: string) {
    await this.page
      .locator(".inventory_item")
      .filter({ hasText: itemName })
      .locator("button")
      .click();
  }

  async goToCart() {
    await this.page.click(".shopping_cart_link");
  }
}
