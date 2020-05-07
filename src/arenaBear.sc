;;; Sierra Script 1.0 - (do not remove this comment)
(script# BEAR) ;420
(include game.sh)
(use Main)
(use Arena)
(use Monster)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	bearArena 0
	bear 1
)

(local
	local0
	[local1 2]
	local3
	[theLeftArm 3]
	[local7 3]
	local10
	local11 =  5
)
(procedure (localproc_000e &tmp temp0)
	(= temp0 0)
	(while (< temp0 2)
		([theLeftArm temp0]
			setScript: [local7 temp0] 0 (= [local7 temp0] (Clone aFightScript))
		)
		(++ temp0)
	)
)

(instance leftArm of Prop
	(properties
		y 39
		x 199
		view vBearFight
		loop 2
	)
)

(instance rightArm of Prop
	(properties
		y 42
		x 115
		view vBearFight
		loop 1
	)
)

(instance bearMusic of Sound
	(properties
		number 2
		priority 2
		loop -1
	)
)

(instance bear of Monster
	(properties
		view vBearFight
		strength 70
		intell 25
		agil 40
		vit 65
		luck 25
		weap 50
		dodge 60
		armorValue 3
		weapValue 5
		attackRange 65
		warriorX 170
		flameX 172
		flameY 73
	)
	
	(method (die)
		(= local0 1)
		(Bset DEFEATED_BEAR)
		(SolvePuzzle POINTS_KILLBEAR -25)
		(curRoom newRoom: 171)
	)
)

(instance bearArena of Arena
	(properties
		picture 420
	)
	
	(method (init)
		(Load VIEW vBearFight)
		(= monster bear)
		(= monsterNum BEAR)
		(= prevRoomNum 171)
		(super init: &rest)
		(leftArm setPri: 12 init: stopUpd:)
		(rightArm setPri: 12 init: stopUpd:)
		(bear
			view: vBearFight
			setLoop: 0
			cel: 0
			posn: 156 68
			setPri: 10
			cycleSpeed: 2
			setScript: bearCycle
		)
		(= [theLeftArm 0] leftArm)
		(= [theLeftArm 1] rightArm)
		(bearMusic number: (SoundFX 2) init: play:)
		(localproc_000e)
	)
	
	(method (doit)
		(if (Btst fMonsterDazzled) (bear setScript: bearHurt))
		(super doit:)
	)
	
	(method (dispose)
		(bearMusic stop:)
		(spareSound number: (SoundFX 38) loop: 1 play:)
		(super dispose:)
	)
)

(instance aFightScript of Script
	(properties)
	
	(method (doit)
		(cond 
			(local0 (= local0 (= cycles 0)))
			((and monsterDazzle (== state 0))
				(= cycles (+ cycles monsterDazzle))
				(= monsterDazzle 0)
				(Bclr fMonsterDazzled)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				([theLeftArm register] cel: local10 setCycle: 0 stopUpd:)
				(bear action: 0)
				(= cycles (Random 5 10))
			)
			(1
				(= local3 (Random 0 1))
				(bear action: 1)
				([theLeftArm register] setCycle: CycleTo local11 1 self)
				(if (bear tryAttack: (bear opponent?))
					(bear ateEgo: 1)
				)
			)
			(2
				(if (bear ateEgo?)
					(bear doDamage: (bear opponent?))
					(bear ateEgo: 0)
				)
				(if local3
					([theLeftArm register]
						setCycle: CycleTo ([theLeftArm register] cel?) 1 self
					)
				else
					(client setCycle: EndLoop self)
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance bearHurt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr fMonsterDazzled)
				(bear setLoop: 0 cel: 1 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(1 (= cycles (Random 2 4)))
			(2
				(bear cel: 4)
				(= cycles (Random 2 4))
			)
			(3
				(bear cel: 5)
				(= cycles (Random 2 4))
			)
			(4
				(bear cycleSpeed: 1 setCycle: BegLoop self)
			)
			(5
				(bear cel: 0 cycleSpeed: 2 setScript: bearCycle)
			)
		)
	)
)

(instance bearCycle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bear setCycle: CycleTo 3 1)
				(= cycles (Random 9 15))
			)
			(1
				(bear setCycle: BegLoop self)
				(= cycles (Random 9 15))
			)
			(2 (self changeState: 0))
		)
	)
)
