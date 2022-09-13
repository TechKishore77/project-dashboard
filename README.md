# project-dashboard
project is aimed to create a reporting tool for construction related activities

frontend - has angular component
1. install node in your pc- after this when we give 'node -v' or 'npm -v' these should be resolved.
2. setup angular in global by running 'npm i -g @angular/cli@latest' command.
3. navigate inside the frontend->dashboard-> and here give 'npm i' to install the node_modules for the front end application.


backend - springboot component
1. built on gradle.
2. connecting to mysql database hosted on aws.
3. to connect to mysql database instance- we need to download mysql workbench and provide the hostname, username and password -> can be taken from application-dev.properties file.


deploy to AWS
1. will share aws credentials seperately.
2. loginto the portal and we need to go to 'AWS elastic bean', click upload and deploy -> navigate to the '*****.jar' file and select and submit. the JAR will gets deployed in sometime we can validate.
