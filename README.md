NAMESPACES:
As we reviewed in the last meeting the goal was to have as options all the classes
and properties of the X3ML file no matter what the prefix was. As far as this is 
concerned I've fixed the options to be as said with all the classes and properties 
for all the prefixes taking into account all the types of prefixes but not the option
of a prefix not existing(adding "ns1") for not known/seen prefixes.
The code handling the prefixes/namespaces is code.js(787-874) but the option for the 
prefix to not be found isn't evaluated correctly.
TRANSLATION:
I've included the backend code in the mainClasses folder in order to use it 
to change the classes needed from the user. I acquire correctly the .x3ml file
contents as string and the changes made the time the user downloads the final 
changes. However it is not final, the strings need more parsing in order to be
able to be changed into a new .x3ml file with all the changes made(I wasn't sure
if it was easier to do it with file handling or strings but I used strings since
I'm more familiar with string handling). In order to get the grasp of my try to 
do the translation review: code.js(357-417,787-874,988-1012),
mainClasses/mainTranslation.java, servlets/translationPost.java
