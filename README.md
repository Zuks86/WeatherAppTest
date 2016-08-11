## INTRODUCTION

The WeatherAppTest project is a project that implements the coding task specified in the "specification.pdf" file. The project is about reading weather forecast data initially from a static json file and then later from a 3rd party api and then displaying the data on a web page.

## INSTALLATION

The app has been implemented in two ways with the one requiring only a web browser to run and the latter requiring java 8 and maven to be installed:

1) To run the first option, which uses the static data from the specification, all that one has to do is download the zip file in the link [Zip-File](https://github.com/Zuks86/WeatherAppTest/blob/master/src/main/resources/static/no-server/static-weather-forecast.zip) and then unzip it and double click on the "index.html" file to run in on your browser of choice.

2) To run the second option, which contains both the static and the dynamic version, you need to have java-8 and maven installed in your device. You can find the installation instructions to these respectively at [Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) and [Maven](https://maven.apache.org/install.html)

Upon installing java-8 and maven then the following command will start the app in port 8011 (port can be changed in the /src/main/resources/application.yml file):<br />
*mvn spring-boot:run<br />*

In the case of the app started at port 8011 then opening a web browser and typing *localhost:8011* should take you to the home page of the app.

## API USED AND LIMITATIONS

The free weather api used is from [http://openweathermap.org/](http://openweathermap.org/). Here are a few things that I believe are necessary to note about the api:

1) The api returns data for 5 days (for our purpose) for a particular city. Each day has weather forecast at 3 hour intervals. For the sake of this coding task only one of the forecasts per day were selected. The rule used to determine which one to select was:<br />
a) Select the 12:00 (noon) entry.<br />
b) If it is not available select the entry closest to 12:00<br />
c) If there are two entries equally close to 12:00, eg 09:00 and 14:00 which are both 3 hours away from 12:00, then take the later entry which in this example would be the 15:00 entry

UPDATE:
Since sometimes the max and min temperatures for a particular time tend to be the same we are changing the rule that determines which max and min temperatures to use from the results:<br />
a) Get the highest max value from all forecasts for that day and set it is the max value.<br />
b) Get the lowest min value from all forecasts for that day and set it is the min value.

2) There is a limitation on the api since it is using the free version as opposed to the paid for version. It it stipulated that one must not send requests more then 1 time per 10 minutes from one device/one API key. Moreover best results are received when a person searches by ***city*** id as opposed to ***city name***. This means that our implementation which uses the city name may not always bring back the right response.

## USING THE DYNAMIC APP

After installing the dynamic app (the spring-boot one, or the one installed using maven) on landing on the home page you will be presented with two options:

a) STATIC FORECAST: This one is similar, pretty much identical, to the experience you get when using the "no server" version you get from the zip file. It is static and always shows the same data for the same city all the time.

b) DYNAMIC FORECAST: This one is dynamic in that it always you to choose not only the city but the country (for more accuracy) whose weather is desired. The browser further stores the last selected city and country so that when the app is visited again in will show the last selected country and city. Cape Town, South Africa (ZA) is the default city,country combo.

SCREENSHOTS:

Home Page:<br />
![Alt text](/src/main/resources/static/images/home-screenshot.png?raw=true "Home Page Desktop")

Dynamic Forecast Page:<br />
![Alt text](/src/main/resources/static/images/dynamic-screenshot.png?raw=true "Dynamic Forecast Desktop")

Changing City:<br />
![Alt text](/src/main/resources/static/images/change-city-screenshot.png?raw=true "Changing City Desktop")

MOBILE SCREEN SHOTS (Chrome Galaxy S5 simulator):

![Alt text](/src/main/resources/static/images/home-screenshot-mobile.png?raw=true "Home Page Mobile")
![Alt text](/src/main/resources/static/images/dynamic-screenshot-mobile.png?raw=true "Dynamic Forecast Mobile")
![Alt text](/src/main/resources/static/images/change-city-screenshot-mobile.png?raw=true "Changing City Mobile")
