1、File - Project Structure
     - Project Settings
          - Project
               - Name: code-snippet
               - SDK: 1.8 java version "1.8.0_144"
               - Language Level: 8 - Lambdas, type annotations etc.
          - Module
               - code-snippet
                    - Sources
                         - Language Level: 8 - Lambdas, type annotations etc.
                         - Dependencies
                              - Module SDK: 1.8 java version "1.8.0_144"
               - jdk18
                    - Sources
                         - Language Level: X Experimental features
                         - Dependencies
                              - Module SDK: 18 Oracle OpenJDK version 18

2、IntelliJ IDEA - Preferences
     - Build, Execution, Deployment
          - Compiler
               - Java Compiler
                    - Project bytecode version: 18
                    - Per-module bytecode version
                         - code-snippet: 1.8
                         - jdk18: 17