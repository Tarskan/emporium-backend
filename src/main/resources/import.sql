INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat) VALUES ('1', 'Tarskan', 'Sous-chef', 'killeur', 'champion');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat) VALUES ('2', 'Yumeko', 'Larbin', 'killeur', 'champion');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat) VALUES ('3', 'Riri', 'Adjoint', 'killeur', 'champion');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat) VALUES ('4', 'Fifi', 'Admin', 'killeur', 'champion');
INSERT INTO Utilisateur(UWUid, pseudo, grade, equipe, resultat) VALUES ('5', 'Loulou', 'chef', 'killeur', 'champion');

INSERT INTO Auteur(idAuteur, name) VALUES ('1', 'Amon Amarth');
INSERT INTO Auteur(idAuteur, name) VALUES ('2', 'Spirit Box');
INSERT INTO Auteur(idAuteur, name) VALUES ('3', 'Ramstein');
INSERT INTO Editeur(idEditeur, name) VALUES ('1', 'Napalm Record');
INSERT INTO Editeur(idEditeur, name) VALUES ('2', 'Tourbillon');
INSERT INTO Editeur(idEditeur, name) VALUES ('3', 'Test');
INSERT INTO Genre(idGenre, name) VALUES ('1', 'Death metal');
INSERT INTO Type(idType, name) VALUES ('1', 'Groupe de metal');
INSERT INTO Type(idType, name) VALUES ('2', 'Jeux vidéo');
INSERT INTO Support(idSupport, name) VALUES ('1', 'DVD');
INSERT INTO Support(idSupport, name) VALUES ('2', 'CD');
INSERT INTO Support(idSupport, name) VALUES ('3', 'Vinyle');
INSERT INTO Support(idSupport, name) VALUES ('4', 'Cassette');

INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, Image, idType, idAuteur, idEditeur, idSupport, idGenre, countFav) VALUES ('1', 'Twilight of the thunder god', 'Twilight of the thunder god', 'Musique de metal', 'https://m.media-amazon.com/images/I/51DLn0GEJpL.jpg', '1', '1', '1', '2', '1', 3);
INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, Image, idType, idAuteur, idEditeur, idSupport, idGenre, countFav) VALUES ('2', 'Holly Roler', 'null', 'Musique de metal', 'https://www.popnmusic.fr/wp-content/uploads/2020/08/cwe0ssz9ylc.jpg', '1', '2', '2', '2', '1', 2);
INSERT INTO Oeuvres(IdOeuvre, Titre, SousTitre, Description, Image, idType, idAuteur, idEditeur, idSupport, idGenre, countFav) VALUES ('3', 'Sonne', 'null', 'Musique de metal', 'https://www.lacoccinelle.net/587118-1.jpg?20191117', '1', '3', '3', '3', '1', 0);

INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('1', '1', '1', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('6', '1', '2', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('5', '2', '1', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('2', '2', '2', false);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('3', '3', '1', true);
INSERT INTO Collection(idCollection, UWUid, idOeuvre, favorite) VALUES ('4', '2', '1', false);

INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike) VALUES ('1', '1', '1', 'texte de test', 2, 1);
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike) VALUES ('2', '2', '1', 'texte de test', 1, 8);
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike) VALUES ('3', '3', '1', 'texte de test', 10, 18);
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike) VALUES ('4', '1', '2', 'texte de test', 70, 14);
INSERT INTO Commentaire(idCommentaire, UWUid, idOeuvre, text, nbLike, nbDislike) VALUES ('5', '2', '3', 'texte de test', 20, 1);
