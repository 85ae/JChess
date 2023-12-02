# jchess.engine.chess.ChessPiece

## Presentation

Represents a chess piece.

```java
public class ChessPiece
```

## Constructors

### Default

```java
public ChessPiece()
```

The default constructor.
Creates a blank piece at a1.

---------------------------------------------

### Another one

```java
public ChessPiece(char piece, String position, char player)
```

Main constructor

#### Parameters

 * `piece` - the piece symbol ('k' for king, 'p' for pawn...).
 * `position` - the position of the piece.
 * `player` - the player ('w' or 'b', ' ' for none).

## Methods

 - [getPosition](#get_postition)
 - [getOwner](#get_owner)
 - [getSymbol](#get_symbol)
 - [toString](#to_string)

### getPosition <span id="get_position"></span>

```java
public String getPosition()
```

Return the position.

#### Return

The position (like "e4").

---------------------------------------------

### getOwner <span id="get_owner"></span>

```java
public char getOwner()
```

Get the piece owner.

#### Return

'w' if white, 'b' if black and ' ' if null.

---------------------------------------------

### getSymbol <span id="get_symbol"></span>

```java
public char getSymbol()
```

Return a character that represents the piece.
It can be 'N' for a white knight or 'p' for a black pawn.

#### Return

The character. '.' for a null piece (blank case).

---------------------------------------------

### toString <span id="to_string"></span>

```java
@Override
public String toString()
```

Return a printable string.
For example, it can be "Be4" for a white bishop situated in e4.

#### Return

A printable string.
