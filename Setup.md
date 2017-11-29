#Steps to deploy project on Openshift container platform

Step 1: Install OC client tool from below URL

https://docs.openshift.com/container-platform/3.4/cli_reference/get_started_cli.html

Step 2: Login to openshift using token or username/password

oc login https://openshift-master.ocp-1.cloud.iairgroup.com --token=<Get your token>

Step 3: Create a Project

oc new-project spring-boot-sample

Step 4: Create new application ( Installs image and deploy application customer from git repository)

oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/amitwidhani/Customer

Step 5: List services in the project

oc get services

Step 6: Expose Customer Service externally(i.e. creates route)

oc expose service customer

Step 7: Create booking application ( Installs image if not exists and deploy application customer from git repository)

oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~https://github.com/amitwidhani/Booking

Step 8: List services in the project

oc get services

Step 9: Expose Booking Service externally(i.e. creates route)

oc expose service booking

Step 10: Test Customer and Booking endpoints using route urls

Example: 

http://customer-spring-boot-sample.apps.ocp-1.cloud.iairgroup.com/customers

http://booking-spring-boot-sample.apps.ocp-1.cloud.iairgroup.com/bookings

