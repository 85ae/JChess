# jchess.engine.chess.ChessBoard

## Presentation

This is a representation of a chess board.
It contains advanced board management methods, getters and others.

```java
public class ChessBoard
```

## Contructors

### Default

```java
public ChessBoard()
```

The default constructor.
Initialize the board with the default board (rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR).

------------------------------------------------

## Methods

 - [getPiece](#getPiece)
 - [getPlayer](#getPlayer)
 - [move](#move)
 - [undo](#undo)
 - [getHistory](#getHistory)
 - [toString](#toString)

### getPiece <span id="getPiece" />

```java
public ChessPiece getPiece(String position)
```

Get a piece.

------------------------------------------------

#### Parameters

 - `position` - the postion where the piece is.

#### Return

The piece situated here.

------------------------------------------------

### getPlayer <span id="getPlayer" />

```java
public char getPlayer()
```

Get the player who plays.

#### Return

'w' for white or 'b' for black.

------------------------------------------------

### move <span id="move" />

```java
public void move(String... moves)
```

Move (a) piece(s) if possible.

#### Parameters

 - `moves` - the move(s) to do.

------------------------------------------------

### undo <span id="undo" />

```java
public void undo()
```

Undo the last move.

------------------------------------------------

### getHistory <span id="getHistory" />

```java
public String[] getHistory()
```

Return the history.

#### Return

The history.

------------------------------------------------

### toString <span id="toString" />

```java
public String toString()
```

Return a representation of the board.

#### Return

A printable string.
