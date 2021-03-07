/*definicion del estado inicial*/
inicial(estado(0, 0)).

/*definicion de los estados objetivo, dado que ahora los L buscados cambian, debemos darselo al predicado objetivo*/
objetivo(estado(B1, _), L):-  B1 = L.
objetivo(estado(_, B2), L):-  B2 = L.

/*definicion de operadores*/
/*Ahora los operadores reciben las capacidades dadas en la consulta para poder operar con ellas*/

/*llenar B1 comprueba que no esta llena y si no lo esta la pone a C1*/
movimiento(estado(B1, B2), estado(NB1, B2), 'Llenar B1', C1, _, _) :- 
					  (B1 < C1), (NB1 is C1).

/*llenar B2 comprueba que no esta llena y si no lo esta la pone a C2*/
movimiento(estado(B1, B2), estado(B1, NB2), 'Llenar B2', _, C2, _) :- 
                      (B2 < C2), (NB2 is C2).

/*Vaciar B1 comprueba que no esta vacia y si no lo esta pone su contenido a 0*/
movimiento(estado(B1, B2), estado(NB1, B2), 'Vaciar B1', _, _, _) :- 
                      (B1 > 0), (NB1 is 0).

/*Vaciar B2 comprueba que no esta vacia y si no lo esta pone su contenido a 0*/
movimiento(estado(B1, B2), estado(B1, NB2), 'Vaciar B2', _, _, _) :- 
                      (B2 > 0), (NB2 is 0).

/*Verter B2 en B1 comprueba que B2 no esta vacia que B1 no esta llena y si se cumple,
si cabe todo el contenido de B2 en B1, B2 pasa a ser 0 y B1 = B1 + B2, si no cabe todo,
se actualiza el contenido de ambas correspondientemente*/
movimiento(estado(B1, B2), estado(NB1, NB2), 'Verter B2- B1', C1, _, _) :- 
					  (B2 > 0), (B1 < C1), 
                      ((B2 =< C1- B1) -> (NB2 is 0, NB1 is B2 + B1); (NB2 is B2 - (C1 - B1), NB1 is C1)).

/*Verter B1 en B2 comprueba que B1 no esta vacia que B2 no esta llena y si se cumple,
si cabe todo el contenido de B1 en B2, B1 pasa a ser 0 y B2 = B1 + B2, si no cabe todo,
se actualiza el contenido de ambas correspondientemente*/
movimiento(estado(B1, B2), estado(NB1, NB2), 'Verter B1- B2', _, C2, _) :- 
	                  (B1 > 0), (B2 < C2), 
                      ((B1 =< C2- B2) -> (NB1 is 0, NB2 is B2 + B1); (NB1 is B1 - (C2 - B2), NB2 is C2)).


/*Estas instrucciones activan la consulta, se trata del esquema general con control de repeticiones, sin coste y predicado consulta*/
/*Estos predicados reciben ahora las variables de entrada C1, C2, L para poder trabajar con ellas y dar la solucion al problema planteado*/
puede(Estado, Visitados, [], [Estado], _, _, L) :- 
objetivo(Estado, L), nl, write('Visitados: '), write(Visitados).

puede(Estado, Visitados, [Operador|Operadores], [Estado|EstadosCamino] , C1, C2, L) :- 
movimiento(Estado, EstadoSig, Operador, C1, C2, L), 
\+ member(EstadoSig, Visitados), 
puede(EstadoSig, [EstadoSig|Visitados], Operadores, EstadosCamino, C1, C2, L).

consulta(C1, C2, L) :-  
write('B1 = '), write(C1), write(', B2 = '), write(C2), write(' y L = '), write(L),
inicial(Estado), puede(Estado, [Estado], Operadores, Estados, C1, C2, L), nl, 
write('SOLUCION ENCONTRADA sin repeticion de estados: '), nl, 
write(Operadores), nl, write('Estados camino: '), nl, write(Estados).

