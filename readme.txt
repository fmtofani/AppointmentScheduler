Greetings examiner,

	I will use this text file to communicate my purpose for choosing the route I took on this project. I spent a lot of time on it and I am proud of the outcome. There is much that I plan to add on to this after I am finished with school. Surely this will become a template for CRUD projects that I have in the future. I thorougly enjoyed every aspect of this assigment, and for my level, I found it quite mind boggling at times. I am happy to say that I figured most of it out on my own by reading Oracle docs instead of reading a code repository and regurgitating it in my own fashion. I did need to do this a couple times though when I was really stuck. Nothing was used to the point that required citing the source.

	First of all, the instructions were very vague. I assume that is by design so that each student can interpret the instructions in a different way so that each project does not look like a carbon copy of the rest. Some fields of the database were left alone. The main one was appointment.contact. I was confused at what this represented so I left it out and deemed it unnecessary. I could interpret it as the main point of contact in my company which is also the user so it would be redundant, or being the main source of contact to the client such as email or phone which I already utilized the phone. I also wanted to be able to allow more locations to be added and I wanted to schedule the appointments in the location's timezone which would have been a lot of work. My mentor told me to just specify time in the user's default timezone which makes this task much easier, but would allow scheduling appointments beyond the business hours of the location which in some instances would have them being held in the middle of the night. I also assumed that each user could only add and edit appointments of their own. I could easily add a module to select a user to each appointment just like I did with the clients. My appointment scheduler was already full and it would have looked very sloppy had I enabled that function which is why I chose to leave it out. If it takes another revision to add this function, so be it. I assure you that its in my wheelhouse, 99% of the code is already in there, and it would only take an hour to tweak the GUI to implement it.

	Secondly, I took the liberty of adding several modules to the instructions, all without editing the database of course. I followed the rubric for the base, but I expanded on it for functionality, security, as well as a way to learn the basic mechanics. I created an admin group of features. You will find basic requirements in there such as the reporting objective. I also added a viewlog button, a client list module, as well as a user list module. I realize that having these features is not for every user of this software. I would modify the database and add a management table, as well as other subclasses that extend from an abstract user table so that the database stays in 4nf. Only users in this management table would have access to these admin controls. The password fields would also not be visible. They would be stored as a hash and only the users themselves could modify the password. Original passwords would be issued via email and would have to be changed upon first login. The admin controls would include a password reset method as well as the users. 


Without further ado, I grant you access to the information that you need to grade this project:

File Locations:
  Log- located in the root directory
  jar file- located in src/Util directory

Database Connection String
Server name: 52.206.157.109
Database name: U04xYO
Username: U04xYO
Password: 53688376998

User Access
  Username: test
  Password: test

Rubric A: View/LoginController
  Create a secure login form that translates into 2 languages

Rubric B: View/AddClientController
  Customer Module

Rubric C: View/AddAppointmentController
  Appointment Module

Rubric D: View/AppointmentController
  View calender by month and week

Rubric E: Model/AccessDB
  Adjust for timezones

Rubric F: View/LoginController, View/AddAppointmentController
  Exception Controls

Rubric G: --Util/MyCounter for interface, report1 method in Model/AccessDB for implementation
	  --View/HomeController and all instances of filling the columns in the tableview objects

Rubric H: View/LoginConroller
  Alert that appointment is in 15 minutes after login

Rubric I: View/ReportController
  Generate 3 reports

Rubric J: View/LoginController
  Log file which can be deleted and is located in the root directory when created or appended


Respectfully,
 Frank Michael Tofani
 ftofani@wgu.edu
 WGU C195