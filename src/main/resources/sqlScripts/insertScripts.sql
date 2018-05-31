insert into accounts (email, hashed_password, first_name, last_name, address, postal_nr, telephone_nr, role, opt_lock_version)
values ('Vartotojas1@test.lt', 'sks0ZEEhGTdD4YMrBR3W5A==', 'Vardenis', 'Pavardenis', 'Naugarduko g. 24', '03225', '860000000', 'User', 0);

insert into accounts (email, hashed_password, first_name, last_name, address, postal_nr, telephone_nr, role, opt_lock_version)
values ('Vartotojas2@test.lt', 'sks0ZEEhGTdD4YMrBR3W5A==', 'Jonas', 'Jonaidis', 'Didlaukio g. 59', '08302', '860000001', 'User', 0);

insert into accounts (email, hashed_password, first_name, last_name, address, postal_nr, telephone_nr, role, opt_lock_version)
values ('Vartotojas3@test.lt', 'sks0ZEEhGTdD4YMrBR3W5A==', 'Uzblokuotas', 'Vartotojas', 'Naugarduko g. 24', '03225', '860000002', 'Blocked', 0);

insert into accounts (email, hashed_password, role, opt_lock_version)
values ('admin', 'gwBdINtuCcv9k+V3I2z1vQ==', 'Admin', 0);

insert into cart (account_id) values (1);
insert into cart (account_id) values (2);
insert into cart (account_id) values (3);
insert into cart (account_id) values (4);

