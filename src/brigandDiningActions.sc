;;; Sierra Script 1.0 - (do not remove this comment)
(script# 160)
(include game.sh)
(use Main)
(use Invent)

(public
	SaidEndGameBrigands 0
)

(procedure (SaidEndGameBrigands event &tmp spell)
	(cond 
		((Said 'look>')
			(cond 
				((Said '[<at,around][/place,area]')
					(HighPrint 160 0)
					;Wooden tables, benches, and chairs fill the space of this brigand meeting area.
					;A chandelier lights the room and an unlit candelabra stands in the corner.  A rope hangs from the chandelier.
				)
				((or (Said '<up') (Said '/ceiling'))
					(HighPrint 160 1)
					;There is a chandelier with a rope attached.
				)
				((or (Said '<down') (Said '/floor'))
					(HighPrint 160 2)
					;The floor is strewn with flotsam and jetsam.
				)
				((Said '/west,north,east')
					(HighPrint 160 3)
					;You see a door .
				)
				((Said '/south')
					(HighPrint 160 4)
					;The door you came through is the only object you see.
				)
				((Said '/table')
					(HighPrint 160 5)
					;Most of the tables are grimy with dried beer and such.  The head table, though, is clean.
				)
				((Said '/chandelier')
					(HighPrint 160 6)
					;The chandelier is firmly attached to the rope in order to lower it to light the candles.
				)
				((Said '/candelabra')
					(HighPrint 160 7)
					;The massive candelabra certainly seems to get in the way.
				)
				((Said '/sign')
					(HighPrint 160 8)
					;It says, "Brigands over all" (only in German).
				)
				((Said '/bandit')
					(HighPrint 160 9)
					;They remind you of a bunch of Knochelkopfs.
				)
				((Said '/rope')
					(HighPrint 160 10)
					;The rope is heavy and very sturdy.  It is secured to the chandelier.
				)
			)
		)
		((Said 'cast>')
			(cond 
				((!= (= spell (SaidSpell event)) OPEN) (event claimed: FALSE))
				((ego inRect: 250 162 310 190)
					(if ((ScriptID 95 0) notify: 3)
						(HighPrint 160 11)
						;You don't dare; the brigands are coming.
					else
						(HighPrint 160 12)
						;The door is open.
					)
				)
				(
					(or
						(ego inRect: 263 82 320 93)
						(ego inRect: 0 90 47 100)
					)
					(HighPrint 160 13)
					;The door is barred on the other side.
				)
				((not (ego inRect: 34 75 73 95))
					(HighPrint 160 14)
					;You aren't near a door.
					)
				((CastSpell OPEN)
					(ego setScript: (ScriptID 95 25))
				)
			)
		)
		((Said 'get/boulder')
			(HighPrint 160 15)
			;There are no rocks in here.
		)
		((Said 'open[/gate,door]')
			(cond 
				((ego inRect: 34 75 73 95)
					(ego setScript: (ScriptID 95 26))
				)
				((ego inRect: 250 162 310 190)
					(if ((ScriptID 95 0) notify: 3)
						(HighPrint 160 11)
						;You don't dare; the brigands are coming.
					else
						(HighPrint 160 12)
						;The door is open.
					)
				)
				(
					(or
						(ego inRect: 263 82 320 93)
						(ego inRect: 0 90 47 100)
					)
					(HighPrint 160 16)
					;The door is locked from the other side.
				)
				(else
					(HighPrint 160 17)
					;You're not near a door.
				)
			)
		)
		(
			(or
				(Said 'close,close,hasp,bar/gate,door')
				(Said '/gate,door<bar')
			)
			(cond 
				((ego inRect: 34 75 73 95)
					(HighPrint 160 18)
					;The door is closed, but has no lock.
				)
				((ego inRect: 250 162 310 190)
					(if ((ScriptID 95 0) notify: 3)
						(HighPrint 160 19)
						;The door is locked.
					else
						(ego setScript: (ScriptID 95 27))
					)
				)
				((ego inRect: 263 82 320 93)
					(HighPrint 160 16)
					;The door is locked from the other side.
				)
				((ego inRect: 0 90 47 100)
					(HighPrint 160 16)
					;The door is locked from the other side.
				)
				(else
					(HighPrint 160 17)
					;You're not near a door.
				)
			)
		)
		((Said 'force,move,get,use/chair')
			(cond 
				(((ScriptID 95 0) notify: 4)
					(HighPrint 160 20)
					;The door is already blocked.
				)
				((ego inRect: 228 106 320 124)
					(ego setScript: (ScriptID 159 0))
				)
				(
					(or
						(ego inRect: 273 88 282 106)
						(ego inRect: 237 96 273 106)
					)
					((ScriptID 95 0) notify: 8)
					(ego setScript: (ScriptID 159 0))
				)
				(else
					(HighPrint 160 21)
					;Get closer to the chair.
				)
			)
		)
		((Said 'bar,block/door,entrance,door')
			(cond 
				((ego inRect: 34 75 73 95)
					(HighPrint 160 22)
					;The door is closed but has no lock.
				)
				((ego inRect: 250 162 310 190)
					(if ((ScriptID 95 0) notify: 3)
						(HighPrint 160 19)
						;The door is locked.
					else
						(ego setScript: (ScriptID 95 27))
					)
				)
				((ego inRect: 0 90 47 100)
					(HighPrint 160 16)
					;The door is locked from the other side.
				)
				(((ScriptID 95 0) notify: 4)
					(HighPrint 160 20)
					;The door is already blocked.
				)
				((ego inRect: 228 106 320 124)
					(ego setScript: (ScriptID 159 0))
				)
				(
					(or
						(ego inRect: 273 88 282 106)
						(ego inRect: 237 96 273 106)
					)
					((ScriptID 95 0) notify: 8)
					(ego setScript: (ScriptID 159 0))
				)
				(else
					(HighPrint 160 21)
					;Get closer to the chair.
				)
			)
		)
		((Said 'beat,force,shove,use,move,knock/candelabra,candle,candlestick')
			(cond 
				(((ScriptID 95 0) notify: 1)
					(HighPrint 160 23)
					;The candelabra won't budge.
				)
				((ego inRect: 227 83 320 115)
					((ScriptID 95 0) notify: 8)
					(ego setScript: (ScriptID 159 1))
				)
				((ego inRect: 271 0 320 96)
					(ego setScript: (ScriptID 159 1))
				)
				(else
					(HighPrint 160 24)
					;You're not in a good spot.
				)
			)
		)
		((Said 'get/candelabra,candle,candlestick')
			(HighPrint 160 25)
			;It's much too heavy to carry.  You can barely move it.
		)
		((Said 'run,walk,sneak')
			(HighPrint 160 26)
			;Just keep a cool head and be logical.  You don't need to run or sneak.
		)
	)
	(DisposeScript 160)
)
