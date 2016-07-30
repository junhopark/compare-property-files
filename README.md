# Compare Property Files

This is a template for a web application that uses embedded Jetty. This code was initially cloned from http://java.heroku.com/ (https://cloner.heroku.com/apps/template-java-embedded-jetty).

Upon cloning, the following updates were made:

## Running the application locally

First build with:

    $mvn clean install

Then run it with:

    $java -cp target/classes:target/dependency/* com.jpark.Main

To hot deploy:
    $mvn install

## Notes on using IntelliJ IDEA

I'm currently using IntelliJ IDEA 2016.2 (Ultimate Edition).  To make this work with IntelliJ, follow these steps:

* After cloning, go to: File > New > Project from Existing Sources > Select the pom.xml file > Set 'Root directory' to the root of the project > Keep selecting Next with default options and close out of the modal.
* Go to: File > Project Structure > Project > Set 'Project language level' to the appropriate Java language level.
* While you're still in Project Structure, select Artifacts > set the 'Output directory' to: [PROJECT ROOT]/target/compare-property-files-1.0-SNAPSHOT > Check 'Build on make' > Click OK.
* Open Main.java > Right-click > Select Run 'Main.main()' or Debug 'Main.main()'.
* Open your browser and go to: http://localhost:8080