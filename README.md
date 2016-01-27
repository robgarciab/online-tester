# Online tester (Spring Boot + AngularJS Example)

[![Build Status](https://travis-ci.org/robgarciab/online-tester.svg?branch=master)](https://travis-ci.org/robgarciab/online-tester)

Basic Java project to show how to integrate an Spring Boot back-end application with an AngularJS front-end. This project is intended to be used as a first template for developers who wants to start writing single page applications and don´t want to deal yet with a task runner tool like [Grunt] or [Gulp].  

###Technology stack:
  - Spring Boot
  - Spring Framework
  - JPA (with Hibernate provider)
  - AngularJS
  - Bootstrap
  - Jasmine
  - TestNG
  - Maven

The project also includes TravisCI and Cloudfoundry config files (Cloudfoundry deployment step was commented in .travis.yml file to prevent memory consumption in my pivotal account).

###Maven plugins:
  - Wro4j: This Maven pluggin is used to optimize javascript and css code. Hardcore javascript developers will prefer using a task runner but again for this project I want to avoid using it to reduce the complexity.
  - Jasmine: This Maven plugin make it possible to run Jasmine tests as part of Maven's test phase.

###Maven profiles:
  - Local: To be able to debug javascript code in local environment. The local profiles imports the orginal javascript and css files.
  - Test: This profile imports the optimized scripts. Same behavior must be used in all shared environments: Test, UAT, Stagging, Prod.

###Authentication:
There are different options to add security to a single page application. For this project I've chosen token based authentication described in this great Spring tutorial: [Spring Security and Angular JS].

###Next steps:
  - Take a look to [jhipster]. It's a very useful code generation tool which allows you to create single page applications.

[Spring Security and Angular JS]: <https://spring.io/guides/tutorials/spring-security-and-angular-js/>
[Grunt]: <http://gruntjs.com/>
[Gulp]: <http://gulpjs.com/>
[jhispter]: <https://jhipster.github.io/>