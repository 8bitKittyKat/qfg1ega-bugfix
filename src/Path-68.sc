;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include game.sh)
(use Main)
(use Motion)
(use Game)

(public
	rm68 0
)

(instance rm68 of Room
	(properties
		picture 68
		style DISSOLVE
		horizon 30
		west 67
	)
	
	(method (init)
		(super init:)
		(self setLocales: FOREST)
		(StatusLine enable:)
		(NormalEgo)
		(ego
			posn:
				1
				(if (>= egoY 150)
					(= egoY (+ egoY 14))
				else
					(= egoY (+ egoY 26))
				)
			init:
			setMotion: MoveTo 320 102
		)
	)
	
	(method (dispose)
		(Bset VISITED_PATH_68)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,forest,greenery]')
								(HighPrint 68 0)
								;You are in a clearing at the edge of the forest.
								;The road you have been following is completely blocked by a very recent avalanche.
								)
							((Said '/ice,road,avalanche')
								(HighPrint 68 1)
								;It appears that a very recent avalanche has sealed off the road leading out of the valley.
								)
							((Said '[<down][/ground,needle,moss,grass]')
								(HighPrint 68 2)
								;The meadow clearing in which you stand is grassy; the road is densely packed and slightly muddy.
								)
							((Said '[<up][/sky,cloud,star]')
								(if Night
									(HighPrint 68 3)
									;The evening is clear and the stars are bright. Dark clouds pass over the moon.
									else
									(HighPrint 68 4)
									;The sky is a piercing blue with scudding white clouds.
									)
								)
							((Said '/birch,tree')
								(HighPrint 68 5)
								;You can see pines, cedars, birches and other trees frequently associated with mountain forests.
								)
							((Said '/bush')
								(HighPrint 68 6)
								;The low-lying bushes form tight tangles of shrubbery between the trees.
								)
							((Said '/boulder')
								(HighPrint 68 7)
								;A tumbled pattern of rocks can be seen at the forest's edge.
								)
							((Said '/hill,cliff,peak,pass')
								(HighPrint 68 8)
								;The ragged peaks of the snow-covered mountains can be seen clearly.
								;The pass, however, is blocked by very recent avalanches.
								)
							((Said '/cave')
								(HighPrint 68 9)
								;There are no caves here.
								)
						)
					)
					((Said 'climb')
						(HighPrint 68 10)
						;Climbing would serve no purpose right now.
						)
				)
			)
		)
		(super handleEvent: event)
	)
)
