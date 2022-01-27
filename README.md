# Chalkboard Birthdays App Challange

This is an app that utilises a web api to provide the user an ordered list of birthdays youngest to oldest

## How to install and run the project

Application was developed in Android Studio
Application will only work on Android devices
To run application in simulator clone repo, build and run in Android Studio

## Why I made the decisions I did

I chose to build the application with MVVM as it is more suited for the app.\
I also made the technical decision to utilise Retrofit for the API and almost all of the jectpack architecture elements.\
to provide a dark mode , you just need to change the colors in the corresponding file

Some of the trade offs I made were not building the application for Tablets. I felt like the design didn't work well with the layout of Tablets so chose to only develop for Android normal phones.
I also did not incorporate local storage of the data once it had been recieved meaning that data had to be loaded each time the app loaded. This was a small thing as the request in optimal network conditions doesn't take long but makes the app unusable with no network.

## If I had more time

If I had more time I would have added the ability of local storage either with user defaults or core data so the user could use the app without a network once the data had been picked up the first time and then only checked the api for delta changes.
This would have also allowed for the ability of adding new data into the app directly by the user which would become a core part of the user experience and allow the app to be used in isolation without the API
I would have also added the ability of the intial icons to be replaced with images if availble from the API allowing the user to see the person whos birthday is being listed
I would also add more complicated Tests but this is a pretty small project

## Contact Details

Github Profile - https://github.com/javabbt \
LinkedIn Profile -https://www.linkedin.com/in/yannick-nyami-98b51a124 \
Email - ngapmennyamiyannickloi@gmail.com \
Mobile number - available on request
