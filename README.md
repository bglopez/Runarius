# Runarius

A RuneScape Classic client and server rewritten from scratch in Java. The client is loosely based on mudclient204, but neither the client nor the server is compatible with any other clients or servers. They are designed to work together exclusively.

![](./doc/screenshot.png?raw=true)

## requirements

- [ant (Apache)](https://ant.apache.org/bindownload.cgi) - build tool
- [jdk](https://www.oracle.com/de/java/technologies/downloads/#jdk22-windows) - java development kit

## build
In the terminal run:

    $ ant

This will build the client and the server in `out/dist/client` and `out/dist/server`.

## license

[MIT](LICENSE.md)