This is a project I am currently working on that is the frame work for an app that will allow students at Head Royce to have to all there school resources in one spot. Currently I am implementing a gps that will allow parents to communicate locations for our carpooling service but I am still establishing its framework as well. 


ConnectHRS
User Guide
─

Part 1: Server
Press control + ` to open console
Type “xcode-select --install” into the console (without the quotes) 
After the first download is finished, type “npm install” into the console
After this finishes downloading, type “node server.js” if everything worked correctly, you should see a message in the console that says “serving running on port 8080”


Next, go to 127.0.0.1:8080/register

Enter a username and the same password twice. If done correctly, you should be brought to the login page (if any fields are left blank you will not be able to submit)

Now enter your username and password into the login page, and you should be brought to the soon to be ConnectHRS home page
To stop running the server, press control + c while in the terminal. To start it again type “node server.js”
As long as you do not delete the database and the server is running, you can log in from any machine by simply replacing “127.0.0.1” with the machine running the server’s IP address. (you can find it press holding option and clicking on the WiFi icon)

Part 2: Android App
Follow steps 1-3 again, 
Download Android Studio from here: https://developer.android.com/studio
Open Android Studio
Click Open existing Android Studio Project
Open your OttoMehdiDesignProject from your desktop
Click Open
Click Project on the left hand side

Go this down folder dropdowns until you reach the folder holding MainActivity
 
Select Main activity
Click the hammer symbol at the top to build the project

Right click (two fingers on mac) on MainActivity and select         “ Run ‘MainActivity’ ”

Wait for emulator popup
Now if you want, you may click the registration button at the top left to check out the registration (although that feature is not implemented yet as we show you it in HTML client)
Put in your name and click the login button
You will be directed to the hub page 
Click the triangle at the top right to activate the drop down menu and select GPS
You are now at the gps page
You will now need to input your current location manually as emulators do not contain an actual gps (but on an actual phone you would not need to do this)
Click the three buttons on the bottom of the phone menu

You will be directed to a menu page 
Click on location in the on the left side
Input your current location in the google map
Now click set location in the bottom right
Now go back to the gps page
Click the “get current location button”
Your gps coordinates will show up in the middle if the gps is supported on your device
The first value is your latitude and the second is your longitude
Thanks for using and feel free to explore the other pages!


