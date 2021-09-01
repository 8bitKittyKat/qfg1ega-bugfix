;;; Sierra Script 1.0 - (do not remove this comment)
(script# 114)
(include game.sh)
(use Main)
(use KoboldCave)
(use CastCalm)
(use Motion)
(use Actor)

(public
	castCalm 0
	castFetch 1
)

(local
	lasso
)
(instance castCalm of KScript
	(method (dispose)
		(= register FALSE)
		(super dispose:)
		(DisposeScript 114)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register TRUE)
				(CastCalm self self)
			)
			(1
				(KoboldFight FALSE)
				(if (not (Btst fKoboldDead))
					(CenterPrint 114 0)
					;The Kobold doesn't look very calm.  In fact, it looks totally unaffected by your spell.
					)
				(if register
					(self dispose:)
				)
			)
		)
	)
)

(instance castFetch of KScript
	(method (dispose)
		(NormalEgo)
		(Face ego theKobold)
		(ego illegalBits: koboldIllBits)
		(HandsOn)
		(super dispose:)
		(DisposeScript 114)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: vEgoMagicFetch setLoop: 1 cel: 0 setCycle: 0)
				(= cycles 5)
			)
			(1
				(ego setCycle: EndLoop self)
			)
			(2
				((= lasso (Actor new:))
					view: vEgoMagicFetch
					illegalBits: 0
					ignoreActors:
					posn: (+ (ego x?) 2) (- (ego y?) 36)
					setLoop: 5
					setPri: 7
					setStep: 6 4
					setCycle: Forward
					init:
					setMotion: MoveTo (theKobold x?) (+ (theKobold y?) 7) self
				)
			)
			(3
				(= seconds 2)
			)
			(4
				(if (not (TrySkill FETCH 35 0))
					(lasso dispose:)
					(if (AwakenKobold)
						(CenterPrint 114 1)
						;Uh oh, you've woken the Kobold.
					)
					(self dispose:)
				else
					(lasso
						setPri: 11
						setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 36) self
					)
					((ScriptID 15 3)
						posn: (lasso x?) (lasso y?)
						show:
						setLoop: 7
						setCel: 0
						setPri: 11
						setStep: 6 4
						setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 36)
					)
				)
			)
			(5
				(= seconds 2)
			)
			(6
				(lasso dispose:)
				((ScriptID rKoboldCave 3) dispose:)
				(ego
					get: iBrassKey
					setLoop: 3
					cel: 0
					setCycle: EndLoop self
				)
				(Bset fGotKoboldKey)
				(SolvePuzzle f15GetKey 7)
			)
			(7
				(CenterPrint 114 2)
				;You now have the Kobold's key.  Nice work!
				(self dispose:)
			)
		)
	)
)
