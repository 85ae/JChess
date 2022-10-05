#! /bin/sh
if [ -z $PREFIX ] ; then PREFIX=~/.local ; fi
#if [ -z $INSTALL_DIR ] ; then INSTALL_DIR=~/.local/opt ; fi

INSTALL_FILES="lang board config pieces players moves Chess.class"

echo '#! /bin/sh' > chess
echo 'java -jar '$PREFIX'/lib/Chess.jar' >> chess

jar -cfe Chess.jar Chess $INSTALL_FILES

if [ ! -d $PREFIX/lib ] ; then install -d $PREFIX/lib ; fi
if [ ! -d $PREFIX/bin ] ; then install -d $PREFIX/bin ; fi
if [ ! -d ~/.chess ] ; then install -d ~/.chess ; fi

install -m 0622 -T Chess.jar $PREFIX/lib/Chess.jar
install -m 0733 -T chess $PREFIX/bin/chess
install -m 0666 -C -T ../examples/config/default.conf ~/.chess/chess.conf
