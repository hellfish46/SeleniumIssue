# SeleniumIssue

1)Run selenoid in docker:
```docker run -d                                   \
--name selenoid                                 \
-p 4444:4444                                    \
-v /var/run/docker.sock:/var/run/docker.sock    \
-v /Users/[username]/Documents/config_dir:/etc/selenoid/:ro              \
aerokube/selenoid:latest-release
```
2)Install Java 11;

3)Run the test:
```
mvn clean test
```
OR find class 'FirstTest' and run the single test 'x'.

Additional info:
I run it on IDE
IntelliJ IDEA 2021.1 (Community Edition)
Build #IC-211.6693.111, built on April 6, 2021
Runtime version: 11.0.10+9-b1341.35 x86_64.
