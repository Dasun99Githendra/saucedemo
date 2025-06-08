## Saucedemo Automation Framework

UI Test Automation Framework developed for https://www.saucedemo.com using Java, Selenium WebDriver, TestNG, Page Object Model (POM) design pattern. 

-----------------------------------------------------------------------------------
## Setup Instructions 

- Java JDK (version 11 or later) 
- Maven
- TestNG
- Chrome browser
- Internet connection (for WebDriverManager)

----------------------------------------------------------------------------------
## Setup Instructions

1. Install Java JDK (version 11 or later)
	- After installation, verify with: java -version

2. Install Apache Maven
	- Add Maven to system environment variables (on Windows or Linux/macOS).
	- Verify it’s working: mvn -v

3. Clone or Download the Project
	- If using Git: 
		git clone https://github.com/your-username/saucedemo-automation.git
		cd saucedemo-automation
	- If not using Git, can download the ZIP from GitHub and extract it.

4. Import Project into IDE
	- You can use:
		* IntelliJ IDEA
		* Eclipse IDE
		* VS Code (with Java plugins)
	- Open it as a Maven Project so dependencies will automatically sync.

5. Install Google Chrome
	- Make sure Google Chrome is installed and up-to-date.
	- This project uses ChromeDriver to run tests on Chrome.

6. Install ChromeDriver Automatically (Handled by Code)
	- This project uses WebDriverManager, which:
		* Detects Chrome version
		* Downloads the matching driver
		* Manages it automatically in the background
	- Can see it in the TestBase.java class: 
		WebDriverManager.chromedriver().setup();

7. Build and Resolve Dependencies
	- Once all setup is done, open a terminal inside the project folder and run:
		mvn clean install
	- This will: 
		* Compile the code
		* Download and resolve all dependencies (from pom.xml)
		* Run a default build lifecycle

 8. Verify Folder Structure
		src/main/java           # Page objects & utilities
		src/test/java           # Test cases
		screenshots   		# Screenshots auto-saved here
		testng.xml              # Entry point for all test executions
		pom.xml                 # Maven config with dependencies


-------------------------------------------------------------------------------------
## Included Test Scenarios

1.LoginTest - Validates successful login and inventory visibility.
2.ProductSearchTest - Searches product, validates results, and navigates back.
3.SortingTest - Sorts by price and verifies descending order.
4.CheckoutFlowTest - Executes end-to-end checkout with validation & summary.

--------------------------------------------------------------------------------------
## Screenshots

All screenshots are auto-saved during test execution under:
	- screenshots/<TestClass>/<yyyy-MM-dd>/screenshots/<step_name>_<HH-mm-ss>.png

---------------------------------------------------------------------------------------
## Reports

TestNG reports are generated under:
	- /test-output/index.html
---------------------------------------------------------------------------------------
## Dependencies

All dependencies are managed through Maven. Important ones include:
	1.Selenium Java - Browser automation
	2.TestNG - Test execution framework
	3.WebDriverManager - Auto-handles WebDriver binaries
	4.Apache Commons IO - File operations for screenshots

	