# Profil développeur Compose

## Description

Petite application Android en Jetpack Compose qui affiche une fiche « Profil développeur » (profil `kd63799`, développeur / DevOps en poste à Marseille et en master d'architecture logicielle).
La fiche montre un nom, un rôle, une courte description, une liste de compétences, un e-mail de contact et une disponibilité.
Deux actions sont possibles : une zone cliquable « disponibilité » et un bouton « Contacter ». Elles ne font rien pour l'instant, elles appellent juste un callback.
Tout le code est dans `MainActivity.kt`.

## Notions utilisées

- fonctions composables (`@Composable`)
- paramètres des composables
- `Modifier` avec paramètre `modifier: Modifier = Modifier`
- chaînage de modifiers
- padding externe / padding interne
- callbacks (`onClick: () -> Unit`)
- `Surface`, `Column`, `Text`, `Button`
- `Scaffold` et `innerPadding`
- accessibilité de base (`semantics`, `contentDescription`, `Role.Button`)
- `@Preview`

## Explication courte

1. **Composables créés** : `ProfilScreen`, `ProfileCard`, `ProfileHeader`, `SkillTag`, `InfoLine`, `AvailabilityZone`, `ContactButton` (7 en tout), plus deux previews.
2. **Écran principal** : `ProfilScreen`, appelé dans le `Scaffold` de `MainActivity`.
3. **Padding externe** : dans `ProfilScreen`, `ProfileCard(modifier = Modifier.padding(16.dp))` : c'est le parent qui place la fiche dans l'écran. On le retrouve aussi avec `innerPadding` du `Scaffold` et avec les `padding(top = ...)` donnés par `ProfileCard` à ses enfants.
4. **Padding interne** : dans `ProfileCard`, `Column(modifier = Modifier.padding(16.dp))` espace le contenu par rapport au bord de la fiche. Même principe dans `SkillTag` et `AvailabilityZone` (padding à l'intérieur du `Surface`).
5. **Callbacks** : `ContactButton` (`onClick`) et `AvailabilityZone` (`onClick`). Ils sont remontés jusqu'à `MainActivity`, qui contient pour l'instant des lambdas vides avec un commentaire : le composant signale l'événement mais ne décide de rien.
6. **Accessibilité** : `AvailabilityZone` est une zone cliquable personnalisée, donc j'ai ajouté `role = Role.Button` dans `clickable` et un `contentDescription` via `semantics` (« Disponible : … Appuyer pour voir les détails »). La chaîne `fillMaxWidth().clickable(...).semantics { ... }` a du sens : toute la largeur est cliquable, le lecteur d'écran annonce un bouton et sa description.
   Le `Button` de `ContactButton` est déjà explicite pour un lecteur d'écran : il a le rôle bouton et lit son texte sans rien ajouter.
7. **Réutilisabilité** : chaque composable reçoit ses données en paramètres (texte, liste de compétences, callbacks) et accepte un `modifier`, donc le parent choisit le placement. `SkillTag` est réutilisé pour chaque compétence, et les deux previews affichent deux profils différents (junior disponible / full-stack non disponible) avec les mêmes composables.

## Capture

![Preview du profil](docs/capture.png)

> À remplacer par une capture d'écran de la preview ou de l'application lancée (`docs/capture.png`).

## Lien Git

<!-- mettre ici l'URL du dépôt public -->
