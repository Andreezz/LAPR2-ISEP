# OO Analysis #

## Rationale to identify domain conceptual classes ##

### _Conceptual Class Category List_ ###

**Business Transactions**

* Vaccine

---

**Transaction Line Items**

* Vaccine

---

**Roles of People or Organizations**

* Administrator
* Nurse
* Recepcionist
* Center Coordinator
* Employee
* SNS User

**Places**

* Vaccination Center
* Health Care Center

---

**Noteworthy Events**

* Vaccination Administration
* Adverse Reaction
* Vaccine Schedule
* Recovery period

---


**Physical Objects**

* Vaccine

---


**Descriptions of Things**

*  Type of Vaccine


---


**Organizations**

* DGS
* AGES
* ARS

---

**Documents mentioned/used to perform some work/**

* EU Covid Certificate
---



###**Rationale to identify associations between conceptual classes**

| Concept (A) 		|  Association   	|  Concept (B) |
|----------	   		|:-------------:		|------:       |
|Administrator|is an|Employee|
|Administrator|manages the core information of|Health Care Center   |
|Center Coordinator|is an|Employee|
|Center Coordinator|manages information of|Vaccination Center|
|Health Care Center|has|Vaccination Center|
|Health Care Center|emites|EU COVID Digital Certificate|
|Vaccination Center|applies|Type of Vaccine|
|Receptionist|is an|Employee|
|Receptionist|works at|Health Care Center|
|Receptionist|issues|EU COVID Digital Certificate|
|Receptionist|registers the arrivals of|SNS User|
|SNS User|requires|Type of Vaccine|
|SNS User|chooses|Vaccine Schedule|
|Nurse|is an|Employee
|Nurse|works at|Health Care Center
|Nurse|performs|Vaccine Administration|
|Vaccine Administration| given to a|SNS User
|Vaccine Administration| in a |Vaccine schedule|
|Vaccine Administration|has|Adverse Reactions|
|Vaccine Administration|of a|Vaccine|
|Vaccine|has|Type of Vaccine|






## Domain Model

**Do NOT forget to identify concepts atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![DM.svg](D:\DEMO\DOMAINMODELSPRINTA.svg)



