# HTTP-Server-Application

## Description of the problem
In this project a simple HTTP server library was built and it was tested with existing HTTP Client applications. A remote file server manager was built on top of the existing library features (Programmed in the previous project). The features that are added are the following: 
1) GET /           - This command returns all the files that are in the specified directory.
2) GET /someFile   - This command looks up the file "someFile" in the specified directory and displays the contents inside the file. If the file doesn't exist 
                      in the current directory then it displays the appropriate status code (For example: HTTP ERROR 404).
3) POST /someFile  - This command creates a new file "someFile" in the directory and writes the content specified in the body of the request. If the file                                exist in the directory then it overrides the data inside the file with the content specified in the body of the request.

 
 ## File List
 - HttpServer.java
 - HttpServerApplication.java
 
 
 ## Built with
* [**Java**](https://en.wikipedia.org/wiki/Java_(programming_language)) - The programming language used
* [**Eclipse**](https://en.wikipedia.org/wiki/Eclipse_(software)) - The IDE used 


## Author(s)

* [**Mushfiqur Anik**](https://github.com/mushfiqur-anik)

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
