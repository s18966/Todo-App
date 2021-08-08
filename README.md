# Todo-App
Todo-App for test task

mappings
/register
pass json
{
"login":"login",
"password":"pass"
}
/login
pass json to get token
{
"login":"login",
"password":"pass"
}

after in Postman you'll get token like this 
"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjI5MjM0MDAwfQ.Muv6pDSuRKimwMWUyeLuuCyMMcLS0gc7lRiNK1GuWoC_mZDCnYixqcX7BFVitSYPHfWJK2CtbtgdGyp3mKl6Ww"

use authorization -> type Bearer Token -> paste token

now you can access 
/api/v1/tasks
and perform crud operations

tasks json looks like 
{
"id":1,
"description":"desc",
"isDone":true
}
