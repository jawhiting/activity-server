[![Build Status](https://travis-ci.com/jawhiting/activity-server.svg?branch=master)](https://travis-ci.com/jawhiting/activity-server)
[![Coverage Status](https://coveralls.io/repos/github/jawhiting/activity-server/badge.svg?branch=master)](https://coveralls.io/github/jawhiting/activity-server?branch=master)

# activity-server

How to start the activity-server application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/activity-server-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
