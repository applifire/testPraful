




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/H5IX8O2HZSWQEQZ92JGHWG
ART_CREATE_PATH=/tmp/applifire/db/H5IX8O2HZSWQEQZ92JGHWG/art/create
AST_CREATE_PATH=/tmp/applifire/db/H5IX8O2HZSWQEQZ92JGHWG/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=ep
USER=ep
PASSWORD=ep
PORT=3306
HOST=localhost


