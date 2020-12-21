# Seniz-Web
Seniz web service to support [Seniz GUI](https://github.com/yepengding/Seniz-GUI). \
This project integrates [Seniz](https://github.com/yepengding/Seniz).

## Compile Command
### Production
```
mvn clean package -D maven.test.skip=true -P prod
```

## Start up
```
java -jar *
```
Default port: 8080 \
In-memory database: /console
API Doc: /swagger-ui.html