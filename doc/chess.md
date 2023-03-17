# jchess.engine.chess.Chess

## Presentation

Chess is the main API class.
It represents a chess game.

```java
public class Chess
```

## Contructors

### Default

```java
public Chess()
```

Default constructor.

---------------------------------------------

## Methods

 - [getBoard](#getBoard)
 - [getPlayer](#getPlayer)
 - [undo](#undo)
 - [move](#move)
 - [toString](#toString)

### getBoard <span id="getBoard" />

```java
public ChessBoard getBoard()
```

Get the chess board.

#### Return

The board.

---------------------------------------------

### getPlayer <span id="getPlayer" />

```java
public char getPlayer()
```

Get player who plays.

#### Return

This player. 'w' for white or 'b' for black.

---------------------------------------------

### undo <span id="undo" />

```java
public void undo()
```

Undo the last move.

---------------------------------------------

### move <span id="move" />

```java
public void move(String... moves)
```

Makes a move.

#### Parameters

 - `move` - the move to do

---------------------------------------------

### toString <span id="toString" />

```java
@Override
public String toString()
```

Return a string that you can use to display.
It may be, for example : 
```
8 r n b q k b n r 
7 p p p p . p p p 
6 . . . . . . . . 
5 . . . . p . . . 
4 . . . . P . . . 
3 . . . . . N . . 
2 P P P P . P P P 
1 R N B Q K B . R 
  a b c d e f g h 
Last move was Ng1-f3.
Black to play.
```

#### Return

A printable string.
