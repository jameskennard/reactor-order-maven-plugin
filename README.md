Reactor Order
========

Prints the Reactor Build Order using the projects artifactId. By default maven builds the reactor order by using the names of the projects (<names/> tag).
Also allows the reactor order to be printed even if quiet mode (-q) is used. This can be helpfull when you want to easilly parse the output to get the projects

In order to use it 
1. Clone this repo
2. Do a mvn install
3. Run mvn org.lytsiware:reactor-order-maven-plugin:print

Optionally can be used the following parameters:<p>
  -Ddelimiter="x", where x a string that will be used as a delimeter between the projects in the output string (default is \r\n)<p>
  -Decho=b, where b is true or false. If true the output will always be printed, even in quiet mode. If false the output will follow the mavens logging rules. (
default is true)


