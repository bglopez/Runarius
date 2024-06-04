# Runarius

A RuneScape Classic client and server. The client is loosely based on mudclient204, but neither the client nor the server is compatible with any other clients or servers. They are designed to work together exclusively.

The emphasis is on an extremely simple and clean codebase. There are no fancy manager classes for other managers or overly clever object inheritance structures. The codebase is designed to be as flat and easy to understand as possible, making it "boring" to read for the sake of clarity and simplicity. Even if the code might not always be idiomatic Java or the most performant, this is done intentionally to prioritize clarity. When performance becomes critical, the code can be refactored later.

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