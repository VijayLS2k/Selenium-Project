import { chromium, Browser, Page } from "playwright";

export class BrowserManager {
  static browser: Browser;
  static page: Page;

  static async start() {
    this.browser = await chromium.launch({
      headless: false,
      slowMo: 300
    });
    const context = await this.browser.newContext();
    this.page = await context.newPage();
  }

  static async stop() {
    await this.browser.close();
  }
}
