## INTRODUCTION

The WeatherAppTest project is a project that implements the coding task specified in the "specification.pdf" file. The project is about reading weather forecast data initially from a static json file and then later from a 3rd party api and then displaying the data on a web page.

## INSTALLATION

The app has been implemented in two ways with the one requiring only a web browser to run and the latter requiring java 8 and maven to be installed:

1) To run the first option, which uses the static data from the specification, all that one has to do is download the zip file in the link [Zip-File](https://github.com/Zuks86/WeatherAppTest/blob/master/src/main/resources/static/no-server/static-weather-forecast.zip) and then unzip it and double click on the "index.html" file to run in on your browser of choice.

2) To run the second option, which contains both the static and the dynamic version, you need to have java-8 and maven installed in your device. You can find the installation instructions to these respectively at [Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) and [Maven](https://maven.apache.org/install.html)

Upon installing java-8 and maven then the following command will start the app in port 8011 (port can be changed in the /src/main/resources/application.yml file):<br />
*mvn spring-boot:run<br />*

In the case of the app started at port 8011 then opening a web browser and typing *localhost:8011* should take you to the home page of the app.

## API USED

The free weather api used is from [http://openweathermap.org/](http://openweathermap.org/). Here are a few things that I believe are necessary to note about the api:

1) The api returns data for 5 days (for our purpose) for a particular city. Each day has weather forecast at 3 hour intervals. For the sake of this coding task only one of the forecasts per day were selected. The rule used to determine which one to select was:<br />
a) Select the 12:00 (noon) entry.
b) If it is not available select the entry closest to 12:00
c) If there are two entries equally close to 12:00, eg 09:00 and 14:00 which are both 3 hours away from 12:00, then take the later entry which in this example would be the 15:00 entry
