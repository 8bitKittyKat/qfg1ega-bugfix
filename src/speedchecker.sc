;;; Sierra Script 1.0 - (do not remove this comment)
(script# 299)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)

(public
	speedChecker 0
)

(local
	endTime
)
(instance fred of Actor
	(properties)
)

(instance speedChecker of Room
	(properties
		picture 400
		style IRISIN
	)
	
	(method (init)
		(HandsOff)
		(super init:)
		(sounds eachElementDo: #stop)
		(while (u> (GetTime) $FC00)
		)
		(fred
			view: vBlack
			setLoop: 0
			illegalBits: 0
			posn: 20 99
			setStep: 1 1
			setMotion: MoveTo 300 100
			setCycle: Forward
			init:
		)
		(theGame setSpeed: 0)
		(= machineSpeed 0)
	)
	
	(method (doit &tmp [temp0 200])
		(super doit:)
		(if (== (++ machineSpeed) 1)
			(= endTime (+ 60 (GetTime)))
		)
		(if (u< endTime (GetTime))
			(cond 
				((<= machineSpeed 25) (= howFast slow))
				((<= machineSpeed 40) (= howFast medium))
				((<= machineSpeed 60) (= howFast fast))
				(else (= howFast fastest)) ;EO: Added in by 1.200
			)
			(theGame setSpeed: 5)
			(RedrawCast)
			(curRoom newRoom: startingRoom)
		)
	)
)
