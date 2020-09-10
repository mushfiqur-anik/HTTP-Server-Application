# HTTP-Server-Application

## Description of the problem
you will implement a simple HTTP server application and use it with off-the-shelf HTTP clients
Precisely, we aim to build a simple remote file manager on top of the HTTP protocol in the server side

Similarly to Assignment #1, the goal of the Lab is to develop your programming library that implements the basic functionalities of the HTTP server as will be described in the following sections.

 In other words, you should build a remote file server manager on top the library according to the following requirements:
 1-GET / returns a list of the current files in the data directory. You can return differenttype format such as JSON, XML, plain text, HTML according to the Accept key of the
Comp445 – Lab Assignment # 2 Page 3
header of the request. However, this is not mandatory; you can simply ignore the header value and make your server always returns the same output.
2-GET /foo returns the content of the file named foo in the data directory. If thecontent does not exist, you should return an appropriate status code (e.g. HTTPERROR 404).
3-POST /bar should create or overwrite the file named bar in the data directory withthe content of the body of the request. You can implement more options for thePOST such as overwrite=true|false, but again this is optional.
 
 ## File List
 
 ## Built with

* [**Java**](https://en.wikipedia.org/wiki/Java_(programming_language)) - The programming language used
* [**Eclipse**](https://en.wikipedia.org/wiki/Eclipse_(software)) - The IDE used 


## Author(s)

* [**Mushfiqur Anik**](https://github.com/mushfiqur-anik)

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