insert into products_categories (name, parent_category) values ('Riedlentės', null);
insert into products_categories (name, parent_category) values ('Riedučiai', null);
insert into products_categories (name, parent_category) values ( 'Apsaugos', null);
insert into products_categories (name, parent_category) values ('Atsarginės dalys', null);
insert into products_categories (name, parent_category) values ('Longboards', 1);
insert into products_categories (name, parent_category) values ('Skateboards', 1);
insert into products_categories (name, parent_category) values ( 'Ratai', 4);
insert into products_categories (name, parent_category) values ('Guoliai', 4);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Choke Logo Series Pinky', 49, 'Riedlentė Choke Logo Series Pinky, skirta pradedantiesiems ir pažengusiems riedlentininkams. Tinkama klasikiniam stiliui, važinėjimui mieste ir nesudėtingiems triukams. Riedlentė pagaminta iš aukštos kokybės medžiagų, tai lemia puikų jos kontroliavimą. Gamintojo nurodomas maksimalus apkrovos indeksas - 100kg.',
1, 'SK75623', 0, 6);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Choke Skull Series Homegrown Evo', 59, 'Riedlentė Choke Skull Series Homegrown Evo, skirta pradedantiesiems ir pažengusiems riedlentininkams. Tinkama klasikiniam stiliui,važinėjimui mieste ir nesudėtingiems triukams. Riedlentė pagaminta iš aukštos kokybės medžiagų, tai lemia puikų jos kontroliavimą. Gamintojo nurodomas maksimali apkrovos indeksas - 100kg.',
2, 'SK564523', 0, 6);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Choke Heavy Metal Silver', 59, 'Riedlentė Choke Heavy Metal Silver, skirta pažengusiems riedlentininkams. Tinkama klasikiniam stiliui ir triukams. Riedlentė pagaminta iš aukštos kokybės medžiagų, tai lemia puikų jos kontroliavimą. Gamintojo nurodomas maksimali apkrovos indeksas - 100kg.',
3, 'SK951236', 0, 6);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Mindless Voodoo Cayuga II', 195, 'Mindless Cayuga Longboard- kokybės, greičio, komforto derinys. Tai lenta, su kuria pasijusite tarsi skriedami banglente jūros bangomis. Neužmirškite apsaugų- ši lenta įsibėgėja labai greitai. Išpjovos važiuoklei ir ratams gerokai pažemino šio asfalto bolido svorio centrą, tad ja galima užtikrintai ir tiksliai manevruoti posūkiuose (carving) bei stabiliai laikytis lekiant dideliu greičiu nuokalne (downhill).',
5, 'SK965424', 0, 5);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Mindless Voodoo Lakota DT', 171.27, 'Mindless Maverick DT (drop through) Longboard- kokybės, greičio, komforto derinys. Ištisinė Kanados klevo (drop through) formos lenta paruošta važiavimui. Tai lenta, su kuria pasijusite tarsi čiuoždami banglente jūros bangomis. Su ja galima užtikrintai ir tiksliai manevruoti posūkiuose (carving) bei stabiliai laikytis lekiant dideliu greičiu nuokalne (downhill) Neužmirškite apsaugų- ši lenta įsibėgėja labai greitai!',
8, 'SK321785', 0, 5);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Surf Logic Fuck The Rules', 139, 'Šių metų Surf Logic kolekcijos &quot;free ride&quot; longboardas Fuck The Rules – pats tas prasilėkti mieste ar parke. Lenkia visus atitinkamos klasės longbordus savo kategorijoje.',
11, 'SK862314', 0, 5);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Tempish Flow 46', 155.86, 'Longboardas Tempish Flow 46. Didesnė ir platesnė nei standartinė riedlentė suteikia papildomo stabilumo ir komforto, todėl ja lengviau išmoks važiuoti net ir pradedantysis. Šis klasikinio lašo formos modelis yra itin universalus ir yra tinkamas kuo įvairiausiomis sąlygomis. Du bambuko sluoksniai šią riedlentę padaro itin lanksčia ir lengva.',
13, 'SK452354', 0, 5);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Volten Imperio II Dropthrough', 158.26, 'Ilgoji riedlentė Volten Imperio II Dropthrough, skirta pradedantiesiems ir pažengusiems riedlentininkams, kurie mėgsta važinėti su longboard tipo riedlentėmis. Lentos ilgio pagalba jaučiamas komfotas tiek važiuojant lygia vieta, tiek nuokalne. Riedlentės plotis suteikia papildomo stabilumo. Riedlentė pagaminta iš aukštos kokybės medžiagų.',
17, 'SK367845', 0, 5);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('SEBA FR1 80 Grey', 176.26, 'SEBA FR1 80 slalomo freestyle gatvės riedučiai- vienas populiariausių modelių pasaulyje. Keičiamos šoninės apsauginės-abrazyvinės plokštelės, apsaugančios batą nuo subraižymų mokinantis slydimus. Patentuotas auliuko 4 pozicijų nustatymas.',
20, 'SK235487', 0, 2);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Powerslide Kaze SC 110', 299, 'Lengvas ir inovatyvus Powerslide riedučių modelis Kaze SC 110. Šiuose riedučiuose sumontuota naujoji Trinity sistema, dėl kurios svorio centras atsiduria žemiau, o pats riedutis tampa stabilesnis dėl trijuose taškuose tvirtinamo rėmo. Batas yra itin tvirtas, bet tuo pačiu metu ir lengvas, dėl to šis modelis tinka tiek važinėjimui mieste, tiek ilgesnėm distancijom.',
22, 'SK198752', 0, 2);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('SEBA FR2 80', 158.40, 'Seba FR2 80 sukurti žengti į SEBA freestyle riedučių pasaulį. Gaminant FRX buvo naudojama populiariojo FR1 bazė- bato kevalas ir rėmas, tad daugeliu charakteristikų šis modelis pasižymi FR1 savybėmis.',
25, 'SK987652', 0, 2);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('SEBA PRO pack 3 S', 60.39, 'Kokybiškos ir tvirtos riešų, kelių ir alkūnių apsaugos visiems riedutininkams, lentų sportininkams ir ne tik. Kelių ir alkūnių apsaugos. Plastikinė apsauginė plokštelė. Minkšta CoolMax medžiaga iš vidaus. Tvirtinimas - elastingi Velcro dirželiai, kuriuos galima užfiksuoti ir nenusiimant riedučių dėl easy on/easy off sistemos. Tinklinė medžiaga apsaugo vidinę kelio pusę nuo prakaitavimo. Riešų apsaugos. Plastikinis nuimamas riešo įtvirtinimas. Vidinė medžiaga su CoolMax technologija, dėl ko rankos neprakaituoja. Greita ir patogi tvirtinimo sistema. Pirmo lygio apsauga.',
26, 'SK875622', 0, 3);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('SEBA PRO pack 3 L', 60.39, 'Kokybiškos ir tvirtos riešų, kelių ir alkūnių apsaugos visiems riedutininkams, lentų sportininkams ir ne tik. Kelių ir alkūnių apsaugos. Plastikinė apsauginė plokštelė. Minkšta CoolMax medžiaga iš vidaus. Tvirtinimas - elastingi Velcro dirželiai, kuriuos galima užfiksuoti ir nenusiimant riedučių dėl easy on/easy off sistemos. Tinklinė medžiaga apsaugo vidinę kelio pusę nuo prakaitavimo. Riešų apsaugos. Plastikinis nuimamas riešo įtvirtinimas. Vidinė medžiaga su CoolMax technologija, dėl ko rankos neprakaituoja. Greita ir patogi tvirtinimo sistema. Pirmo lygio apsauga.',
28, 'SK875623', 0, 3);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Powerslide standard Man S', 29, 'Powerslide kompanijos trijų dalių apsaugų komplektas riedutininkams. Anatominės formos dvigubo tankio EVA kempinė su plastikinėmis plokštelėmis. Fiksacija - elastingi Velcro dirželiai ir ergonomiška medvilnės &quot;rankovė&quot;. Saugumo klasė A+++',
30, 'SK321256', 0, 3);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Powerslide standard Man M', 29, 'Powerslide kompanijos trijų dalių apsaugų komplektas riedutininkams. Anatominės formos dvigubo tankio EVA kempinė su plastikinėmis plokštelėmis. Fiksacija - elastingi Velcro dirželiai ir ergonomiška medvilnės &quot;rankovė&quot;. Saugumo klasė A+++',
31, 'SK321257', 0, 3);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Ennui City Brace', 39, 'Riešų apsaugos Ennui Allround Wrist Brace. Riešas įtvirtinamas dviem anatomiškai išformuotais aliumininiais įtvarais apsisaugojimui nuo traumos krintant. Neopreninė įmautė leidžia rankai maksimaliai kvėpuoti ir maloniai priglunda prie plaštakos. Tvirtinama dviem Velcro dirželiais su papildoma fiksacijos apsauga.',
32, 'SK321369', 0, 3);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Autobahn Nexus', 29, 'Komplekte 4 vienetai.',
34, 'SK126756', 0, 7);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Powerslide DEFCON RTS', 32, 'Powerslide kompanijos riedučių ratukai Defcon RTS, pagaminti iš dvigubo kietumo medžiagos. Vidinis minkštas sluoksnis lemia puikų vibracijos sugėrimą, išorinis kietesnis - tinkamą sukibimą su važiuojamąją danga ir didesnį greitį. Grubus ratuko apdirbimas leidžia lengviau atlikti slydimus - &quot;powerslide&#39;us&quot;. RTS - Ready to Slide! Komplekte 4 vienetai.',
35, 'SK513548', 0, 7);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('SEBA Luminous', 30, 'Šviečiantys SEBA kompanijos ratukai LUMINOUS. Dėl didesnio LED lempučių kiekio ir kokybės šie ratukai yra ypač ryškūs, o aukštos kokybės tarpinės-generatoriaus šie ratukai niekada nenustos šviesti.',
36, 'SK189456', 0, 7);

