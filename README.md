# Todo-App
Todo-App for test task

mappings <br>
/register <br>
pass json <br>
{ <br>
"login":"login", <br>
"password":"pass" <br>
} <br>
/login <br>
pass json to get token <br>
{ <br>
"login":"login", <br>
"password":"pass" <br>
} <br>

after in Postman you'll get token like this <br>
"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjI5MjM0MDAwfQ.Muv6pDSuRKimwMWUyeLuuCyMMcLS0gc7lRiNK1GuWoC_mZDCnYixqcX7BFVitSYPHfWJK2CtbtgdGyp3mKl6Ww" <br>

use authorization -> type Bearer Token -> paste token <br>

now you can access <br>
/api/v1/tasks <br>
and perform crud operations <br>

tasks json looks like <br>
{ <br>
"id":1, <br>
"description":"desc", <br>
"isDone":true <br>
} <br>
