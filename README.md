RMISimpleBankSystem


To run Java RMI server/client on ubuntu/linux, run the following on terminal:

cd '/directory' 

//compile .java files

javac BankServer.java

javac BankClient.java

javac BankAccount.java

javac BankInterface.java

javac DbConnection.java

rmic BankAccount

rmiregistry 2008

//run the server with mysql dependency

java -cp "/mysqldirectory/mysql-connector-java-5.1.23:."BankServer

//run the client

java BankClient


===================
