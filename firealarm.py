#!/usr/bin/python3

import firebase_admin
import gpiozero
from firebase_admin import messaging
from firebase_admin import credentials
from time import sleep
from time import strftime
from time import localtime

sensorPin = 14
BuzzerPin = 15
sensor = gpiozero.DigitalInputDevice(sensorPin)
buzzer = gpiozero.Buzzer(BuzzerPin)

cred = credentials.Certificate("./serviceAccountKey.json")
firebase_admin.initialize_app(cred)

def trigger_alarm ():
    message = messaging.Message(
    notification=messaging.Notification(
        title='Fire Detected',
        body='Fire Detected at Flame Sensor '+strftime('%H:%M:%S', localtime()),
    ),
    android=messaging.AndroidConfig(
        priority='high',
        ),
    topic='Firetest',
)
    response = messaging.send(message)
    print("Message Sent", response)

while True:

    sensor.wait_for_inactive()
    sleep(1)
    if(sensor.value == 0):
        print("Fire Detected "+strftime('%H:%M:%S', localtime()))
        trigger_alarm()
        buzzer.on()
    sensor.wait_for_active()
    print("Fire not detected ")
    buzzer.off()
