# 📘Automation Practice

This project demonstrates modern automation practices using the following technologies:

☕ Java – core language for writing tests

🌿 Selenide – concise and stable framework for UI testing

🧱 Selenoid – lightweight Selenium hub for running tests in Dockerized browsers

✅ TestNG – flexible testing framework with powerful annotations

📦 Maven – build and dependency management tool

📊 Allure – rich and interactive test reporting

🔍 Swagger / OpenAPI Generator – for generating typed API clients from specs

🌐 REST Assured – fluent Java DSL for testing REST APIs

## 📦 How to Install

Clone the project:

```bash
git clone git@github.com:stalker-detected/Automation-Practice.git
cd Automation-Practice
```
Make sure you have:

Java 17+

Maven

Selenoid

## ▶️ How to Run
### ⚙️ Running a specific test class

Use this command to run the ElementsTests class with the `baseUrl` parameter:
```bash
mvn test surefire:test -Dtest=ElementsTests -Dtestng.baseUrl=demoQa -Dtestng.browser=chrome
```
### ⚙️ Run all UI tests
```bash
mvn test surefire:test -Dsurefire.suiteXmlFiles=src/test/resources/regress_UI_tests.xml -Dtestng.baseUrl=demoQa -Dtestng.browser=chrome
```

### ⚙️ Run all API tests
```bash

mvn test surefire:test -Dsurefire.suiteXmlFiles=src/test/resources/regress_API_tests.xml -Dtestng.baseUrl=petStore
```
## ⚡ Features

### Parallel test execution

An example command was given above when running UI autotests. Using testng.xml, you can run autotests in parallel.

### Remote execution
Autotests can be run remotely using Selenoid.
```bash
mvn test surefire:test -Dsurefire.suiteXmlFiles=src/test/resources/regress_UI_tests.xml -Dtestng.baseUrl=demoQa -Dtestng.browser=chrome -Dtestng.driverType=remote
```
⚠️ Attention! For this to work, you need to specify the remote Selenoid URL in src/main/resources/settings.properties in remoteServerURL. 
Also, in utils/DriverManager.java, you need to specify the browser version that you have in the Docker image via setVersion.

### Multi-browser support
Currently, it is launched in these browsers.

| Browser  |  Local  | Remote  |
|----------|:-------:|:-------:|
| Chrome   |    ✅    |    ✅    |
| Firefox  |    ✅    |    ✅    |
| Edge     |    ✅    |    ✅    |

## 🛠Tech stack
- Java
- Selenide
- Selenoid
- TestNG
- Maven
- Allure
- Swagger/OpenAPI Generator
- REST Assured

## 🐞 Known Problems
### 📹 How to fix Selenoid video recording on Windows

If you are facing issues with video recording in Selenoid (e.g., video is not saved or missing), the problem might be caused by WSL2 limitations in Docker Desktop. To fix this:

#### ✅ Solution: Use Hyper-V Instead of WSL2

1. **Open Docker Desktop**  
   Go to **Settings → General**

2. **Disable WSL2 Engine**  
   Uncheck the option:
   > ❌ *Use the WSL 2 based engine*

3. **Enable Hyper-V Support**  
   Open the Windows Features dialog (`OptionalFeatures.exe`) and enable:
    - ✅ Hyper-V
    - ✅ Virtual Machine Platform
    - ✅ Containers

4. **Restart your machine**

5. **Restart Docker Desktop**

After doing this, video recording in Selenoid should work properly.

> 💡 Reason: The `selenoid/video-recorder` container relies on access to display and audio devices, which often fails in WSL2 environments due to limited hardware passthrough.

