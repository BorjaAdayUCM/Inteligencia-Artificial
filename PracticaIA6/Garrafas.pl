/*definicion del estado inicial*/
inicial(estado(0, 0)).

/*definicion de los estados objetivo*/
objetivo(estado(2, _)).
objetivo(estado(_, 2)). 

/*definicion de operadores*/

/*llenar B1 comprueba que no esta llena y si no lo esta la pone a su maxima capacidad*/
movimiento(estado(B1, B2), estado(NB1, B2), 'Llenar B1') :-
                        (B1 < 4), (NB1 is 4).

/*llenar B2 comprueba que no esta llena y si no lo esta la pone a su maxima capacidad*/
movimiento(estado(B1, B2), estado(B1, NB2), 'Llenar B2') :-
                       (B2 < 3),(NB2 is 3).

/*Vaciar B1 comprueba que no esta vacia y si no lo esta pone su contenido a 0*/
movimiento(estado(B1, B2), estado(NB1, B2), 'Vaciar B1') :-
                      (B1 > 0),(NB1 is 0).

/*Vaciar B2 comprueba que no esta vacia y si no lo esta pone su contenido a 0*/
movimiento(estado(B1, B2), estado(B1, NB2), 'Vaciar B2') :-
                      (B2 > 0), (NB2 is 0).

/*Verter B2 en B1 comprueba que B2 no esta vacia que B1 no esta llena y si no lo esta actualiza el contenido de ambas*/
movimiento(estado(B1, B2), estado(NB1, NB2), 'Verter B2-B1') :-
					  (B2 > 0), (B1 < 4),
                      ((B2 =< 4-B1) -> (NB2 is 0,NB1 is B2+B1); (NB2 is B2-(4-B1),NB1 is 4)).

/*Verter B1 en B2 comprueba que B1 no esta vacia que B2 no esta llena y si no lo esta actualiza el contenido de ambas*/
movimiento(estado(B1, B2), estado(NB1, NB2), 'Verter B1-B2') :-
					  (B1 > 0), (B2 < 3),
                      ((B1 =< 3-B2) -> (NB1 is 0,NB2 is B2+B1); (NB1 is B1-(3-B2),NB2 is 3)).


/*Estas instrucciones activan la consulta, se trata del esquema general con control de repeticiones, sin coste y predicado consulta*/
puede(Estado,Visitados, [],[Estado]) :-
objetivo(Estado), nl, write('Visitados: '), write(Visitados).

puede(Estado,Visitados, [Operador|Operadores],[Estado|EstadosCamino] ) :-
movimiento(Estado, EstadoSig, Operador),
\+ member(EstadoSig, Visitados),
puede(EstadoSig,[EstadoSig|Visitados], Operadores, EstadosCamino).

consulta :- write('B1 = 4L y B2 = 3L'),
inicial(Estado), puede(Estado,[Estado], Operadores,Estados), nl,
write('SOLUCION ENCONTRADA sin repeticion de estados: '), nl,
write(Operadores), nl, write('Estados camino: '), nl, write(Estados).
