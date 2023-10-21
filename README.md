# Mobile Testing Automation

This automation framework can be used in any mobile testing area

Tools Needed
---------------------------------------------

- IntelliJ (Download Community Edition): https://www.jetbrains.com/idea/download/
- Java 17
- Git: https://git-scm.com/downloads
- Appium Server: https://search.maven.org/search?q=g:io.appium%20AND%20a:java-client
- Android Studio  
  **Note:** After IntelliJ Installation when you open the project you should install some recommended plugins inside
  IntelliJ: Cucumber for Java, Gherkin.

Framework Structure
---------------------------------------------
Below is the framework structure explanation:

## Main Folder
Contains three main packages
### Core Package
Contains four packages
*	**“custom_exceptions”** support different custom exceptions that can be used instead of normal RuntimeExceptions
*	**“drivers_initializer”** have all the drivers logic and classes
*	**“errors_handlers”** support different custom error handlers that can be used instead of normal Exceptions
*	**“interfaces”** have the mobile driver and the implicit handler interfaces.

### Pages Package
Contains the page object packages and classes:
*	**CommonPO** this class has the definition for all the common locators
*	Package for every page that should have the below classes:
*	**Abstract Class** extends the CommonPO class, this class should have the abstract functions and the common locators between iOS and Android.
*	**Android Logic** Implementation Class which extends the Abstract Class and have the android logic implementation for the abstract functions and the android specific locators.
*	**iOS Logic** Implementation Class which extends the Abstract Class and have the iOS logic implementation for the abstract functions and the iOS specific locators.
*	**Note:** You can add react also if you have specific react implementation.

### Utils Package
Contain all the needed utilities.
* **“data_generator”** Package: data generator helper for creating random emails and phone numbers and get date if needed.
* **“readers”**, **“reading_helper”** Packages: have xml and excel readers ready to use if needed.
* **“rest_assured”** Package: Helper for all rest assured functionalities to be used with API integration whenever needed.
-----------
#### **“element_helpers”** package
* **“ActionHelper”** class: Contains Wrapped needed element actions functionalities.
* **“VerifyHelper”** class: Contains the verification needed wrapper functions.
* **“ScrollHelper”** class: Contains Wrapped needed scroll and swipe functionalities.
* **“TextHelper”** class: Contains Wrapped needed get element by text functionalities.
* **“WaitHelper”** class: Contains different type of waits wrapped functions.
-----------------
* **“PropertiesLoader”** class: Contains the properties reader implementation.
* **“Logger”** class: contain the logger implementation.

### Resources Package
*	**“builds” folder**: Put here the builds under test. “Android or iOS and you can also add folder for react.”
*	**“capabilities” folder**: add property file for every OS capabilities (Exact capability name should be provided).
*	**“config.properties”**: contains the different run configurations (Platform, capabilities location, etc.…)
## Test Folder
### Step Definition Package
*	Contain all the step definitions required for every feature file.
*	In the Step Definition Class You should create a constructor initializing the required pages needed for this step definition based on if condition to switch the pages initilializing Android or iOS according to the driver instance initiated based on the config.properties (This is shown in the example)
*	Contain “Hooks” class that is responsible for the setup and teardown methods.
### TestRunner Class
This class is responsible for getting the cucumber configurations and run specific features accordingly
### Resources Package
* **“Features Folder”**: Should have all the feature files you want to add in the project.
#### **“Junit-platform.properties”** contain all the cucumber configurations needed
* The configurations and the glue of the feature files.
* Reports configurations.

## How to Run the Features
*	You Can run the feature through IntelliJ by right click on any feature or scenario and run it directly.
*	You Can run all the features or filter by tags inside the **“Junit-platform.properties”** class using **“cucumber.filter.tags=”** then run the **“TestRunner”** class
*	You Can run using this command by also adding tags if needed for the run.

```
mvn clean install -Dgroups="tagName"
```

@tagName can be added above any feature or above any scenario in the feature file (Showed in Example.feature)  