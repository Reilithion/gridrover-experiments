GridRover is a game designed to teach programming skills.

-- Building --

To build GridRover using the included waf tool, you will need Python 2.x
installed on your system.  Depending on what form of GridRover you want
and what OS you use, there are a few different ways to build it.
Make sure you have the build-requirements met, or waf will complain.

Linux/Unix
	To build the .jar file:
		Make the root directory of the project your working directory
		Type "./waf configure" (waf complains if it can't find javac)
		Type "./waf"
		The program is now located at ./_build_/default/gridrover.jar
	To build the native executable:
		Make the root directory of the project your working directory.
		Type "./waf configure --native" (waf complains without gcj)
		Type "./waf"
		The program is now located at ./_build_/default/gridrover

Windows
	Get to a command prompt
	Make sure the directory that has python.exe is in your PATH *
	Make your working directory the top directory of the project
	Type "python waf configure" (waf complains if it can't find javac) **
	Type "python waf"
	The program is now located at .\_build_\default\gridrover.jar
	
	*  This step is optional, so long as you're willing to type the whole
	   path to python.exe every time you use it.
	** If waf can't find javac.exe, find out where your JDK is installed and
	   set the JAVA_HOME environment variable, like so:
	   set JAVA_HOME=C:\Program Files\Java\jdk1.6.0_11\
	   (Replace that path with the one to your JDK) Then run waf configure
	   again as shown above.

Mac OS X
	Honestly, I don't know how you'd go about this, but it's probably
	similar to Linux/Unix.

You can probably also compile a native version of GridRover for Windows
if you have MinGW or Cygwin installed.  Just make sure gcj is floating around
somewhere on your system where waf can find it and try a command like
"python waf configure --native".  If waf complains, it's probably because
it can't find gcj, or possibly your JDK.

-- Credits --

GridRover was written by "Lucas" Adam M. Paul <reilithion@gmail.com>
Much help was generously contributed by Ackypi,
and Commissioner.moo <commissioner.moo@gmail.com>.

Special thanks are due to the University of Utah ( http://www.utah.edu ) for
the original idea of a rover simulator and making a program to be a player.
It was their Cassini rover simulation that inspired this project and on which
GridRover is based.  In particular, I owe thanks to the following people at
the University:
Robert Kessler - Professor in charge of HSCI, where I first used Cassini
Stan Shebs - original author of Cassini
Eric Muehle - FROBS and forward-chaining rule system
Eric Eide - Cassini's keeper for oh-so-many years

-- License Information --

GridRover is licensed under the GPL 3.0.  The full text of this license
can be found in the file LICENSE.TXT in the top directory of the source
tree.
