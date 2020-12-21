INSERT INTO koerier_data_model(koerier_id, naam, postcode_ronde, vervoercapaciteit) 
VALUES 
('1','Jonny','9900','150'),
('2','James','9000','80'),
('3','Betty','9000','100'),
('4','Jamie','4000','90');

INSERT INTO order_data_model(order_id, koerier_id, naam_afzender, postcode_afzender, straat_afzender, plaats_afzender, land_afzender, naam_ontvanger, postcode_ontvanger, straat_ontvanger, plaats_ontvanger, land_ontvanger, aanmaak_datum                     , spoed, extern, order_status)
VALUES                      ('1'     ,'1'        ,'Jan klaasen' ,'9000'            ,'griekstraat 5' ,'Gent'          ,'Belgie'      , 'Geert Klaasen' ,'9100'           ,'klopperstraat 5','Sint-Niklaas'   , 'Belgie'      , TO_DATE('20/12/2020','DD/MM/YYYY'), FALSE, FALSE, 'OP_TE_HALEN');








