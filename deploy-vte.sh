source ./setup.sh
javac -d bin -cp $CATALINA_HOME/lib/\* --enable-preview --release 19 src/main/java/executor/*.java
jar cvf vte.jar -C bin executor
cp vte.jar $CATALINA_HOME/lib/