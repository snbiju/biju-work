# biju-work

Basic requirement
-----------------
Java 8 or higher

Spring boot 2.0.4.RELEASE

Apache Maven 3.5.4


To run
-------
clone https://github.com/snbiju/biju-work.git / download

go to biju-work-master

mvn spring-boot:run


http://localhost:9999/card/add  (POST REQUEST)

Request body

  {
        "name": "visa",
        "cardNumber": "4242424242424242",
        "limit": "4"
    }


http://localhost:9999/card/list (GET REQUEST)

[
    {
        "name": "visa",
        "cardNumber": "4242424242424242",
        "limit": 4
    }
]