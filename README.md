# SystemDesignPostNL
## Docker setup and run
```
bash build.sh && docker-compose up --build
```

## Front-end runnen met Swagger
Om de front-end te testen, surf je naar 'http:localhost/swagger-ui.html'. Hier kan er een service worden geselecteerd en kan een REST-call worden verstuurd. Hieronder zijn mogelijke JSON-bodies voor elke service en elke methode.

### BestelManagement
#### maakBestelling
```
{
"aanmaakDatum": "2021-01-15",
"afzender": {
"correctAdres": true,
"land": "België",
"naam": "Travis Fimmel",
"plaats": "Ozegem",
"postcode": "1014",
"straat": "Vikingstraat 87"
},
"bestellingId": 0,
"extern": true,
"externeLeveringService": "EXTRAATHOME",
"ontvanger": {
"correctAdres": true,
"land": "België",
"naam": "Michael Hirst",
"plaats": "Bijlen",
"postcode": "7770",
"straat": "Bijlenstraat 2"
},
"ophalen": true,
"spoed": true,
"status": "AANGEMAAKT",
"typeBestelling": "PAKKET"
}
```
#### maakRetour
```
{
"bestellingId": 0,
"land": "België",
"naam": "Michael Hirst",
"plaats": "Bijlen",
"postcode": "7770",
"status": "VERWERKT",
"straat": "Bijlenstraat 2"
}
```
#### 3
```
{
"aanmaakDatum": "2021-01-15",
"afzender": {
"correctAdres": true,
"land": "België",
"naam": "Bob Marley",
"plaats": "Putte",
"postcode": "6000",
"straat": "Bobstraat 55"
},
"bestellingId": 0,
"ontvanger": {
"correctAdres": true,
"land": "België",
"naam": "Damien Marley",
"plaats": "Bobbegem",
"postcode": "4200",
"straat": "Marleystraat 1"
},
"ophalen": true,
"spoed": true,
"status": "AANGEMAAKT",
"typeBestelling": "PAKKET"
}
```

### ZendingManagement
```
{
"correctAdres": true,
"land": "België",
"naam": "Alexander De Groote",
"plaats": "Leuven",
"postcode": "4444",
"straat": "Kafkastraat 1"
}
```
### SorteerItemManagement
#### maakBriefSorteerItem
```
{
    "trackId": "1000",
    "doel" : {
        "naam" : "Lodewijk XIV",
        "postcode" : "9000",
        "straat" : "straat",
        "plaats" : "Nevele",
        "land" : "Belgie"
    },
    "afkomst" : {
        "naam" : "Karel de Grote",
        "postcode" : "9000",
        "straat" : "straat",
        "plaats" : "Nevele",
        "land" : "Belgie"
    },
    "huidigeLocatie" : {
        "naam" : "Sorteercentrum Gent",
        "postcode" : "9000",
        "straat" : "Gentstraat 10",
        "plaats" : "Gent",
        "land" : "Belgie"
    },
    "soort": "BRIEF",
    "spoed": "false"
}
```

## Wat niet werkt
(verschillend van de changelog, 
zoals Christof zegt:  'Als een deel van jullie functionaliteit niet werkt, gelieve dit hier dan ook te vermelden zodat wij niet nodeloos moeten zoeken.'
)
