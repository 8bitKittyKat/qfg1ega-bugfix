;;; Sierra Script 1.0 - (do not remove this comment)
(script# 341)
(include game.sh)
(use Main)

(public
	DagNabItHelp 0
)

(procedure (DagNabItHelp)
	(HighPrint 341 0)
	;The game is called Dag-Nab-It.  You take turns with the Chief Thief throwing daggers at the board.
	;Each player gets three turns, and throws three daggers in each turn.
	(HighPrint 341 1)
	;More points are scored the closer each dagger hits to the center of the board.  Highest score wins the bet.
	(HighPrint 341 2)
	;Adjust force and angle of throw with the arrow keys or mouse, according to your hand position.
	;Press Enter or click on the small dag-nab-it board to throw.
	(DisposeScript 341)
)
