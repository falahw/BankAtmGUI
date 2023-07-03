# BankAtmGUI
GUI version of falahw/BankAtm (with roles of admin  user)

## How-to-Use
- download & extract SimpleBankGUI.zip. You should find executable file 'BankAtmGUI.jar' & folder 'dbbank'
- open console/terminal in your pc or laptop
- locate this location of executable file 'BankAtmGUI.jar' & folder 'SimpleBankGUI' in your console/terminal. Both of them should be in the same folder
- run BankAtmGUI.jar by typing `java -jar BankAtmGUI.jar` you must run this in directory where 'BankAtmGUI.jar' & folder 'SimpleBankGUI' are located
- make sure you have Java installed in your pc or laptop (this app is built on top of Java 17)
- you should find a table contain pairs of user-id & password showed in the console/terminal
- user one of them to login & try app BankAtmGUI

## Tech
- BankAtmGUI is an app for console mode
- databases in app BankAtmGUI.jar are saved in files in folder 'dbbank'
- databases are saved in format of text file (not SQL) but still in RDB (Relational Database) model by using unique identifier like account number, user id, and transaction-id called 'sequence'
- calling join operation like in SQL is still possible eventhough it has to be manually coded in java
- LinkedList() is used as a medium to CRUD data
- LinkedList() is chosen because of its efficiency in indexing random access memory/data if compared to ArrayList()

## App Files
- BankAtmGUI.java --> work like a home page in a website. It will show registration form in the login applet and list of pair of users & passwords (in console/termina;) for clients to test the application
- BankLoginGUI.java --> checking validity of users login. If they are valid, users will be redirected to clients' dashboard with setup depends on their role ('admin' or 'customer')
- BankMenuAdminGUI.java --> open applet window for user with admin role and serve dashboard with setup for administrator
- BankMenuCustGUI.java --> open applet window for user with customer role and serve dashboard with setup for customer
- BankKudatGUI.java --> work as DAO file or repository that access database and give data needed by clients

## Note/WARNING!
>> if you want to edit database file, make sure to leave a space of empty row after editing the file
- if you miss the step above, new data will be created next to the last existing data, causing app BankAtmGUI has difficulty in reading the database
>> moving any files from its defined location will cause error because it makes the app's algorithm can not locate the app's files properly
- joni99, admin007, & cust00 are users who have many transaction records if you want to test feature 'transaction record'

## Unfinish work
- in folder dbbank/dbbank_userlog you will find files with format name 'userlog_domestic-[accNo]-[userId].txt'
- those files are useless at the moment
- they are intended to record transaction coming from other app BankAtmGUI.jar but this feature has not developed yet