insert into products (name, price, description, main_image_id, sku_code, opt_lock_version, category)
values ('Wicked SUS Rustproof', 73.90, 'Wicked kompanijos SUS Rustproof guoliai pagaminti iš aukščiausios kokybės medžiagų. Freespin technologija mažina trintį guolyje, taip pagerindama riedėjimo kokybę bei guolio tarnavimo laiką. Šie guoliukai yra puikus pasirinkimas tiems kas mėgsta riedėti visus metus, guoliukai pritaikyti visoms oro sąlygoms. Uždengta guolio viena pusė sukurta patogiam išvalymui ir sutepimui. Sukurti riedučiams, tačiau tinka ir riedlentėms, paspirtukams. Komplekte - 16vnt.',
36, 'SK956231', 0, 8);

insert into product_images (product_id) values (1);
insert into product_images (product_id) values (2);
insert into product_images (product_id) values (3);
insert into product_images (product_id) values (3);
insert into product_images (product_id) values (4);
insert into product_images (product_id) values (4);
insert into product_images (product_id) values (4);
insert into product_images (product_id) values (5);
insert into product_images (product_id) values (5);
insert into product_images (product_id) values (5);
insert into product_images (product_id) values (6);
insert into product_images (product_id) values (6);
insert into product_images (product_id) values (7);
insert into product_images (product_id) values (7);
insert into product_images (product_id) values (7);
insert into product_images (product_id) values (7);
insert into product_images (product_id) values (8);
insert into product_images (product_id) values (8);
insert into product_images (product_id) values (8);
insert into product_images (product_id) values (9);
insert into product_images (product_id) values (9);
insert into product_images (product_id) values (10);
insert into product_images (product_id) values (10);
insert into product_images (product_id) values (10);
insert into product_images (product_id) values (11);
insert into product_images (product_id) values (12);
insert into product_images (product_id) values (12);
insert into product_images (product_id) values (13);
insert into product_images (product_id) values (13);
insert into product_images (product_id) values (14);
insert into product_images (product_id) values (15);
insert into product_images (product_id) values (16);
insert into product_images (product_id) values (16);
insert into product_images (product_id) values (17);
insert into product_images (product_id) values (18);
insert into product_images (product_id) values (19);
insert into product_images (product_id) values (20);

