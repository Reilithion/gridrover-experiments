GridRover is a game designed to teach programming skills.

To build GridRover using the included waf tool, you will need Python 2.x
installed on your system.  Depending on what form of GridRover you want
and what OS you use, there are a few different ways to build it.
Make sure you have the build-requirements met, or waf will complain.

Linux/Unix
	To build the .jar file:
		Make the root directory of the project your working directory
		Type "./waf configure" (waf complains if you don't have javac)
		Type "./waf"
		The program is now located at ./_build_/default/experiment.jar
	To build the native executable:
		Make the root directory of the project your working directory.
		Type "./waf configure --native" (waf complains without gcj)
		Type "./waf"
		The program is now located at ./_build_/default/experiment

Windows
	Get to a command prompt
	Make sure the directory that has python.exe is in your PATH
	Make your working directory the top directory of the project
	Type "python waf configure" (waf complains if you don't have javac)
	Type "python waf"
	The program is now located at .\_build_\default\experiment.jar

Mac OS X
	Honestly, I don't know how you'd go about this, but it's probably
	similar to Linux/Unix.

You can probably also compile a native version of GridRover for Windows
if you have MinGW or Cygwin installed.  Just make sure gcj is floating around
somewhere on your system where waf can find it and try a command like
"python waf configure --native".  If waf complains, it's probably because
it can't find gcj.

GridRover is licensed under the GPL 3.0.  The full text of this license
can be found in the file LICENSE.txt in the top directory of the source
tree.
