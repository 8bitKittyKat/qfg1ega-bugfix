;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
(use Sleep)
(use RFeature)
(use Extra)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm10 0
)

(local
	incrMove
	stoneOpened
	sleepX
	sleepY
	timesReadStone
)
(procedure (RevealCalmScroll)
	(HighPrint 10 0)
	;In a hole, hidden beneath the ancient stone, someone has placed a scroll.
)

(procedure (GetCalmScroll)
	(cond 
		((Btst fLearnedCalm)
			(HighPrint 10 1)
			;The scroll is no longer here.
		)
		((not stoneOpened)
			(HighPrint 10 2)
			;You see no scroll here.
		)
		((not (ego inRect: 92 126 150 149))
			(NotClose)
		)
		(else
			(HighPrint 10 3)
			;The scroll vanishes even as you read the magical runes upon it.
			;You now have the knowledge to cast a "Calm" spell.
			(Bset fLearnedCalm)
			(ego get: iPaper 1)
			(ego learn: CALM 10)
			(SolvePuzzle f10LearnCalm 4 MAGIC_USER)
		)
	)
)

(procedure (DontNeedFruit)
	(HighPrint 10 4)
	;Your hunger has been totally satisfied.  You don't need another piece of fruit.
)

(procedure (ReadStone)
	(switch timesReadStone
		(1
			(HighPrint 10 5)
			;The stone has the words "Guruka's Peace" carved upon the top.  Funny.
			;You thought it said something else the last time you looked at it.
			)
		(2
			(HighPrint 10 6)
			;The stone has the words "Erana's Peace" carved upon the top.  Maybe you need glasses.
			)
		(else
			(HighPrint 10 7)
			;The stone has the words "Erana's Peace" carved upon the top.  There are some runes carved along the side.
			)
	)
	(return (++ timesReadStone))
)

(procedure (ReadRunes)
	(HighPrint 10 8)
	;It reads:  "If thy Will is Magic, so shall I share.  Open this Stone and claim what is there."
)

(procedure (CantMoveStone)
	(HighPrint 10 9)
	;No amount of physical effort will be able to move this stone.
)

