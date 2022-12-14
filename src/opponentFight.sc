;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include game.sh)
(use Main)
(use System)

(public
	opponentFight 0
)

(instance opponentFight of Script
	(method (init)
		(super init: &rest)
		((ScriptID 218 0)
			opponent: (self client?)
			setLoop: (if (client fightLeft?) 0 else 1)
			fightLeft: (if (client fightLeft?) 0 else 1)
			init:
			setScript: (ScriptID 216 0)
		)
		(client
			view: vWeaponMaster
			ignoreActors:
			setLoop: (if (client fightLeft?) 1 else 0)
			setCel: 0
			opponent: (ScriptID 218 0)
			stopUpd:
		)
		(ego ignoreActors: hide:)
		((ScriptID 39 1)
			number: 80
			loop: -1
			priority: 1
			init:
			play:
		)
	)
	
	(method (doit)
		(cond 
			(
				(or
					(and (client fightLeft?) (> (client x?) 300))
					(and (not (client fightLeft?)) (< (client x?) 20))
				)
				(client gotBeat: (ScriptID 223 0))
				(return)
			)
			(
				(and
					(== (client action?) ActNone)
					(== (client canFight?) TRUE)
				)
				(switch ((client opponent?) action?)
					(ActNone
						(self changeState: 2)
					)
					(ActThrust
						(switch (Random 0 1)
							(0
								(client action: ActDodgeLeft canFight: FALSE)
								(self setScript: (Clone (ScriptID 217 0)) self client)
							)
							(1
								(client action: ActParryUp canFight: FALSE)
								(self setScript: (Clone (ScriptID 217 4)) self client)
							)
						)
					)
					(ActSlash
						(switch (Random 0 1)
							(0
								(client action: ActDuck canFight: FALSE)
								(self setScript: (Clone (ScriptID 217 1)) self client)
							)
							(1
								(client action: ActParryDown canFight: FALSE)
								(self setScript: (Clone (ScriptID 217 5)) self client)
							)
						)
					)
					(ActParryUp
						(client action: ActParryUp canFight: FALSE)
						(self setScript: (Clone (ScriptID 217 4)) self client)
					)
					(ActParryDown
						(client action: ActParryDown canFight: FALSE)
						(self setScript: (Clone (ScriptID 217 5)) self client)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 220)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client action: ActNone)
				(= cycles (Random 5 10))
			)
			(1
				(if ((client opponent?) endFight?)
					(client
						view: vWeaponMasterTalk
						setLoop: (if (client fightLeft?) 5 else 4)
						cel: 0
						setScript: 0
					)
				else
					(client canFight: TRUE)
					(= cycles (Random 10 15))
				)
			)
			(2
				(switch (Random 0 1)
					(0
						(client action: ActThrust canFight: FALSE)
						(self setScript: (Clone (ScriptID 217 2)) self client)
					)
					(1
						(client action: ActSlash canFight: FALSE)
						(self setScript: (Clone (ScriptID 217 3)) self client)
					)
				)
				(if (client tryAttack: (client opponent?))
					((client opponent?) getHit:)
				)
				(= cycles 8)
			)
			(3
				(self changeState: 0)
			)
		)
	)
)
