# JChess

JChess is a chess application containing a chess engine, a chess interface and a command-line tool to use them.

## Presentation

JChess contains three modules:

- `jchess.engine`: this is a chess engine. It includes many backend features and provides a simple API.
- `jchess.interface`: _À venir_
- `jchess.interface`: _À venir_

## Modules

### jchess.engine

This is the chess engine.

## Dependencies

jdk >= 14

## Build and install

To build and install this program, you must first clone the repository.
To do it, open a terminal in the folder you want and type:

```sh
git clone https://85ae/JChess.git
```

Then, you can go to the directory you cloned and use the `Build` tool to create the build script

```sh
java Build.java [OPTIONS]
```

Where OPTIONS can be:

Option              | Description
--------------------|------------
--help, -h          | Prints an help message then exit.
--prefix PREFIX     | The directory (`/usr` by default on Linux / Unix / Macos, `C:\Program files\JChess` on Windows) where are placed the library files. DO NOT set the root on Windows (write `\Program files\JChess` and not `C:\Program files\JChess` for example).
--os OS             | Make the scripts for os `OS` (can be `unix` for a *nix or linux-based system, `macos` or `windows`).
--test              | Enable testing (disabled by default).
--debug             | Enable debugging (disabled by default).
--clean             | Delete the build directories (`build/build_*`) then exit.
--build-dir DIR     | Build in the directory `DIR` instead of `./build`.
--src-dir DIR       | Search the sources in the directory `DIR` instead of `./src`.
--no-app            | Disable the `jchess.app` module.
--no-engine         | Disable the `jchess.engine` and `jchess.app` modules.
--no-interface      | Disable the `jchess.interface` and `jchess.app` modules.

The program will show more instructions about what you'll need to do after this.
