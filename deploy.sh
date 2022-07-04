source ./setup.sh
javac -d src/main/webapp/WEB-INF/classes/ -cp $CATALINA_HOME/lib/\*  src/main/java/com/example/loom/*.java
jar cvf ThreadDemo.war -C src/main/webapp .
cp ThreadDemo.war $CATALINA_HOME/webapps/
