@echo off
set /p p_user="DBA's User Name: "

set /p p_password="Password: "

set /p p_sid="SID: "

sqlplus %p_user%/%p_password%@%p_sid% as sysdba @shoppingcart.sql

exit