(instance rm10 of Room
	(properties
		picture 10
		style HSHUTTER
		south 12
	)
	
	(method (init)
		(Load VIEW vEranasPeace)
		(Load SOUND (SoundFX 24))
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(ChangeGait MOVE_WALK FALSE)
		(ego posn: 160 188 init: setMotion: MoveTo 160 170)
		(magicStone posn: 109 145 init:)
		(fruit1 init: setPri: 5)
		(fruit2 init: setPri: 5)
		(fruit3 init: setPri: 5)
		(fruit4 init: setPri: 5)
		(fruit5 init: setPri: 5)
		(fruit6 init: setPri: 5)
		(fruit7 init: setPri: 5)
		(fruit8 init: setPri: 5)
		(fruit9 init: setPri: 5)
		(fruit10 init: setPri: 5)
		(fruit11 init: setPri: 5)
		(fruit12 init: setPri: 5)
		(fruit13 init: setPri: 5)
		(fruit14 init: setPri: 5)
		(self setFeatures: onTree onTrunk)
		(cSound stop: number: (SoundFX 24) loop: -1 play:)
	)
	
	(method (dispose)
		(Bset fBeenIn10)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/scenery,clearing]')
								(HighPrint 10 10)
								;The meadow lies covered with a blanket of flowers, unusual for this early in the spring
								;It is warm, even though surrounded by the late snows of winter.
								(HighPrint 10 11)
								;The air has the fresh, clean scent of the mountains, accompanied by numerous perfume-like fragrances.
								(HighPrint 10 12)
								;A large, carved stone lies flat on the ground.  You feel as though someone gentle was watching over you.
								;You feel that you are safe here.
							)
							((Said '/flower,grass')
								(if (ego has: iFlowers)
									(event claimed: FALSE)
								else
									(HighPrint 10 13)
									;All kinds of colorful and fragrant flowers and grasses cover the meadow.
								)
							)
							((or (Said '<up') (Said '/sky,cloud,star'))
								(HighPrint 10 14)
								;The sky is clear.
								(if Night
									(HighPrint 10 15)
									;The stars shine.
								else
									(HighPrint 10 16)
									;The sun feels warm.
								)
							)
							((or (Said '<down') (Said '/ground'))
								(HighPrint 10 13)
								;All kinds of colorful and fragrant flowers and grasses cover the meadow.
							)
							((Said '/blossom')
								(HighPrint 10 17)
								;The tree's blossoms are soft, green and fragrant.
							)
							((Said '/apple')
								(if Night
									(HighPrint 10 18)
									;The fruit glows and shimmers in the moonlight.
									;It produces a fragrance like a sun-ripened strawberry.
								else
									(HighPrint 10 19)
									;The fruit sparkles in the sunlight, yet it appears soft and fuzzy.
								)
							)
							((Said '/hill,north,dragon,smoke,peak')
								(HighPrint 10 20)
								;To the north, the high, snowy peaks of the Dragon Smoke mountains are clearly visible.
							)
							((Said '/cliff,cliff,east,west')
								(HighPrint 10 21)
								;The meadow is an oasis at the edge of the snowline.  The rocky hillside looks very steep and slippery.
							)
							((Said '/ice')
								(HighPrint 10 22)
								;You are at the snowline.  All around you, except for the forest, the rocks and mountains glisten with slippery snow.
							)
							((Said '/south,forest')
								(HighPrint 10 23)
								;To the south, you see the deep forest through which you came to this meadow.
							)
							(
								(or
									(Said '/boulder,brick')
									(MouseClaimed magicStone event shiftDown)
								)
								(if stoneOpened
									(HighPrint 10 24)
									;The stone has been moved.
								else
									(HighPrint 10 25)
									;The large stone appears to be ancient and deliberately placed.
									;Marks carved into the stone almost look like writing.
								)
							)
							((Said '/mark,word,carving,mark')
								(if (< (ego distanceTo: magicStone) 50)
									(ReadStone)
								else
									(NotClose)
								)
							)
							((Said '/rune')
								(if (< (ego distanceTo: magicStone) 50)
									(ReadRunes)
								else
									(NotClose)
								)
							)
							((Said '/scroll')
								(GetCalmScroll)
							)
							((Said '/chasm')
								(cond 
									((not stoneOpened)
										(HighPrint 10 26)
										;You see no holes.
									)
									((not (Btst fLearnedCalm))
										(RevealCalmScroll)
									)
									((ego inRect: 80 115 160 160)
										(HighPrint 10 27)
										;The hole that was beneath the stone is empty.
									)
									(else
										(NotClose)
									)
								)
							)
						)
					)
					((Said 'read>')
						(cond 
							((Said '/boulder,brick,word,carving,mark')
								(if (< (ego distanceTo: magicStone) 50)
									(ReadStone)
								else
									(NotClose)
								)
							)
							((Said '/rune')
								(if (< (ego distanceTo: magicStone) 50)
									(ReadRunes)
								else
									(NotClose)
								)
							)
							((Said '/scroll')
								(GetCalmScroll)
							)
						)
					)
					((Said 'open,force,move/brick,boulder')
						(CantMoveStone)
					)
					((Said 'eat/apple')
						(cond 
							((> freeMeals 2)
								(DontNeedFruit)
							)
							((not (ego inRect: 109 88 223 106))
								(HighPrint 10 28)
								;Go over to the tree and pick some.
							)
							(else
								(HighPrint 10 29)
								;The sweet, juicy fruit of the tree is amazingly satisfying and refreshing.
								(= freeMeals 4)
								(Bclr fHungry)
								(Bclr fStarving)
								(SolvePuzzle f10EatMeadowFruit 2)
							)
						)
					)
					((Said 'lockpick,get>')
						(cond 
							((Said '/blossom')
								(HighPrint 10 30)
								;The lovely blossoms should stay on the tree.
							)
							((Said '/apple')
								(cond 
									((not (ego inRect: 109 88 223 106))
										(NotClose)
									)
									((> freeMeals 2)
										(DontNeedFruit)
									)
									(else (HighPrint 10 31)
										;The fruit is very soft and juicy.   It would be impossible to keep in your pack.
									)
								)
							)
							((Said '/grass')
								(HighPrint 10 32)
								;The grasses in the meadow are covered over with flowers.
							)
							((Said '/boulder,brick')
								(CantMoveStone)
							)
							((Said '/scroll')
								(GetCalmScroll)
							)
							((Said '/flower')
								(if (Btst fPickedEranaFlowers)
									(HighPrint 10 33)
									;You take another handful of the lovely, fragrant flowers.
								else
									(HighPrint 10 34)
									;As you pick a variety of the sweet-smelling flowers, they seem to glow in your hands.  You put them safely away.
								)
								(Bset fPickedEranaFlowers)
								(ego get: iFlowers 5)
							)
						)
					)
					((Said 'odor/flower,blossom,grass,clearing')
						(HighPrint 10 35)
						;The smell reminds you of laughter.
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(OPEN
								(cond 
									(stoneOpened
										(HighPrint 10 36)
										;There is no further purpose in casting the Open spell.
									)
									((CastSpell spell)
										(ego setScript: moveStoneAway)
									)
								)
							)
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 10 37)
									;There is an aura of magic throughout this meadow.  It seems to be benevolent and restorative.
								)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((or (Said 'nap') (Said 'go[<to]/nap'))
						(if (not (NeedSleep))
							(HighPrint 10 38)
							;You just can't sleep during the daytime.
							(DisposeScript 7)
						else
							(SolvePuzzle f10SleepInMeadow 5 MAGIC_USER)
							(ego setScript: goToSleep)
						)
					)
					((Said 'climb')
						(HighPrint 10 39)
						;The rock faces are slippery with melted snow, and there is no need to climb the little tree.
					)
					((Said 'throw')
						(HighPrint 10 40)
						;The atmosphere here is peaceful and calm.   There is no need to throw anything.
					)
				)
			)
		)
	)
)

