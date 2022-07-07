DROP DATABASE IF EXISTS el_rincon_del_tragon_db;
CREATE DATABASE el_rincon_del_tragon_db CHARACTER SET utf8 COLLATE utf8_general_ci;
USE el_rincon_del_tragon_db;

-- Drop Tables
        
DROP TABLE IF EXISTS users; 
   
CREATE TABLE users (
                            
	email VARCHAR(100) NOT NULL PRIMARY KEY,                                       
	password VARCHAR(100) NOT NULL,
	firstname VARCHAR(100) NOT NULL,
	lastname VARCHAR(100) NOT NULL
    -- days_of_password_validity INTEGER NOT NULL, -- En dias
	-- date_of_last_password_update TIMESTAMP NOT NULL,
	-- is_temporal_password BOOLEAN NOT NULL,
	-- activation_key VARCHAR(50),
	-- status VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes (
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	recipe_creator_email VARCHAR(100) NOT NULL,
	name VARCHAR(100) NOT NULL,
	description VARCHAR(250) NOT NULL,
	no_people INTEGER NOT NULL,
	ingredient_list VARCHAR(2500) NOT NULL,
	recipe_procedure VARCHAR(2500) NOT NULL,
	image VARCHAR(50) NOT NULL,
	rating INTEGER NOT NULL,
	

	FOREIGN KEY (recipe_creator_email) REFERENCES users(email)
);

DROP TABLE IF EXISTS rating_recipe;

CREATE TABLE rating_recipe (
	id_recipe INTEGER NOT NULL,
	email_user VARCHAR(100) NOT NULL,
	rating INTEGER NOT NULL,

	FOREIGN KEY (id_recipe) REFERENCES recipes(id),
	FOREIGN KEY (email_user) REFERENCES users(email)
);

INSERT INTO users VALUES ("admin@elrincondeltragon.net", "passw0rd", "Admin", "");
INSERT INTO users VALUES ("guillermart@gmail.com", "passw0rd", "Guillermo", "Martinez");
INSERT INTO users VALUES ("rebeca.strempler@gmail.com", "passw0rd", "Rebeca", "Strempler");
INSERT INTO users VALUES ("anton.corbijn@gmail.com", "passw0rd", "Anton", "Corbijn");
/*usuarios nuevos*/
INSERT INTO users VALUES ("ivan_alamos@outlook.com", "ivndot", "Ivan", "Alamos");
INSERT INTO users VALUES ("marco_epifanio@gmail.com", "passw0rd", "Marco", "Epifanio");

INSERT INTO recipes (recipe_creator_email, name, description, no_people, ingredient_list, recipe_procedure, image, rating)
VALUES( 
"guillermart@gmail.com",
"Pizza Diavola", 
"Pizza con pepperoni y jitomate, y un ligero y delicioso sabor picante.", 
8,
"1 lámina de masa de pizza; 2 pimientos; 3 cucharadas de Salsa de tomate; 12 lonchas de salami; Mozarella rallada; 1 pizca de pimienta blanca o negra; 1 pizca de cayena molida", 
"Calentamos el horno a 220 grados.;En una superficie lisa y fría, como puede ser la encimera o una mesa de cocina, estiramos la masa de pizza con ayuda de un rodillo untado de harina. 
;Cuando tenemos la masa de la pizza del grosor deseado la colocamos sobre papel para horno y comenzamos a rellenar la pizza diavola.
;Esparcimos la salsa de tomate y luego rociamos ahora con la pimienta y la cayena molida y rematamos con mucho queso rallado.
;Cuando esté lista para meter al horno añadimos el salami.
;Bajamos el horno a 180 grados y horneamos la pizza diavola (porque pica y porque está bastante sabrosa) durante unos 40 minutos o hasta que la masa esté crujiente.",
"pizza.jpg",
3 );

INSERT INTO recipes (recipe_creator_email, name, description, no_people, ingredient_list, recipe_procedure, image, rating)
VALUES( 
"rebeca.strempler@gmail.com",
"Sopes Tricolor", 
"Deliciosos sopes con frijol, pollo, chorizo verde y chorizo rojo.",
12,
"1 aguacate; 1 chile serrano ;3 tomates verdes ;1/3 de cebolla picada ;2 jitomates ;2 guajillos hidratados; 1 diente de ajo ;1/2 cucharadita de orégano;12 sopes medianos ;1 taza de frijoles refritos ;2 tazas de pollo deshebrado ;1 taza de lechuga rallada ;1 taza de queso doble crema ;1/2 taza de crema", 
"Para la salsa verde: licúa el aguacate con el chile serrano y los tomates.;Mezcla la cebolla y salpimienta a tu gusto.; Reserva.
Para la salsa roja: asa los jitomates, licúalos con el guajillo, el ajo y el orégano, salpimienta y reserva.
;Calienta los sopes. ;Úntales los frijoles, coloca el pollo, la lechuga, el queso y la crema.
;Termina con las salsas y sirve.",
"sopes.jpg",
4 );

INSERT INTO recipes (recipe_creator_email, name, description, no_people, ingredient_list, recipe_procedure, image, rating)
VALUES(  
"rebeca.strempler@gmail.com",
"Hamburguesas Barbecue", 
"Hamburguesas de sirloin con tocino aderezadas con una deliciosa salsa barbecue New York",
8,
"750 gramos de carne de res molida, para la carne;1/2 cebollas, finamente picada, para la carne
;2 dientes de ajo, finamente picado, para la carne;3 cucharadas de perejil, finamente picado, para la carne
;4 cucharadas de queso parmesano, rallado, para la carne;2 cucharadas de mostaza, para la carne
;1 huevo, para la carne;1 cucharada de sal, para la carne
;4 cucharaditas de aceite, para la carne;1/2 tazas de salsa de tomate, para la salsa
;11/2 cucharadas de azúcar, para la salsa;1 cucharada de salsa de soya, para la salsa
;1 cucharada de salsa inglesa, para la salsa;1/2 cucharadas de paprika, para la salsa
;2 cucharadas de vinagre de manzana, para la salsa;1/2 cucharaditas de sal, para la salsa
;4 hojas de lechuga;1 aguacate, rebanado;4 panes para hamburguesa", 
"Para la carne, mezcla todos los ingredientes y forma hamburguesas con las manos.
;Combina todos los ingredientes de la salsa hasta obtener una mezcla homogénea. Reserva.
;Calienta el aceite a fuego medio en un sartén y fríe cada hamburguesa hasta que esté cocida, bañando con la salsa bbq.
;Arma las hamburguesas colocando la carne en el pan sobre lechuga y el aguacate. Sirve.",
"hamburguesa.jpg",
3 );

INSERT INTO recipes (recipe_creator_email, name, description, no_people, ingredient_list, recipe_procedure, image, rating)
VALUES( 
"anton.corbijn@gmail.com",
"Pasta con cerdo",
"En una tarde fr&iacute;a, este plato picante realmente proporciona calor. Es un divertido cambio a los espaguetis.",
4,
"450 g chorizo de cerdo picante; 1 cebolla mediana, picada; 1/2 taza de pimiento verde picado;410 g de tomates estofados;225 g de salsa de tomate; 1 taza de pasta de espiral sin cocer;2 cucharadas de azucar morena; 1 a 2 cucharaditas de chile en polvo; 1 cucharadita de sal; Queso Parmesano, opcional",
"En una sart&eacute;n grande cocinar el chorizo a fuego medio hasta que ya no est&eacute; de color rosa.; Escurrir.; Agregar la cebolla y el pimiento verde y cocinar hasta que esten tiernos.; Añadir los tomates, la salsa de tomate, la pasta, el az&uacute;car morena, el chile en polvo y la sal.; Cubrir y cocinar a fuego lento durante 20 minutos o hasta que la pasta este tierna.; Espolvorear con queso parmesano si lo desea.",
"pasta_de_cerdo.jpg",
3 );

INSERT INTO recipes (recipe_creator_email, name, description, no_people, ingredient_list, recipe_procedure, image, rating)
VALUES( 
"anton.corbijn@gmail.com",
"Costilla de cerdo al lim&oacute;n",
"Deliciosa costilla de cerdo con un toque especial de lim&oacute;n",
6,
"1 kilo de costilla o/falda de cerdo; 1/2 cebolla cortada en cuadraditos; 4 tomates grandes cortados en cuadraditos;4 chiles güeros frescos cortados en rodajas; 6 limones; 3 dientes de ajo finamente picados; 1/2 kilo de papas cambray cocidas",
"Marinar la carne, con el jugo de limón, ajo y sal por 2 o 3 horas.;Poner la carne en una olla caliente con un poco de aceite.
;Freír hasta que se evapore todo el jugo.;En una olla aparte se fríe la cebolla.
;Cuando esté transparente se agregan los chiles y por último el tomate. Sazonar y dejar por unos 10 minutos.
;Luego agregar la carne y las papas.;Revolver bien para que se incorporen todos los ingredientes.
;Agregar 2 tazas de agua y dejar hervir por 15 minutos. Se puede acompañar con arroz blanco.",
"costilla_de_cerdo_al_limon.jpg",
3 );

INSERT INTO recipes (recipe_creator_email, name, description, no_people, ingredient_list, recipe_procedure, image, rating)
VALUES(
"ivan_alamos@outlook.com",
"Pasta al Horno a la Mexicana",
"Un delicioso cambio de las cazuelas de pasta comunes. Los fideos tirabuz&oacute;n hacen que sea m&aacute;s novedoso",
6,
"450 g de carne molida; 425 g de salsa de tomate, el pimiento verde y las especias; 1/4 taza de pimiento verde picado; 1 cucharadita de ajo en polvo; 1 cucharadita de orégano seco; 225 g sin cocer pasta espiral, hervidos y escurridos; 1 taza (115 g) de queso Cheddar rallado; 1/2 taza crema agria",
"En una sartén grande, cocinar la carne a fuego medio hasta que pierda el color rosado.;Escurrir.
;Agregar el condimento para tacos, la salsa de tomate, el pimiento verde y las especias
;Llevar a ebullición y luego retirar del fuego.Mientras tanto, mezclar la pasta, 1/2 taza de queso y la crema agria.
;Colocar en un molde para hornear engrasado.;Cubrir con la mezcla de carne y el queso restante.
Hornear, sin tapar, a 350 o durante 30 minutos o hasta que esté cocinado.",
"pasta_al_horno_a_la_mexicana.jpg",
3 ); 

INSERT INTO recipes (recipe_creator_email, name, description, no_people, ingredient_list, recipe_procedure, image, rating)
VALUES( 
"marco_epifanio@gmail.com",
"Pollo a la Crema",
"Esquisito pollo a la crema muy facil de preparar que sera de tus platillos favoritos",
2,
"1 pechuga de pollo; 3 rodajas de cebolla morada; 1 ajo; 1 chile dulce; 1 cebolla blanca; 2 a 3 chiles morrones verdes; 1 lata de elote (maíz); 2 latas o cartones de media crema (crema para cocción que no necesita refrigeración); 2 cubos de caldo de pollo; 1 cucharada de mantequilla;Sal al gusto",
"Para cocer la pechuga agregar la cebolla morada, el ajo y el chile dulce.;Agregar sal al gusto.
;Ya cocida, dejar enfriar y desmenuzar.;Guardar un poco de caldo.;Cortar en rodajas la cebolla blanca.;Cortar el chile en tiritas.
;Colocar la mantequilla en una sartén grande.;Agregar la cebolla y chile y sofreír a fuego lento.
;Posteriormente incorporar la carne desmenuzada.;Colocar el elote, sazonar y cocinar unos 10 minutos.
;Luego agregar los cubos de caldo de pollo hasta que se disuelvan, y media taza de agua.
;Dejar hervir por 5 minutos e incorporar la media crema.;Cocinar unos 5 minutos más y listo para servir. Se puede acompañar con totopos.",
"pollo_a_la_crema.jpg",
3 );

INSERT INTO rating_recipe VALUES (1,"ivan_alamos@outlook.com",4);
INSERT INTO rating_recipe VALUES (1,"marco_epifanio@gmail.com",2);
INSERT INTO rating_recipe VALUES (1,"rebeca.strempler@gmail.com",2);
INSERT INTO rating_recipe VALUES (2,"ivan_alamos@outlook.com",3);
INSERT INTO rating_recipe VALUES (2,"marco_epifanio@gmail.com",5);
INSERT INTO rating_recipe VALUES (2,"rebeca.strempler@gmail.com",3);
INSERT INTO rating_recipe VALUES (3,"ivan_alamos@outlook.com",5);
INSERT INTO rating_recipe VALUES (3,"marco_epifanio@gmail.com",3);
INSERT INTO rating_recipe VALUES (3,"rebeca.strempler@gmail.com",2);
