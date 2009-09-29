GridRover is a game designed to teach programming skills.

-- Building --

There are two supported build systems for GridRover.  Ant and Waf.  In most
cases, they should produce identical results.  If you have problems with one
it might pay off to try the other.

To build GridRover, you will need the Java JDK version 5 or greater.

To build GridRover using Ant, you will need Ant.  From the GridRover root
directory, type "ant" (without the quotes, of course).  Ant should put the
resulting .jar file in the dist subdirectory.  This section is small not because
building with ant is necessarily simpler than building with waf, but because
thorough testing has not been done with ant yet.  A different command might be
needed under Windows, for instance.

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

-- Running --

In order to run GridRover, Java must be able to find the Apache Commons Digester
(and its dependencies) in your classpath.  This may make it tricky to run
GridRover from, say, the commandline.  This is one way known to work on
GNU/Linux:

java -classpath .:*:/usr/share/java/* gridrover.GridRover

This assumes that the Apache Commons Digester and its dependencies are all
found in /usr/share/java/ and that GridRover.jar is in the current directory.
Figuring out how to start up GridRover on your own system should be a matter of
replacing my directory structure with yours.

-- Credits --

GridRover was written by "Lucas" Adam M. Paul <reilithion@gmail.com>
Much help was generously contributed by:
- Ackypi
- Commissioner.moo <commissioner.moo@gmail.com>
- Karasu <kino.ichigo@gmail.com> (contributed to the data files; Thanks!)

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
