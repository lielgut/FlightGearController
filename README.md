# FlightGear Remote Control App

![forthebadge](https://img.shields.io/badge/Made%20with-Kotlin-orange)

## About

This is my Android project for my advanced programming 2 course. This is an Android application written with Kotlin, to remote control a plane in FlightGear using a virtual joystick for changing the aileron and elevator, and seekbars for changing the throttle and rudder.


## Project Files
The project files include the following:
- The <b>View</b> package includes the MainActivity class, as well as custom UI elements that are used - a joystick and a vertical seekbar.
- The <b>Model</b> folder includes the Model object (which is a singleton) that saves the throttle, rudder, aileron and elevator, as well as the TcpClient class which is responsible for connecting to the server.
- The <b>ViewModel</b> folder includes ControllerViewModel class which extends the ViewModel class and is responsible for the represenation logic.

<img src="diagram.svg" alt="diagram" height="400">

## Dependencies
- [Android Studio](https://developer.android.com/studio) is required for opening the project.
- Minimal Android version for running the app is Android 5.0 (API Level 21).
- [FlightGear](https://www.flightgear.org/) needs to be installed on your computer in order to run the FlightGear server.

## Installation

In order to download the project run the following command:
```
git clone https://github.com/lielgut/FlightGearController.git
```
then open the project with Android Studio, and use your phone or the Android Studio emulator to run the app.

## Running & Usage

Open FlightGear and under Settings > Additional Settings enter the following settings:
```
--telnet=socket,in,10,<IP>,<PORT>,tcp
```
with \<IP> being the local network IP of your computer running FlightGear, and \<PORT> being a valid port number of your choice.
Press "fly" to run the FlightGear simulator and then autostart the plane.
Enter the port and IP number in the app and press "connect".
If connection is succesful you should be able to control the plane.
Use the left seekbar to control the throttle, the bottom seekabr to control the rudder and the joystick to change the elevator and throttle.


## Additional information
- [video]()
