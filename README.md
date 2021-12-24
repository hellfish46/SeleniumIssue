# SeleniumIssue

Run selenide in docker:
```docker run -d                                   \
--name selenoid                                 \
-p 4444:4444                                    \
-v /var/run/docker.sock:/var/run/docker.sock    \
-v /Users/antonhrytsun/Documents/config_dir:/etc/selenoid/:ro              \
aerokube/selenoid:latest-release
```

Install Java 11
To run tests:

```
mvnw clean test
```
