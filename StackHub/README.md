# **Prerequisites**

-   **ANTLR 4.11.1 or newer**

-   **Java 17**

-   **Spring etc.**

# **How to start (Windows)**

-   `cd .\link-parser\src\main\antlr`

-   `antlr4 .\LinkParser.g4 -package antlr -visitor -o ../java/antlr -lib ../java/antlr -no-listener`

-    Enjoy