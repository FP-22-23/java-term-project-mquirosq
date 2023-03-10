# Project Second Term Programming Fundamentals (2022/2023)
Author: María Quirós Quiroga  uvus: fgb3726

## Structure of the folders:

* **/src**: Directory with all the code
  * **fp.app**: Package with the classes in the project
    * **fp.app.test**: Package with the test classes of the project.
  * **fp.utils**:  Packages with utility classes. 
* **/data**: Contains the project data set.
    * **app_data.csv**: csv file with the data of the apps in the play store.
    
## Structure of the *data set*

The original data set "Play Store App Analysis" can be found in the URL: [http://kaggle.com/adarshpawar/play-store-app-analysis](http://kaggle.com/adarshpawar/play-store-app-analysis). Originally it had 13 columns. Each row contains the data of app in the Play Store. Our final data set has 12 columns, 9 taken from the original data set and 3 generated randomly. The columns of the data set are:

* **name**: of type string, indicates the name of the app.
* **rating**: of type double, indicates the rating of the app. Takes values from 0 to 5, both included.
* **reviews**: of type integer, contains the number of reviews the app has.
* **size**: of type string, contains the memory space needed to install the app.
* **price**: of type double, indicates the price of the app.
* **restriction**: of type string, indicates the age restriction of the app. Can take the values: Everyone, Teen, Mature and Adult.
* **category**: of type string, contains the category of the app.
* **genres**: of type string, contains the genres of the app.
* **last updated**: of type date, contains the date of the last update made to the app.
* **age**: of type integer, contains the target age for the users of the app.
* **nationality**: of type string, contains the target nationality for users on the app.
* **gender**: of type string contains the target gender for users on the app.


## Implemented types:

The types implemented in this project are:

### Base type - App
Represents an specific app in the play store.

**Properties**:

- _name_, of type _String_, consultable and modifiable. Indicates the name of the app.
- _rating_, of type _Double_, consultable and modifiable. Indicates the rating of the app in the Play Store. Takes values form 0 to 5, both included.
- _reviews_, of type _Integer_, consultable and modifiable. Indicates the number of reviews that the app has.
- _size_, of type _Double_, consultable and modifiable. Contains the  memory space needed to install the app.
- _price_, of type _Double_, consultable and modifiable. Contains the price of the app.
- _restriction_, of type _Restriction_, consultable and modifiable. Contains the age restriction of the app. Can take the values: EVERYONE, TEEN, MATURE or ADULT.
- _category_, of type _Set\<String\>_, consultable. Set of tags of the app (Contains data from the category and genres columns).
- _lastUpdate_, of type _LocalDate_, consultable and modifiable. Date of the last update made to the app.
-_targetUser_, of type _User_, consultable. Contains data about the target user.

**Derived properties**
- _recommended_, of type _Rec_, consultable.  Recommended takes a value that can be LOW, MID, HIGH or VERY_HIGH. It represents the level of recommendation of the app. The more reviews and rating, the more recommended it is, the lowest the number of reviews and the rating, the least recommended:
  - VERY_HIGH: reviews should be more than 10000 and rating more than 4
	- HIGH: rating should be over 4 independent of number of reviews
	- MID: more than 100000 and rating over 3 or more than 1000 reviews and rating over 2.5
	- LOW: any other result.
- _premium_, of type _Boolean_, consultable. Indicates if you have to pay for the app:
	- True: The app must be purchased.
  - False: The app is free to use.

**Constructors**: 

- C1: Contains a parameter for each basic property of the type.
- C2: Creates an object of type ``App`` from the parameters: ``String n, Double p, Restrictions res``. The rest take null, 0  or empty values.

**Restrictions**:
 
- R1: The rating must be in [0,5].
- R2: The name of the app can't be null.
- R3: The price of the app can't be negative.
- R4: The size of the app can't be negative.
- R5: The number of reviews can't be negative.
- R6: The date can't be after the actual date.

**Equality criteria**: Two apps are equal if the names, the prices and the restriction are the same.

**Natural order**: They will be ordered by their names, then their price and finally their restriction.

**String representation**: String representation, as: Name (category): rating stars for reviews reviews. It is a premium app: premium (price), restriction. Recommended: recommended.


**Other operations**:

- _void addCategory(String s)_: Adds a new String element (s) to the category set.

#### Auxiliary types

**Rec**:

Enum. Contains the values: LOW, MID, HIGH and VERY_HIGH.

**Restrictions**: 

Enum. Contains the values: EVERYONE, TEEN, MATURE and ADULT.

**Gender**:

Enum. Contains the values: MALE, FEMALE, OTHER.

**User**:

#####Properties:

* **gender**: of type Gender contains the target gender for users on the app. Can take the values: MALE, FEMALE, OTHER.
* **age**: of type Integer, contains the target age for the users of the app.
* **nationality**: of type string, contains the target nationality for users on the app.

#####Restrictions:

- R1: Age can't be negative.

#####String representation:
String representation, as: User [gender = gender value, age= age value, nationality = "nationality value"]
