package com.diego.exception.httperrors;


public class ClientErrorHttpException extends RuntimeException {

}

/*
* In Spring, when an error of type 400 (client error) occurs, the HttpClientErrorException
*  class is typically thrown. This class extends the RestClientResponseException class and
* represents an HTTP status code in the 4xx range. Similarly, when an error of type 500
* (server error) occurs, the HttpServerErrorException class is typically thrown. This class
* also extends the RestClientResponseException class and represents an HTTP status code in
* the 5xx range.

In your custom exception package, it looks like you have defined a ClientErrorHttpException
* class and a ServerErrorHttpException class. These classes could be used to handle client
* and server errors, respectively, in your application. You could use these custom exceptions
* to provide more specific error handling and messaging for different types of client and
* server errors that may occur in your application. Is there anything else you would like
* to know? 😊
* */