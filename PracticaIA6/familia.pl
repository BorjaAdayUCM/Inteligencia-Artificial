persona(juan, hombre).
persona(maria, mujer).
persona(rosa, mujer).
persona(luis, hombre).
persona(jose, hombre).
persona(laura, mujer).
persona(pilar, mujer).
persona(miguel, hombre).
persona(isabel, mujer).
persona(jaime, hombre).
persona(pedro, hombre).
persona(pablo, hombre).
persona(begona, mujer).

progenitores(juan, maria, rosa).
progenitores(maria, juan, luis).
progenitores(jose, laura, pilar).
progenitores(pilar, luis, miguel).
progenitores(miguel, isabel, jaime).
progenitores(pedro, rosa, pablo).
progenitores(pedro, rosa, begona).

/*Dado que el nombre puede aparecer en cualquiera de las dos posiciones, implementamos un predicado
progenitor(X, Y) que nos devuelve si X es progenitor de Y independientemente del lugar que ocupe*/
progenitor(X, Y):- progenitores(X, _, Y).
progenitor(X, Y):- progenitores(_, X, Y).

/*Este predicado solo comprueba que X sea progenitor de Y y que X sea del sexo que pide el predicado*/
padre(X, Y):- progenitor(X, Y), persona(X, hombre).
madre(X, Y):- progenitor(X, Y), persona(X, mujer).

/*Estos predicados comprueban que Y sea progenitor de X, ya que si esto ocurre, X es hijo o hija de Y
y comprueba que X sea del sexo que solicita el predicado*/
hijo(X, Y):- progenitor(Y, X), persona(X, hombre).
hija(X, Y):- progenitor(Y, X), persona(X, mujer).

/*Comprobamos que exista un progenitor Z comun para X e Y siendo X e y diferentes y solicitando que X sea del sexo solicitado*/
hermano(X, Y):- progenitor(Z, X), progenitor(Z, Y), X\=Y, persona(X, hombre).
hermana(X, Y):- progenitor(Z, X), progenitor(Z, Y), X\=Y, persona(X, mujer).

/*Este predicado exige que haya un X progenitor de Z y que Z sea progenitor de Y siendo X del sexo solicitado en la consulta*/
abuelo(X, Y):- padre(X, Z), (padre(Z, Y) ; madre(Z, Y)).
abuela(X, Y):- madre(X, Z), (padre(Z, Y) ; madre(Z, Y)).

/*comprueban que existan dos hermanos o hermanas Z1 y Z2 tal que Z1 o Z2 sea padre o madre de X y Z1 o Z2 sea madre de Y, 
exigiendo además que X sea hombre o mujer dependiendo del predicado primo o prima.*/
primo(X, Y):- (hermano(Z1, Z2) ; hermana(Z1, Z2)), (padre(Z1, X) ; madre(Z1, X)), (padre(Z2, Y) ; madre(Z2, Y)), persona(X, hombre).
prima(X, Y):- (hermano(Z1, Z2) ; hermana(Z1, Z2)), (padre(Z1, X) ; madre(Z1, X)), (padre(Z2, Y) ; madre(Z2, Y)), persona(X, mujer).

/*ascendiente se implementa de forma recursiva, el caso base es que X sea padre de Y, si esto no se cumple entramos en el caso recursivo
que comprueba que haya un padre o madre Z de Y tal que X sea ahora ascendiente de Z*/
ascendiente(X, Y):- (padre(X, Y) ; madre(X, Y)).
ascendiente(X, Y):- (padre(Z, Y) ; madre(Z, Y)), ascendiente(X, Z).