insert into product_attributes (product_id, attribute_name, attribute_description)
values (1, 'Ilgis', '31“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (1, 'Plotis', '7,5“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (1, 'Ratai', '52x30mm, 100A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (1, 'Guoliai', 'Wicked ABEC 5');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (1, 'Ašys', '5“Temple Pro');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (1, 'Spalva', 'Oranžinė');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (2, 'Ilgis', '31“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (2, 'Plotis', '8“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (2, 'Ratai', '52x33mm, 100A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (2, 'Guoliai', 'Wicked ABEC 7');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (2, 'Ašys', '5“Temple Pro');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (2, 'Spalva', 'Mėlyna');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (3, 'Ilgis', '32“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (3, 'Plotis', '8“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (3, 'Ratai', '32x30mm, 100A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (3, 'Guoliai', 'Wicked ABEC 7');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (3, 'Ašys', '5“ gravity casted AL');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (3, 'Spalva', 'Sidabrinė');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (4, 'Ilgis', '39“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (4, 'Plotis', '10“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (4, 'Ratai', '75x52mm 78A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (4, 'Guoliai', 'High Precision Mindless');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (4, 'Maksimalus Svoris', '130kg');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (5, 'Ilgis', '40“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (5, 'Plotis', '29.75“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (5, 'Guoliai', 'High Precision Mindless');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (5, 'Maksimalus Svoris', '130kg');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (6, 'Ilgis', '40.5“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (6, 'Plotis', '9.75“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (6, 'Ratai', '71x51mm 82A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (6, 'Guoliai', 'ABEC 7');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (6, 'Maksimalus Svoris', '120kg');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (7, 'Ilgis', '46“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (7, 'Plotis', '9.6“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (7, 'Ratai', '70x51 mm 78A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (7, 'Guoliai', 'ABEC 9');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (7, 'Maksimalus Svoris', '100kg');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (8, 'Ilgis', '38“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (8, 'Plotis', '9“');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (8, 'Ratai', '70x51mm 80A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (8, 'Guoliai', 'ABEC 9');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (8, 'Maksimalus Svoris', '100kg');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (9, 'Guoliai', 'Twincam Titalium freeride');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (9, 'Ašys', '8mm');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (9, 'Ratai', '80 mm 84A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (9, 'Paskirtis', 'Freeride');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (10, 'Guoliai', 'Wicked freespin ABEC 9');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (10, 'Ašys', '8mm');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (10, 'Ratai', '110mm 88A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (10, 'Paskirtis', 'Fitness');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (11, 'Ratai', '80mm 85A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (11, 'Paskirtis', 'Freeride');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (12, 'Gamintojas', 'SEBA');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (12, 'Dydis', 'S');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (13, 'Gamintojas', 'SEBA');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (13, 'Dydis', 'L');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (14, 'Gamintojas', 'Powerslide');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (14, 'Dydis', 'S');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (15, 'Gamintojas', 'Powerslide');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (15, 'Dydis', 'M');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (16, 'Gamintojas', 'Ennui');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (16, 'Dydis', 'M');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (17, 'Dydis', '52mm');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (17, 'Storis', '30mm');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (17, 'Kietumas', '100A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (17, 'Skirta', 'Riedlentėms');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (18, 'Dydis', '80mm');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (18, 'Kietumas', '85A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (18, 'Skirta', 'Riedučiams');

insert into product_attributes (product_id, attribute_name, attribute_description)
values (19, 'Dydis', '62mm');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (19, 'Kietumas', '85A');
insert into product_attributes (product_id, attribute_name, attribute_description)
values (19, 'Skirta', 'Riedučiams');

insert into orders(account_id, status, order_created)
values (1, 'In progress', current_timestamp );
insert into orders(account_id, status, order_created)
values (1, 'Delivered', current_timestamp );
insert into orders(account_id, status, order_created)
values (2, 'Sent', current_timestamp );
insert into orders(account_id, status, order_created)
values (3, 'Accepted', current_timestamp );

insert into order_product(order_id, product_name, amount, price)
values (1, 'Mindless Voodoo Cayuga II', 1, 195);
insert into order_product(order_id, product_name, amount, price)
values (2, 'Powerslide standard Man M', 1, 29);
insert into order_product(order_id, product_name, amount, price)
values (2, 'Wicked SUS Rustproof', 4, 73.90);
insert into order_product(order_id, product_name, amount, price)
values (3, 'SEBA FR1 80 Grey', 2, 176.26);
insert into order_product(order_id, product_name, amount, price)
values (3, 'Ennui City Brace', 2, 39);
insert into order_product(order_id, product_name, amount, price)
values (4, 'Mindless Voodoo Lakota DT', 1, 171.27);