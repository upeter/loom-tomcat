## Tests:
### Normal Tomcat: localhost:8080
```curltime -s "http://localhost:8080/blocking/sleep?delay=200"```
```baton -u http://localhost:8080/blocking/sleep?delay=6000 -c 150 -r 200```

```curltime -s "http://localhost:8080/blocking/remote?delay=200"```
```baton -u http://localhost:8080/blocking/remote?delay=6000 -c 150 -r 200```


### Loom Tomcat: localhost:8082
```curltime -s "http://localhost:8082/loom/sleep?delay=200"```
```baton -u http://localhost:8082/loom/sleep?delay=6000 -c 150 -r 200```

```curltime -s "http://localhost:8082/loom/remote?delay=200"```
```baton -u http://localhost:8082/loom/remote?delay=6000 -c 150 -r 200```


### Netty Coroutines: localhost:8083
```curltime -s "http://localhost:8083/coroutines/sleep?delay=200"```
```baton -u http://localhost:8083/coroutines/sleep?delay=6000 -c 150 -r 200```

```curltime -s "http://localhost:8083/coroutines/remote?delay=200"```
```baton -u http://localhost:8083/coroutines/remote?delay=6000 -c 150 -r 200```

### Netty Reactor: localhost:8083
```curltime -s "http://localhost:8083/reactor/remote?delay=200"```
```baton -u http://localhost:8083/reactor/remote?delay=6000 -c 150 -r 200```# loom-tomcat
