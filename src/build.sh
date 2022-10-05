#! /bin/sh
rm -rf ../build
javac -d ../build Chess.java
cp -r ../lang ../build
cp install.sh ../build
echo "You can install it in /opt or ~/.local/opt by running"
echo "    cd ../build"
echo "    [PREFIX=<DIR> ][INSTALL_DIR=<DIR> ]./install.sh"
echo "The default prefix is /usr and the default install dir is /opt"
