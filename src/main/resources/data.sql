/* Prod */
INSERT INTO categorie (id, active, libelle, position, parent_id, image_categorie) VALUES 
(1, 1, 'Formations', 0, NULL, 0),
(2, 1, 'Expériences Professionnelles', 1, NULL, 0),
(3, 1, 'Compétences informatiques', 2, NULL, 0),
(4, 1, 'Langues étrangères', 3, NULL, 0),
(5, 1, 'Centres d''intérêts', 4, NULL, 1),
(6, 1, 'Informations complémentaires', 5, NULL, 0),
(7, 1, 'Langages', 0 ,3, 1),
(8, 1, 'Frameworks', 1, 3, 1);

INSERT INTO ligne (id, active, contenu, date_debut, date_fin, image, lien, note, position, categorie_id, only_year_date) VALUES 
(1, 1, 'Formation "développeur web et mobile, JavaScript / J2EE" de 5 mois à la Wild Code School de La Loupe', '2019-01-01', NULL, NULL, NULL, NULL, 0, 1, 1),
(2, 1, 'Brevet de Technicien Supérieur, Système Numérique Informatique et Réseaux (À Évreux – Lycée Modeste Leroy)', '2018-01-01', NULL, NULL, NULL, NULL, 1, 1, 1),
(3, 1, 'BAC S Option Maths (À Évreux – Lycée Aristide Briand)', '2013-01-01', NULL, NULL, NULL, NULL, 2, 1, 1),

(4, 1, 'CDI Développeur web junior à Trésor du Patrimoine', '2019-12-13', NULL, NULL, NULL, NULL, 0, 2, 0),
(5, 1, 'Mission intérimaire au Centre de Recette de la CPAM de l''Eure, à Évreux.', '2019-07-22', '2019-10-11', NULL, NULL, NULL, 1, 2, 0),
(6, 1, 'Stage au Centre De Recette de la CPAM de l''Eure, à Évreux.', '2019-03-04', '2019-05-31', NULL, NULL, NULL, 2, 2, 0),
(7, 1, 'Stage de 2 mois au sein de « Computers For Charities », à Hailsham en Angleterre.', '2017-05-08', '2017-07-07', NULL, NULL, NULL, 3, 2, 0),

(8, 1, 'Anglais - Niveau B2', NULL, NULL, NULL, NULL, 4, 0, 4, 0),

(9, 1, 'Culture générale', NULL, NULL, 'assets/cv/photos/culture-generale.jpg', NULL, NULL, 0, 5, 0),
(10, 1, 'Randonnée pédestre', NULL, NULL, 'assets/cv/photos/randonnee.jpg', NULL, NULL, 1, 5, 0),
(11, 1, 'Tennis de table', NULL, NULL, 'assets/cv/photos/table-tennis.jpg', NULL, NULL, 2, 5, 0),
(12, 1, 'Programmation (Raspberry PI)', NULL, NULL, 'assets/cv/photos/raspberry-pi.jpg', NULL, NULL, 3, 5, 0),

(13, 1, 'Initiation aux premiers secours', NULL, NULL, NULL, NULL, NULL, 0, 6, 0), 
(14, 1, 'Permis B et véhicule', NULL, NULL, NULL, NULL, NULL, 1, 6, 0),

(15, 1, 'Java', NULL, NULL, 'assets/cv/logo/java.png', NULL, 5, 0, 7, 0),
(16, 1, 'Php', NULL, NULL, 'assets/cv/logo/php.png', NULL, 5, 1, 7, 0),
(17, 1, 'C/C++', NULL, NULL, 'assets/cv/logo/c++.png', NULL, 2, 2, 7, 0),
(18, 1, 'Html', NULL, NULL, 'assets/cv/logo/html.png', NULL, 4, 3, 7, 0),
(19, 1, 'Css', NULL, NULL, 'assets/cv/logo/css.png', NULL, 3, 4, 7, 0),
(20, 1, 'Javascript', NULL, NULL, 'assets/cv/logo/js.png', NULL, 5, 5, 7, 0),
(21, 1, 'Sql', NULL, NULL, 'assets/cv/logo/sql.png', NULL, 4, 6, 7, 0),

(22, 1, 'Spring', NULL, NULL, 'assets/cv/logo/spring.png', NULL, 4, 0, 8, 0),
(23, 1, 'Symfony', NULL, NULL, 'assets/cv/logo/symfony.png', NULL, 4, 1, 8, 0),
(24, 1, 'Qt', NULL, NULL, 'assets/cv/logo/qt.png', NULL, 2, 2, 8, 0),
(25, 1, 'Bootstrap', NULL, NULL, 'assets/cv/logo/bootstrap4.png', NULL, 4, 3, 8, 0),
(26, 1, 'Angular', NULL, NULL, 'assets/cv/logo/angular.png', NULL, 3, 4, 8, 0),
(27, 1, 'JQuery', NULL, NULL, 'assets/cv/logo/jquery.png', NULL, 5, 5, 8, 0),
(28, 1, 'Sass', NULL, NULL, 'assets/cv/logo/sass.png', NULL, 3, 7, 8, 0),
(29, 1, 'Prestashop', NULL, NULL, 'assets/cv/logo/prestashop.png', NULL, 3, 7, 8, 0);

INSERT INTO user (id, username, password, role) VALUES 
(1, 'clement', '$2y$10$OBzIOVEBim42YJAx4p9LGuLJyz2s8EHNnATxxwfN2oxEiT/VMKO12', 'USER');

ALTER SEQUENCE Categorie_SEQ RESTART WITH 9;
ALTER SEQUENCE Ligne_SEQ RESTART WITH 30;
/* ALTER TABLE user ALTER COLUMN id RESTART WITH 1; */
