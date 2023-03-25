# ProjektZespolowyBackend
Backend oparty o Springboota 3 na potrzeby projektu zespołowego

ENDPOINTY:

/auth/register (POST)
Przyjmuje: JSON {"email":"aaa","nick":"bbb","password":1234,"phone":123123}
Zwraca: JWT

/auth/login (POST)
Przyjmuje: JSON {"email":"aaa","password":1234}    //narazie jedynie email, przerobie na email/login
Zwraca: JWT

