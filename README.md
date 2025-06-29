# Automation Practice
### ▶️ Running a specific test class

Use this command to run the ElementsTests class with the `baseUrl` parameter:
```bash

mvn test surefire:test -Dtest=ElementsTests -Dtestng.baseUrl=demoQa
```
### ▶️ Run all UI tests
```bash

mvn test surefire:test -Dsurefire.suiteXmlFiles=src/test/resources/regress_UI_tests.xml -Dtestng.baseUrl=demoQa
```

### ▶️ Run all API tests
```bash

mvn test surefire:test -Dsurefire.suiteXmlFiles=src/test/resources/regress_API_tests.xml -Dtestng.baseUrl=petStore
```

## Technologies
- Java
- Selenide
- TestNG
- Maven
- Allure