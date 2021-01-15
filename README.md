# SystemDesignPostNL
## Docker setup and run
```
bash build.sh && docker-compose up --build
```

## Front-end runnen met Swagger
Om de front-end te testen, surf je naar 'http:localhost/swagger-ui.html'. Hier kan er een service worden geselecteerd en kan een REST-call worden verstuurd. Hieronder zijn mogelijke JSON-bodies voor elke service en elke methode.

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
