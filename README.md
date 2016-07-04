# TOTP Demo

A simple demo of TOTP functionality


This application is a simple Spring Boot Application to allow registration of TOTP Soft Tokens and to validate TOTP.  I have wrapped the APIs with a simple UI to demonstrate the functions.

### Registration API
```http
POST /register?email=john.doe@example.org HTTP/1.1
Host: example.org
Content-Type: application/json; charset=utf-8
Content-Length: 137

###Expected Response
200 OK
Registration successful. Please check your email for more information.
```

### Validation API
```http
POST /validate?email=john.doe@example.org&otp=123456 HTTP/1.1
Host: example.org
Content-Type: application/json; charset=utf-8
Content-Length: 137

###Expected Response
200 OK
true (or) false
```

### UI Screenshot

![Image](documentation/mainpage.png?raw=true)


