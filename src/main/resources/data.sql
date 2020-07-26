/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cleme
 * Created: 4 juil. 2020
 */

/* Prod */
INSERT INTO categorie (id, active, libelle, position, parent_id, image_categorie) VALUES 
(1, 1, 'Formations', 0, NULL, 0),
(2, 1, 'Expériences Professionnelles', 1, NULL, 0),
(3, 1, 'Compétences informatiques', 2, NULL, 0),
(4, 1, 'Langues étrangères', 3, NULL, 0),
(5, 1, 'Centres d''intérêts', 4, NULL, 0),
(6, 1, 'Informations complémentaires', 5, NULL, 0),
(7, 1, 'Langages', 0 ,3, 1),
(8, 1, 'Frameworks', 1, 3, 1);

INSERT INTO ligne (id, active, contenu, date_debut, date_fin, image, lien, note, position, categorie_id) VALUES 
(1, 1, 'Formation "développeur web et mobile, JavaScript / J2EE" de 5 mois à la Wild Code School de La Loupe', '2019-01-01', NULL, NULL, NULL, NULL, 0, 1),
(2, 1, 'Brevet de Technicien Supérieur, Système Numérique Informatique et Réseaux (À Évreux – Lycée Modeste Leroy)', '2018-01-01', NULL, NULL, NULL, NULL, 1, 1),
(3, 1, 'BAC S Option Maths (À Évreux – Lycée Aristide Briand)', '2013-01-01', NULL, NULL, NULL, NULL, 2, 1),

(4, 1, 'CDI Développeur web junior à Trésor du Patrimoine', '2019-12-13', NULL, NULL, NULL, NULL, 0, 2),
(5, 1, 'Mission intérimaire au Centre de Recette de la CPAM de l''Eure, à Évreux.', '2019-07-22', '2019-10-11', NULL, NULL, NULL, 1, 2),
(6, 1, 'Stage au Centre De Recette de la CPAM de l''Eure, à Évreux.', '2019-03-04', '2019-05-31', NULL, NULL, NULL, 2, 2),
(7, 1, 'Stage de 2 mois au sein de « Computers For Charities », à Hailsham en Angleterre.', '2017-05-08', '2017-07-07', NULL, NULL, NULL, 3, 2),

(8, 1, 'Anglais - Niveau B2', NULL, NULL, NULL, NULL, 4, 0, 4),

(9, 1, 'Culture générale', NULL, NULL, NULL, NULL, NULL, 0, 5),
(10, 1, 'Randonnée pédestre', NULL, NULL, NULL, NULL, NULL, 1, 5),
(11, 1, 'Tennis de table', NULL, NULL, NULL, NULL, NULL, 2, 5),
(12, 1, 'Programmation (raspberry PI)', NULL, NULL, NULL, NULL, NULL, 3, 5),

(13, 1, 'Initiation aux premiers secours', NULL, NULL, NULL, NULL, NULL, 0, 6), 
(14, 1, 'Permis B et véhicule', NULL, NULL, NULL, NULL, NULL, 1, 6),

(15, 1, 'Java', NULL, NULL, 'assets/cv/logo/java.png', NULL, 5, 0, 7),
(16, 1, 'Php', NULL, NULL, 'assets/cv/logo/php.png', NULL, 5, 1, 7),
(17, 1, 'C/C++', NULL, NULL, 'assets/cv/logo/c++.png', NULL, 2, 2, 7),
(18, 1, 'Html', NULL, NULL, 'assets/cv/logo/html.png', NULL, 4, 3, 7),
(19, 1, 'Css', NULL, NULL, 'assets/cv/logo/css.png', NULL, 3, 4, 7),
(20, 1, 'Javascript', NULL, NULL, 'assets/cv/logo/js.png', NULL, 5, 5, 7),
(21, 1, 'Sql', NULL, NULL, 'assets/cv/logo/sql.png', NULL, 4, 6, 7),

(22, 1, 'Spring', NULL, NULL, 'assets/cv/logo/spring.png', NULL, 4, 0, 8),
(23, 1, 'Symfony', NULL, NULL, 'assets/cv/logo/symfony.png', NULL, 4, 1, 8),
(24, 1, 'Qt', NULL, NULL, 'assets/cv/logo/qt.png', NULL, 2, 2, 8),
(25, 1, 'Bootstrap', NULL, NULL, 'assets/cv/logo/bootstrap4.png', NULL, 4, 3, 8),
(26, 1, 'Angular', NULL, NULL, 'assets/cv/logo/angular.png', NULL, 3, 4, 8),
(27, 1, 'JQuery', NULL, NULL, 'assets/cv/logo/jquery.png', NULL, 5, 5, 8),
(28, 1, 'Sass', NULL, NULL, 'assets/cv/logo/sass.png', NULL, 3, 7, 8),
(29, 1, 'Prestashop', NULL, NULL, 'assets/cv/logo/prestashop.png', NULL, 3, 7, 8);