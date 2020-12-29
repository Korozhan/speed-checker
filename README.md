#### Для запуска проекта необходимо выполнить следующие шаги:
1. `git clone https://github.com/Korozhan/speed-checker.git`
2. `cd speed-checker`
3. `mvn clean package`
4. `cd target`
5. `java -Dfile.encoding=UTF8 -jar speed-checker-test-1.0-SNAPSHOT.jar`
Для того чтобы узнать какие команды поддерживаются приложением нужно выпониль команду с аргументом `help`:
```java
java -Dfile.encoding=UTF8 -jar speed-checker-test-1.0-SNAPSHOT.jar help
```