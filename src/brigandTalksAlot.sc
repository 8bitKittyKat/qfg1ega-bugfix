;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	talksAlot 0
	knockout 1
)

(instance talksAlot of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 230)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 43 188 self)
			)
			(1
				(ego loop: 2)
				(= cycles 2)
			)
			(2
				(HighPrint 230 0)
				;You bar the door behind you.
				(ego setMotion: MoveTo 43 179 self)
			)
			(3
				(ego illegalBits: cWHITE)
				(HighPrint 230 1)
				;You behold a most fascinating place.
				(HighPrint 230 2)
				;The person labeled "ME" speaks:
				(HighPrint 230 3)
				;"Abandon mope, all ye who enter here!"
				(HighPrint 230 4)
				;"Job hunting, or just sight-seeing?"
				(HighPrint 230 5)
				;"I foresee a brilliant future for you in the fall."
				(HighPrint 230 6)
				;"If you've got something to say, then speak fast or forever hold your pieces."
				(HighPrint 230 7)
				;"State your case before I case your estate!"
				(self cue:)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance knockout of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 230)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 73 117 self)
			)
			(1
				(ego view: vEgoJesterRoom setLoop: 7 setCel: 0)
				(= cycles 8)
			)
			(2
				((ScriptID 96 7)
					setPri: (+ (ego priority?) 1)
					setCel: 1
				)
				(= cycles 2)
			)
			(3
				((ScriptID 96 7)
					setPri: (- ((ScriptID 96 7) priority?) 1)
					setCel: 2
				)
				(ego setCel: 1)
				(= cycles 2)
				(if (not (Btst fPulledChain))
					((ScriptID 96 6) setCel: 3)
					((ScriptID 96 5)
						setLoop: 4
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
					)
				)
			)
			(4
				((ScriptID 96 16)
					number: (SoundFX 85)
					loop: 1
					priority: 2
					play:
				)
				((ScriptID 96 7) setCel: 3)
				(ego posn: 91 111 setCel: 2)
				(= cycles 2)
			)
			(5
				((ScriptID 96 7) setCel: 4)
				(ego posn: 109 107 setCel: 3)
				(= cycles 2)
			)
			(6
				((ScriptID 96 7) setCel: 5)
				(ego setPri: 7 posn: 126 119 setCel: 4)
				(= cycles 2)
			)
			(7
				((ScriptID 96 7) setCel: 0 stopUpd:)
				(ego posn: 137 137)
				(= cycles 2)
			)
			(8
				(ego posn: 152 161)
				(= cycles 2)
			)
			(9
				(ego posn: 160 189)
				(if (not (TakeDamage 5))
					(EgoDead 230 8
						#title {You're the Fall Guy again}
						#icon vEgoClimbing 2 5
					)
						;You're mad as heck, and you just won't take it anymore.  As a matter of fact, you CAN'T take it anymore.
						;Keep up your strength and health and try again.
				else
					(= cycles 10)
				)
			)
			(10
				(Bclr fFallingOffLedge)
				(Bclr fOpeningDoor)
				(Bset fRollingOut)
				(ego setScript: (ScriptID 226 0))
				(client setScript: 0)
			)
		)
	)
)
