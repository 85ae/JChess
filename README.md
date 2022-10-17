# JChess

JChess is a chess library wich allow you to create a java program without writing frontend part.

## Presentation

JChess is a chess library written in Java.
He can manipulate .pgn and .fen files.

## Features

Feature                | Support
-----------------------|--------
PGN                    | No
FEN                    | No
A.I. player            | No

## Dependencies
jdk >= 8

## Build and install

To build and install this program, you must first clone the repository.
To do it, open a terminal in the folder you want and type:
```
git clone https://85ae/JChess.git
```

Then, you can go to the directory you cloned and use the `Build` tool to create the build script
```sh
java Build.java [OPTIONS]
```
Where OPTIONS can be:

Option | Description
-------|------------
--prefix PREFIX     | The directory (`/usr/` by default on Linux / Unix / Macos, `C:\Program files\` on Windows) where are placed the library files.
--no-jar            | By default, the library files are compressed in a jar file named `JChess.jar`. This option places these files in a directory (`JChess/`) instead.
--os OS             | Make the scripts for os `OS` (can be `unix` for a *nix or linux-based system, `macos` or `windows`).
--test              | Enable testing (disabled by default).
--debug             | Enable debugging (disabled by default).
--build-dir DIR     | Build in the directory `DIR` instead of `./build/`.
--src-dir DIR       | Search the sources in the directory `DIR` instead of `./src/`.

The program will show more instructions about what you'll need to do after this.
