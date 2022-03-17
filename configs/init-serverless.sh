#!/bin/sh
echo "Please Login to OCP using oc login ..... "  
echo "Make sure Openshift Serverless Operator is installed"
echo "Make sure knative-serving namespace is created and an instance is already provisioned"
echo "Press [Enter] key to resume..." 
read

oc new-project dev
oc apply -f https://raw.githubusercontent.com/osa-ora/UserAccountService/master/configs/db-secret.yaml
oc apply -f https://raw.githubusercontent.com/osa-ora/UserAccountService/master/configs/customer-db-deployment.yaml
oc apply -f https://raw.githubusercontent.com/osa-ora/UserAccountService/master/configs/customer-db-service.yaml
echo "Service 'Useraccounts-db' deployed successfully as ephemeral" 
echo "Login to user-accounts-db mysql pod and install the schema using:"
echo "mysql -u root"
echo "connect accounts"
curl https://raw.githubusercontent.com/osa-ora/UserAccountService/master/scripts/initial_schema.sql

oc apply -f https://raw.githubusercontent.com/osa-ora/UserAccountService/master/configs/image.yaml
oc apply -f https://raw.githubusercontent.com/osa-ora/UserAccountService/master/configs/build-config.yaml
oc apply -f https://raw.githubusercontent.com/osa-ora/UserAccountService/master/configs/useraccount-serverless.yaml
echo "Service 'UserAccountsService' deployed successfully as a serverless" 
echo "Completed!"
