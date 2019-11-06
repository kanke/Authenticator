# Authenticator

<img src="https://imgur.com/85K61bJ.png" width="300" height="150" />

#  :bowtie: About this application #
A simple RESTful API that provides the user with the functionality of authentication. The API is easy to understand and integrate with. It uses Dropwizard and JWTokens for 
Authentication and Authorization.
 
##  To run this project ##

> :computer: -  Clone to computer

>  🧭 -  Navigate to root project directory of project i.e `cd /{path-to-project}`

> 🧹 -  Run `mvn clean install`

> :package: - Run  `mvn package`

> :runner: -  Run `java -jar target/Authenticator-1.0-SNAPSHOT.jar server`

##  Sample usages ##

###  Using MAC OS Terminal ###

> cd `/Users/kanke/Documents/authenticator`

> `java -jar target/Authenticator-1.0-SNAPSHOT.jar server`

![Imgur](https://imgur.com/lwzeRu8.png)


![Imgur](https://imgur.com/sJql3PR.png)


![Imgur](https://imgur.com/FF8AC6N.png)


![Imgur](https://imgur.com/keq3oQf.png)


![Imgur](https://imgur.com/reioWMS.png)


![Imgur](https://imgur.com/GNiQrZ9.png)


##  Original Problem Statement ##

1. Design and implement a simple API that provides the user with the functionality of authentication.
2. The API should be easy to understand and integrate with. RESTful is a welcomed approach.
3. The language must be Java and you can use any framework/libraries such as Spring Boot, Dropwizard, Jackson, etc.
4. Don’t worry about persistence as data can be handled solely in memory.

##  Assumptions ##


##  Future Implementations ##
1. Version API - Format versions should go in headers—Accept-Version and Content-Version—in the request and response
2. Containerised application - Using maven plugin or docker file
3. Access policies - Restrictions from certain networks, rate limits, guidance of caching of API call content
   etcetera
4. Add terms & conditions and legal obligations
