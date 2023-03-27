# Supplementary Specification (FURPS+)

## Functionality
####Auditing
- _"(...) for any time interval on one day, the difference between the
         number of new clients arrival and the number of clients leaving the center every five-minute period
         is computed."_

- _"In the case of a working day, with a center open from 8 a.m. until 8 p.m., a list with
    144 integers is obtained, where a positive integer means that in such a five-minute slot more clients
                            arrive at the center for vaccination than clients leave with the vaccination process completed. A
                            negative integer means the opposite. "_


####Localization 

- _"The application must support, at least, the Portuguese and the English languages."_

####Email
- _"Both kinds of vaccination centers are characterized by a name, an address, a phone number, an e-mail address, a fax number, a website address(...)."_

####Help
- _"The worst-case time complexity analysis of the algorithms should be properly
    documented in the user manual of the application (in the annexes). The user manual must be
    delivered with the application."_


####Reporting
- _"(...) the application should send an SMS message when the vaccination event is scheduled and registered in
    the system."_
    
- _"The system should be able to notify the user that his/her recovery period has ended."_

- _"(..) any SNS user can request the issuance of the EU COVID Digital
    Certificate"_
    
- _"The Center Coordinator wants to (...) generate reports (...)."_

####Security
- _"All those who wish to use the
    application must be authenticated with a password holding seven alphanumeric characters,
    including three capital letters and two digits."_
- _" Only the nurses are allowed to access all user’s health data."_

## Usability 
####Accessibility
- _"(...) this application to be
          operated daily by SNS users, nurses, receptionists, etc. "_
- _"(...) uses the application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in
    the vaccination process."_
- _"Only the nurses are allowed to access all user’s health data."_
- _"Some users (e.g.: older ones) may want to go to a healthcare center to schedule the
    vaccine appointment with the help of a nurse. "_
####Consistency
- _"(...) will show the time interval, in such a day, when the
    vaccination center was less effective in responding."_


## Reliability
- _"Usually, the user that has arrived firstly will be the first one
  to be vaccinated (like a FIFO queue). However, sometimes, due to operational issues, that might not
  happen"_
- _"If the nurse identifies any
  adverse reactions during that recovery period, the nurse should record the adverse reactions in the
  system."_
- _"The Center Coordinator wants to monitor the vaccination process, to see
  statistics and charts, to evaluate the performance of the vaccination process, generate reports and
  analyze data from other centers, including data from law systems."_




## Performance
- _"(...)where a positive integer means that in such a five-minute slot more clients
  arrive at the center for vaccination than clients leave with the vaccination process completed. A
  negative integer means the opposite."_
- _"(...)whose sum of their entries is maximum. This will show the time interval, in such a day, when the
  vaccination center was less effective in responding."_



## Supportability
- _"(...)the application should be designed to easily support
  managing other future pandemic events requiring a massive vaccination of the population."_
- _"(...)should also be conceived having in mind that it can be further commercialized
  to other companies and/or organizations and/or healthcare systems besides DGS."_ 
- _"If the user authorizes the sending of the SMS, the
  application should send an SMS message when the vaccination event is scheduled and registered in
  the system."_
- _"The system should be able to notify
  (e.g.: SMS or email) the user that his/her recovery period has ended."_
- _"An Administrator is responsible for properly configuring and managing the core information (e.g.:
  type of vaccines, vaccines, vaccination centers, employees) required for this application to be
  operated daily by SNS users, nurses, receptionists, etc."_
- _"As the allocation of receptionists and nurses to
  vaccination centers might be complex, by now, the system might assume that receptionists and
  nurses can work on any vaccination center."_
- _"(...)the application should check the
  vaccination center capacity for that day/time and, if possible, confirm that the vaccination is
  scheduled and inform the user that (s)he should be at the selected vaccination center at the
  scheduled day and time."_
- _"If the information is
  correct, the receptionist acknowledges the system that the user is ready to take the vaccine. "_
- _"Any Administrator uses the
  application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in
  the vaccination process."_






## +


#### Development Tools

##### IDEA  
- _"(...) using the IntelliJ IDE or NetBeans."_

##### Graphical Interface
- _"The application graphical interface is to be developed in JavaFX 11."_

#### Images/Figures file format
- _"All the images/figures produced during the software development process should be recorded in SVG format."_


### Implementation Constraints

#### Implementation Languages

 - _"The application must be developed in Java language."_
 - _"The unit tests should be implemented using the JUnit 4 framework."_
 - _"The JaCoCo plugin should be used to generate the coverage report."_
 
#### Mandatory Standards

 - _"Adopt best practices for identifying requirements and for OO software analysis and design."_
 - _"Adopt recognized coding standards (e.g., CamelCase)."_
 - _"Use Javadoc to generate useful documentation for Java code."_
 - _"The development team must implement unit tests for all methods except methods that implement Input/Output operations."_
 - _"The application should use object serialization to ensure data persistence between two runs of the application."_
 - _"The application must support the English language only."_
 
#### Database Integrity

_"All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters,
including three capital letters and two digits. Only the specialist doctor is allowed to access all client data."_