(instance goToSleep of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= sleepX (ego x?))
				(= sleepY (ego y?))
				(= currentPalette 1)
				(curRoom drawPic: 10 IRISIN)
				(ego view: vEgoSleep setLoop: 4 setCel: 0)
				(= seconds 3)
			)
			(1
				(TimePrint 3 10 41)
				;You sleep comfortably among the fragrant flowers.
				(= seconds 3)
			)
			(2
				(EgoSleeps 6 0)
				(= currentPalette 0)
				(curRoom drawPic: 10 IRISOUT)
				(ego posn: sleepX sleepY setLoop: 2)
				(magicStone forceUpd:)
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance moveStoneAway of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoMagicDetect
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 15)
			)
			(1
				(++ incrMove)
				(if (> (ego y?) 140)
					(magicStone
						posn: (+ (magicStone x?) 5) (- (magicStone y?) 2)
					)
				else
					(magicStone
						posn: (- (magicStone x?) 5) (+ (magicStone y?) 2)
					)
				)
				(= cycles 1)
			)
			(2
				(if (== incrMove 6)
					(= incrMove 0)
					(HandsOn)
					(= stoneOpened TRUE)
					(if (not (Btst fLearnedCalm))
						(RevealCalmScroll)
					)
					(NormalEgo)
					(ego loop: 2 setScript: 0)
				else
					(self changeState: 1)
				)
			)
		)
	)
)

(instance fruit1 of Extra
	(properties
		y 43
		x 166
		view vEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 50
		maxPause 100
		minCycles 1
		maxCycles 1
	)
)

(instance fruit2 of Extra
	(properties
		y 60
		x 139
		view vEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 60
		maxPause 110
		minCycles 1
		maxCycles 1
	)
)

(instance fruit3 of Extra
	(properties
		y 49
		x 154
		view vEranasPeace
		loop 2
		cycleType ExtraEndLoop
		minPause 70
		maxPause 120
		minCycles 1
		maxCycles 1
	)
)

(instance fruit4 of Extra
	(properties
		y 50
		x 175
		view vEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 80
		maxPause 130
		minCycles 1
		maxCycles 1
	)
)

(instance fruit5 of Extra
	(properties
		y 43
		x 184
		view vEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 90
		maxPause 140
		minCycles 1
		maxCycles 1
	)
)

(instance fruit6 of Extra
	(properties
		y 60
		x 191
		view vEranasPeace
		loop 2
		cycleType ExtraEndLoop
		minPause 100
		maxPause 150
		minCycles 1
		maxCycles 1
	)
)

(instance fruit7 of Extra
	(properties
		y 66
		x 208
		view vEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 40
		maxPause 90
		minCycles 1
		maxCycles 1
	)
)

(instance fruit8 of Extra
	(properties
		y 48
		x 136
		view vEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 30
		maxPause 80
		minCycles 1
		maxCycles 1
	)
)

(instance fruit9 of Extra
	(properties
		y 71
		x 163
		view vEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 20
		maxPause 70
		minCycles 1
		maxCycles 1
	)
)

(instance fruit10 of Extra
	(properties
		y 49
		x 208
		view vEranasPeace
		loop 2
		cycleType ExtraEndLoop
		maxPause 60
		minCycles 1
		maxCycles 1
	)
)

(instance fruit11 of Extra
	(properties
		y 80
		x 190
		view vEranasPeace
		loop 3
		cycleType ExtraEndLoop
		minPause 20
		maxPause 70
		minCycles 1
		maxCycles 1
	)
)

(instance fruit12 of Extra
	(properties
		y 43
		x 148
		view vEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 30
		maxPause 80
		minCycles 1
		maxCycles 1
	)
)

(instance fruit13 of Extra
	(properties
		y 70
		x 128
		view vEranasPeace
		loop 2
		cycleType ExtraEndLoop
		minPause 40
		maxPause 90
		minCycles 1
		maxCycles 1
	)
)

(instance fruit14 of Extra
	(properties
		y 79
		x 145
		view vEranasPeace
		loop 1
		cycleType ExtraEndLoop
		minPause 50
		maxPause 100
		minCycles 1
		maxCycles 1
	)
)

(instance magicStone of View
	(properties
		view vEranasPeace
	)
)

(instance onTrunk of RFeature
	(properties
		nsTop 72
		nsLeft 154
		nsBottom 92
		nsRight 185
	)
)

(instance onTree of RFeature
	(properties
		nsTop 25
		nsLeft 119
		nsBottom 72
		nsRight 220
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed self event shiftDown)
					(MouseClaimed onTrunk event shiftDown)
					(Said 'look/tree,bush,trunk')
				)
				(HighPrint 10 42)
				;The small tree is most amazing.
				;It bears blossoms and fruit at the same time, and the fruit on its boughs seems to shyly appear and disappear, shimmering.
			)
		)
	)
)
