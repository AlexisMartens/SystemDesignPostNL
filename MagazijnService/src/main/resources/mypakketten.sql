INSERT INTO pakket_data_model(zending_id, type_zending, naam_huidige_locatie, postcode_huidige_locatie, straat_huidige_locatie, plaats_huidige_locatie, land_huidige_locatie,
naam_afzender, postcode_afzender, straat_afzender, plaats_afzender, land_afzender, naam_ontvanger, postcode_ontvanger, straat_ontvanger, plaats_ontvanger, land_ontvanger,
ophalen_bij_klant_thuis, aanmaak_datum, status, spoed) VALUES 
('0','Pakket','Spar Deinze','9005','Deinzestraat 5','Deinze','Belgie','Geert Klaasen','9100','klopperstraat 5','Sint-Niklaas','Belgie','Piet klaasen','9000','grieksetraat 20','Gent','Belgie',TRUE,TO_DATE('12/20/2020','DD/MM/YYYY'),'OP_TE_HALEN_BIJ_KLANT',FALSE),
('1','Pakket','Delhaize Affligem','9800','Bierstraat 24','Affligem','Belgie','Lucia Glacia','7000','westvlstraat 9','Westegem','Belgie','Carolien Haas','8404','ieperstraat 20','Ieper','Belgie',FALSE,TO_DATE('10/20/2020','DD/MM/YYYY'),'AF_TE_HALEN_IN_AFHAALPUNT',FALSE),
('2','Pakket','Afhaalpunt Melle','9200','Melleweg 8','Melle','Belgie','Kaat Kaas','1000','komstraat 55','Sint-Niklaas','Belgie','Piet klaasen','9000','grieksetraat 20','Gent','Belgie',TRUE,TO_DATE('10/20/2020','DD/MM/YYYY'),'AF_TE_HALEN_IN_AFHAALPUNT',FALSE);

