INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat, creationDate, modificationDate) VALUES ('1', 'Tarskan', 'Sous-chef', 'killeur', 'champion', '2023-05-01', '2023-05-02');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat, creationDate, modificationDate) VALUES ('2', 'Yumeko', 'Larbin', 'killeur', 'champion','2023-05-01', '2023-05-01');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat, creationDate, modificationDate) VALUES ('3', 'Riri', 'Adjoint', 'killeur', 'champion', '2023-05-03', '2023-05-04');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat, creationDate, modificationDate) VALUES ('4', 'Fifi', 'Admin', 'killeur', 'champion', '2023-05-03', '2023-05-03');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat, creationDate, modificationDate) VALUES ('5', 'Loulou', 'chef', 'killeur', 'champion', '2023-05-02', '2023-05-02');

INSERT INTO Auteur(idAuteur, name) VALUES ('1', 'Amon Amarth');
INSERT INTO Auteur(idAuteur, name) VALUES ('2', 'Spirit Box');
INSERT INTO Auteur(idAuteur, name) VALUES ('3', 'Ramstein');
INSERT INTO Auteur(idAuteur, name) VALUES ('4', 'Korn');
INSERT INTO Auteur(idAuteur, name) VALUES ('5', 'Slayer');
INSERT INTO Editeur(idEditeur, name) VALUES ('1', 'Napalm Record');
INSERT INTO Editeur(idEditeur, name) VALUES ('2', 'Tourbillon');
INSERT INTO Editeur(idEditeur, name) VALUES ('3', 'Test');
INSERT INTO Genre(idGenre, name) VALUES ('1', 'Death metal');
INSERT INTO Type(idType, name) VALUES ('1', 'Musique');
INSERT INTO Type(idType, name) VALUES ('2', 'Jeux vidéo');
INSERT INTO Support(idSupport, name) VALUES ('1', 'DVD');
INSERT INTO Support(idSupport, name) VALUES ('2', 'CD');
INSERT INTO Support(idSupport, name) VALUES ('3', 'Vinyle');
INSERT INTO Support(idSupport, name) VALUES ('4', 'Cassette');

INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, imagePath, idType, idAuteur, idEditeur, idSupport, idGenre, countFav, creationDate, modificationDate) VALUES ('1', 'Twilight of the thunder god', 'Twilight of the thunder god', 'Musique de metal', 'https://m.media-amazon.com/images/I/51DLn0GEJpL.jpg', '1', '1', '1', '2', '1', 3, '2023-04-30', '2023-05-02');
INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, imagePath, idType, idAuteur, idEditeur, idSupport, idGenre, countFav, creationDate, modificationDate) VALUES ('2', 'Holly Roler', 'null', 'Musique de metal', 'https://www.popnmusic.fr/wp-content/uploads/2020/08/cwe0ssz9ylc.jpg', '1', '2', '2', '2', '1', 2, '2023-04-24', '2023-04-24');
INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, imagePath, idType, idAuteur, idEditeur, idSupport, idGenre, countFav, creationDate, modificationDate) VALUES ('3', 'Sonne', 'null', 'Musique de metal', 'https://www.lacoccinelle.net/587118-1.jpg?20191117', '1', '3', '3', '3', '1', 0, '2023-04-16', '2023-04-16');
INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, imagePath, idType, idAuteur, idEditeur, idSupport, idGenre, countFav, creationDate, modificationDate) VALUES ('4', 'Hater', 'null', 'Musique de metal', 'https://upload.wikimedia.org/wikipedia/en/2/2d/%22Hater%2C_artwork_for_Korn_single%2C_June_2014.jpg%22.jpg', '1', '4', '3', '3', '1', 0, '2023-03-16', '2023-04-18');
INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, imagePath, idType, idAuteur, idEditeur, idSupport, idGenre, countFav, creationDate, modificationDate) VALUES ('5', 'Raining blood', 'null', 'Musique de metal', 'https://m.media-amazon.com/images/I/81wW0qxswYL._SY450_.jpg', '1', '5', '3', '3', '1', 0, '2023-02-16', '2023-05-16');

INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('1', '1', '1', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('6', '1', '2', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('5', '2', '1', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('2', '2', '2', false);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('3', '3', '1', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('4', '2', '1', false);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('7', '4', '3', false);

INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike, creationDate, modificationDate) VALUES ('1', '1', '1', 'texte de test', 2, 1, '2023-05-02', '2023-05-02');
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike, creationDate, modificationDate) VALUES ('2', '2', '1', 'texte de test', 1, 8, '2023-05-03', '2023-05-03');
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike, creationDate, modificationDate) VALUES ('3', '3', '1', 'texte de test', 10, 18, '2023-05-04', '2023-05-02');
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike, creationDate, modificationDate) VALUES ('4', '1', '2', 'texte de test', 70, 14, '2023-05-15', '2023-05-02');
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike, creationDate, modificationDate) VALUES ('5', '2', '3', 'texte de test', 20, 1, '2023-05-06', '2023-05-06');
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike, creationDate, modificationDate) VALUES ('6', '3', '3', 'texte de test', 20, 1, '2023-05-07', '2023-05-02');
