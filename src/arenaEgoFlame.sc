;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_FLAME) ;147
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoFlame 0
)

(local
	local0
	local1
	local2
	local3
)
(instance egoFlame of Script
	(properties)
	
	(method (init)
		(= local1 (ScriptID WARRIOR 0))
		(= local0 (ScriptID CLOSECOMBAT 1))
		(super init: &rest)
	)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
		(DisposeScript ARENA_FLAME)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(local1 canFight: 0 action: 11)
				(self cue:)
			)
			(1
				(local0
					setLoop: 6
					setCel: 0
					cycleSpeed: 1
					ignoreActors:
					posn: (- (local1 x?) 77) (- (local1 y?) 87)
					init:
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(= local2
					(/ (- ((local1 opponent?) flameX?) (local0 x?)) 3)
				)
				(= local3
					(/ (- (local0 y?) ((local1 opponent?) flameY?)) 3)
				)
				(local0
					setPri: 15
					setCel: 3
					posn: (+ (local0 x?) local2) (- (local0 y?) local3)
				)
				(= cycles 2)
			)
			(3
				(local0
					setCel: 4
					posn: (+ (local0 x?) local2) (- (local0 y?) local3)
				)
				(= cycles 2)
			)
			(4
				(local0
					setCel: 5
					posn: (+ (local0 x?) local2) (+ (local0 y?) local3)
				)
				(= cycles 2)
			)
			(5
				(local0 setCel: -1 setCycle: EndLoop self)
			)
			(6
				(local0 dispose:)
				((local1 opponent?) getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
				(local1 canFight: TRUE show:)
				(self dispose:)
			)
		)
	)
)
