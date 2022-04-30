This repository is my IoT Fire Alarm project designed to run on a Raspberry Pi. It detects input from a flame sensor on GPIO Pin 14 and using Firebase Cloud Messaging sends a signal to any registered Android device with the app installed, and also sends a signal to a Buzzer on Pin 15. firealarmAndroidApp is an Android Studio project for the app, and firealarm.py is the Python script running on the Raspberry Pi.

To recreate this, you must create a project on the [Firebase Console](https://console.firebase.google.com/) and create a service account key by going to Settings > Service Accounts and clicking *Generate New Private Key*. Then you need to download the JSON file, rename it as serviceAccountKey.json, and place it in the same directory as firealarm.py. 

You must also add an Android app in the Firebase console and use the same package name/applicationId in the buiild.gradle of firealarmAndroidApp. You can then either use the FireBase assistant in Android Studio to register the app to your account, or manually add your google-services.json in the app directory.