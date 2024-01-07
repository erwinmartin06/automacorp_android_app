# Automacorp Android App - Erwin MARTIN & Céline NI

This Git repository contains the Automacorp Android App developped for a programming course.
The goal was to implement the backend of an application managing buildings, rooms, windows, heaters and temperature sensors.
All of this can, for example, serve as a simulation to understand how to efficiently use energy during winters.

# Instructions to access the backend

The backend can be downloaded [here](https://github.com/erwinmartin06/automacorp) on Github.
To access the backend with swagger interface, you can connect locally after starting the Automacorp Application on your computer [here](http://localhost:8080/swagger-ui/index.html).<br>
Since the backend has been deployed in clever cloud you can also access it [here](http://automacorp-erwin-martin.cleverapps.io/swagger-ui/index.html). <br> <br>

The logins to access the data and the [H2 console](http://localhost:8080/console) (it can be access only locally) are:
<br>Username: <b>user</b> ; Password: <b>theSecurePassword</b> -> Can access anything except the console and the path api/admin
<br>Username: <b>Erwin</b> ; Password: <b>theSecurePassword</b> -> Can access anything

# App description

The Automacorp Android App has the following functionalities:
- Search for a building by name and access all the rooms of that building (Home page)
- See all the buildings (Home page)
- Display all the rooms (Menu -> All rooms)
- When clicking on a room, all the details are displayed (room name, current temperature, target temperature and the windows/heaters of that room with there status and names)
- On the room details page, it is possible to change the name or the target temperature of the room, then send it to the server with the save button
- Access dev-mind website or send an email