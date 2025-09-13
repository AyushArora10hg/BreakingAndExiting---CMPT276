<h1 align = "center"> Breaking and Exiting </h1>

## Overview
<p> 
<b>Robby Bobby</b> may be great at breaking into places, but breaking out is a different story. In one such heist, Robby robs a bank
stealthily, but gets stuck inside the bank vault, protected by high tech security measures and vigilant security guards. <br>
<b> Breaking and Exiting </b> is a 2D style retro aracde game following moments after Robby Booby breaks into a bank, but it clueless of
escaping it. The goal of the game is to safely escape the Bank premisises, tackling all obstacles and gathering all valuables that Robby 
clumsily dropped on his way out. The core concepts of the game are : <br>
<ul>
<li> Interactive hazards: security guards </li>
<li> Stationary hazards: tables, plants, and lasers </li>
<li> Rewards: cash and time-sensitive gems </li>
<li> The score is the amount of rewards player has collected </li>
</ul>
</p>

## Gameplay
<h3> Enemies </h3>
<p>
There are two types of enemies in the game: <br>
<b> Moving Enemies:</b> The guards, alerted by Robby's movement inside the Bank, will either follow Robby's movement all over the map, or patrol their respective stations. If they catch Robby, the game ends. <br>
<b> Fixed Enemies: </b> Lasers, which if tripped by Robby, will cause him to lose a part of his score. If the score falls below zero, the
game ends.
</p>
<h3> Rewards </h3>
<p>
There are two types of rewards in the game: <br>
<b> Regular Rewards:</b> The basic cold hard cash which stay on the map till Robby collects them. <br>
<b> Special Rewards:</b> Bonus rewards in the form of gems that randomly appear for a small interval of time.
</p>
<h3> Barriers </h3>
<p>
The heist takes place indoors, so the map is bordered with walls. There are other barriers like tables and plants that block Robby's 
movements.
</p>
<h3> The Map </h3>
The starting point is the vault inside the bank that Robby robbed. The exit is an exit door, which only allows Robby to escape once he has
collected enough rewards. Between these two points, enemies and barriers pose challenges for Robby's escape.
</p>

## Game Tutorial Video

<p>
  Video tutorial 2: https://youtu.be/pTJnpwt0L10?si=gNimBNyBmTrL1jh3<br><br>
  
  Video tutorial 1: https://youtu.be/u5victWB0Mk <br>
  Video Credit: Conor Benson<br>
  Song used in video: The Entertainer by Scott Joplin
</p>


## Pre-requisites
<p> For the user to effectively run the game, the following tools are required to be installed on their system: <br>
  <ul>
    <li> Java Development Kit (JDK) version 11 or higher. </li>
    <li> Apache Maven for build automation. </li>
    <li> Git for version control (to clone the repository).</li>
   </ul>
 </p>

## Directory Structure
<pre><code>
BreakingAndExiting/
├── src/
│   ├── main/
│   │   ├── java/                # Production code
│   │   ├── resources/           
│   ├── test/
│   │   ├── java/                # Unit and integration test code
│   │   ├── resources/           # Test-related resources
│   ├── Sounds/                  # Sound effects
│   ├── Sprites/                 # Background Images and element skins
├── pom.xml                      # Maven configuration

</code></pre>

## Maven Information
<p> The following maven commands are of utmost importance: <br>
  <ol>
    <li> <b> mvn clean:</b> Deletes the target directory and all the build artifacts contained within it. </li>
    <li> <b> mvn compile -DskipTests:</b> Compiles the Java source files located in the src/main/java directory </li>
    <li> <b> mvn install -DskipTests:</b> Compile the code and package the compiled code and other resources into a JAR artifact </li>
    <li> <b> mvn javafx:run</b> Executes the program </li>
   </ol>
  </p>

## Building the Project
<ol>
  <li>Clone the repository:
    <pre><code>git clone https://github.com/your-team-repo/BreakingAndExiting.git
</code></pre>
  </li>
  <li>Navigate to the project root directory and run Maven to build the project:
    <pre><code>mvn clean install -DskipTests</code></pre>
  </li>
  <li>The compiled files will be placed in the <code>target</code> directory.</li>
</ol>

## Building Executable JAR
<p> To build and run a jar, follow the steps mentioned below <br>
  <ol>
    <li>Navigate to correct directory: <pre><code>cd ~\CMPT276F24_group23\BreakingAndExiting </code></pre> </li>
    <li>To create an executable jar file, build the project:  <pre><code> mvn clean install -DskipTests </code></pre></li>
    <li>Run the executable:  <pre><code> java -jar target/BreakingAndExiting-1.0.jar </code></pre> </li>
  </ol>
  

## Building JavaDocs
  <ol>
  <li>Run the following to ensure project is up to date: <pre><code> mvn clean install -DskipTests</code></pre></li>
    <li>Navigate to correct directory: <pre><code>cd ~\CMPT276F24_group23\BreakingAndExiting </code></pre> </li>
    <li>To create a javaDoc jar file run:  <pre><code> mvn javadoc:javadoc </code></pre></li>
    <li>Extract the jar files contents with:  <pre><code> jar -xvf BreakingAndExiting-1.0-javadoc.jar </code></pre> </li>
    <li>All relevant javaDoc information resides in this jar, including index-all.html, which contains all javadoc info </li>
  </ol>

## Running the Game
<ol>
  <li>After building the project, execute the main application:
    <pre><code>mvn javafx:run</code></pre>
  </li>
  <li> Running the JAR Artifact:
    <pre><code>java -jar target/BreakingAndExiting-1.0.jar</code></pre>
  <li>The game will launch in fullscreen mode by default. Press <b>Escape</b> to toggle between fullscreen and windowed mode.</li>
</ol>

## Testing the Project
<p>Due to the nature of our tests, running test as a suite is not possible, instead to running unit and integration test must be done individually as 
   follows (replace test class with the name of the specific test you'd like to run) :   
 </p>
<ol>
  <li>
    Navigate to the project root directory
  </li>
  <li>
    Run the following:
    <pre><code>mvn -Dtest=<testClass> -DforkCount=0 test</code></pre>
  </li>
</ol>

## Contributors
<ul>
  <li><b>Ayush Arora</b> </li>
  <li><b>Conor Benson</b> </li>
  <li><b>Lacey Swamy</b></li>
  <li><b>Aidan de Vaal</b></li>
</ul>
    
    




