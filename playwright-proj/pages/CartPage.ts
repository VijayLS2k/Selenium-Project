import { Page } from "playwright";

export class CartPage {
  constructor(private page: Page) {}

  async checkout() {
    await this.page.click("#checkout");
  }
}
