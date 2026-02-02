import { Page } from "playwright";

export class CheckoutPage {
  constructor(private page: Page) {}

  async fillDetails() {
    await this.page.fill("#first-name", "Vijay");
    await this.page.fill("#last-name", "Tester");
    await this.page.fill("#postal-code", "600001");
    await this.page.click("#continue");
    await this.page.click("#finish");
  }

  async verifySuccess() {
    await this.page
      .locator("text=Thank you for your order!")
      .waitFor();
  }
}
