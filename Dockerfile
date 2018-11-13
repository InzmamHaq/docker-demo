FROM openjdk:8-jre-slim

# Add the jar with all the dependencies
# Maven creates container-test.jar in the target folder of my workspace.
# We need this in some location - say - /usr/share/tag folder of the container

RUN mkdir -p /usr/share/tag/outputfolder

COPY  target /usr/share/tag/target
COPY  TestNGXML.xml /usr/share/tag/TestNGXML.xml
COPY  InputFolder /usr/share/tag/InputFolder



# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# SELENIUM_HUB = selenium hub hostname / ipaddress


# Command line to execute the test
#ENTRYPOINT ["/usr/bin/java", "-cp", "/usr/share/tag/target/jars/*:/usr/share/tag/target/classes", "-DseleniumHubHost=$SELENIUM_HUB", "-Dbrowser=$BROWSER", "org.testng.TestNG", "/usr/share/tag/TestNGXML.xml"]

ENTRYPOINT /usr/bin/java -cp /usr/share/tag/target/jars/*:/usr/share/tag/target/classes -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER -Dinputfolder=/usr/share/tag/InputFolder org.testng.TestNG /usr/share/tag/$MODULE
