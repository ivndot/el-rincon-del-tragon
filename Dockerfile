# Use the official Tomcat base image with JDK 8
FROM tomcat:8-jdk8-openjdk

# Copy your Java project files to the container
COPY ./ /usr/local/tomcat/el-rincon-del-tragon

# Install Ant (if not already available in the base image)
RUN apt-get update && apt-get install -y ant

# Set the working directory in the container
WORKDIR /usr/local/tomcat

# Build the Java project using Ant (assuming your build.xml is in the root of the project)
RUN cd el-rincon-del-tragon && ant war

# Copy the generated WAR file to Tomcat's webapps directory
RUN cp /usr/local/tomcat/el-rincon-del-tragon/dist/el-rincon-del-tragon.war /usr/local/tomcat/webapps/
