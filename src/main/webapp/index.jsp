<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thread Info</title>
</head>
<body>
<h1><%= "Loom Demo" %>
</h1>
<ul>
    <li><a href="sleep?delay=0">Thread Info</a></li>
    <li><a href="remote?delay=0">Remote Call with Delay</a></li>
    <li><a href="parallel?delay=0">Parallel Remote Call with Delay virtual Threads</a></li>
    <li><a href="parallelj?delay=0">Parallel Remote Call with Delay normal Threads</a></li>
</ul>
</body>
</html>