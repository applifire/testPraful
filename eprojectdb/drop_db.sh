




echo $PATH
DB_PATH=/tmp/applifire/db/H5IX8O2HZSWQEQZ92JGHWG
MYSQL=/usr/bin
USER=ep
PASSWORD=ep
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'