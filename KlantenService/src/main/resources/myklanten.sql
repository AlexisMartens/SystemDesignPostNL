INSERT INTO bestelling_data_model(bestelling_id, type_bestelling, naam_afzender, postcode_afzender, straat_afzender, plaats_afzender, land_afzender, naam_ontvanger, postcode_ontvanger, straat_ontvanger, plaats_ontvanger, land_ontvanger, ophalen, aanmaak_datum, status, spoed, extern, externe_levering_service) VALUES ('0','Pakket','Jan klaasen','9000','griekstraat 5','Gent','Belgie','Geert Klaasen','9100','klopperstraat 5','Sint-Niklaas','Belgie',TRUE,TO_DATE('12/20/2020','DD/MM/YYYY'),'AANGEMAAKT',FALSE,FALSE,NULL),
('1','Pakket','Piet klaasen','9000','grieksetraat 20','Gent','Belgie','Guy Klaasen','9100','klopperstraat 3','Sint-Niklaas','Belgie',FALSE,TO_DATE('10/20/2020','DD/MM/YYYY'),'AANGEMAAKT',FALSE,FALSE,NULL),
('2','Pakket','Piet klaasen','9000','grieksetraat 20','Gent','Belgie','Guy Klaasen','9100','klopperstraat 3','Sint-Niklaas','Belgie',FALSE,TO_DATE('10/20/2020','DD/MM/YYYY'),'AANGEMAAKT',FALSE,TRUE,'EXTRAATHOME');

