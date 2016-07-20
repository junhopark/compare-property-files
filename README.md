# Compare Property Files 

This is a template for a web application that uses embedded Jetty. This code was initially cloned from http://java.heroku.com/ (https://cloner.heroku.com/apps/template-java-embedded-jetty).

Upon cloning, the following updates were made:

## Running the application locally

First build with:

    $mvn clean install

Then run it with:

    $java -cp target/classes:target/dependency/* com.jpark.Main
