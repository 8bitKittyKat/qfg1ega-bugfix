;;; Sierra Script 1.0 - (do not remove this comment)
(script# rMagicShop)
(include game.sh)
(use Main)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm314 0
)

(local
	[smokeCloud 4]
	familiar
	lightning
	zaraWarp
	zaraLowerTorso
	talkRet
	zaraHere
	familiarAwake
	local11
	local12
	zaraBored
	fetchPrice =  40
	flameDartPrice =  60
	openPrice =  30
	healingPrice =  50
	manaPrice =  75
	vigorPrice =  25
)
(procedure (BuySomething item spell price)
	(return
		(cond 
			((and spell (not [egoStats MAGIC]))
				(HighPrint 314 0)
				;"The powers of magic are reserved for those with the potential to use them.  I sense no such potential in you."
			)
			((and spell (ego knows: spell))
				(AlreadyDone)
			)
			((GiveMoney price)
				(HighPrint 314 1)
				;"May this aid you on your Quest!"
				(ego get: item 1)
				(if spell
					(HighPrint 314 2)
					;As you read the spell scroll, the spell is ingrained in your mind.
					(ego learn: spell 5)
				)
				(return TRUE)
			)
			(else
				(HighPrint 314 3)
				;Zara says, "I detect that you have not the money to purchase that item.  Do not toy with me!"
				(return FALSE)
			)
		)
	)
)

(instance shopMusic of Sound
	(properties
		number 67
	)
)

(instance theThunder of Sound
	(properties
		number 45
		priority 1
	)
)

(instance theTeleport of Sound
	(properties
		number 28
		priority 2
	)
)

(instance rm314 of Room
	(properties
		picture 314
		style DISSOLVE
		south 310
		vanishingY 80
	)
	
	(method (init)
		(Load VIEW rMagicShop)
		(Load VIEW vZara)
		(LoadMany SOUND 67 (SoundFX 45) (SoundFX 28))
		(super init:)
		(mouseDownHandler add: self)
		(shopMusic init: play:)
		(self
			setFeatures:
				onFrog
				onPentagram
				onFloor
				onToaster
				onManta
				onCrystalBalls
				onRobe
				onTinsel1
				onBottles
				onBooks1
				onParphernalia
				onAccoutrements
				onHead
				onBat
				onOdds1
		)
		(StatusLine enable:)
		(NormalEgo)
		(ego loop: 3 posn: 159 188 init:)
		((= [smokeCloud 0] (Prop new:))
			view: rMagicShop
			posn: 159 13
			loop: 0
			cel: 0
			init:
			stopUpd:
		)
		((= [smokeCloud 1] (Prop new:))
			view: rMagicShop
			posn: 149 27
			loop: 1
			cel: 0
			init:
			stopUpd:
		)
		((= [smokeCloud 2] (Prop new:))
			view: rMagicShop
			posn: 161 38
			loop: 2
			cel: 0
			init:
			stopUpd:
		)
		((= [smokeCloud 3] (Prop new:))
			view: rMagicShop
			posn: 160 53
			loop: 3
			cel: 0
			init:
			stopUpd:
		)
		((= familiar (Prop new:))
			view: rMagicShop
			posn: 110 55
			loop: 6
			cel: 0
			init:
			setPri: 10
			stopUpd:
		)
		((View new:)
			view: rMagicShop
			posn: 111 66
			loop: 8
			cel: 0
			setPri: 9
			init:
			stopUpd:
		)
		(self setLocales: 801)
	)
	
	(method (doit)
		(if (and (< (ego y?) 165) (not familiarAwake))
			(= familiarAwake TRUE)
			(familiar setScript: familiarScript)
		)
		(if local11
			(cond 
				((< (ego y?) 125)
					(if local12
						(= local12 0)
						(familiarScript changeState: 4)
					)
				)
				((not local12)
					(= local12 1)
					(familiarScript changeState: 6)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn314)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,room,building,shop]')
								(HighPrint 314 4)
								;Tall shelves with books and odds and ends suited to magic line the walls.
								;In front of you is a counter with a pentagram embossed on the front.
								;The ceiling and the far wall are mysterious indeed.  There is a strange symbol on the floor.
							)
							((Said '/shelf,shelf')
								(HighPrint 314 5)
								;You see a wide variety of arcane objects.  For example:
								(switch (Random 0 8)
									(0
										(HighPrint 314 6)
										;A replacement part for a magical face-lift.
									)
									(1
										(HighPrint 314 7)
										;Books dealing with sorcery, necromancy and other occult subjects.
									)
									(2
										(HighPrint 314 8)
										;Bottles of mephitic potions.
									)
									(3
										(HighPrint 314 9)
										;Tinsel and chaff, very good for avoiding radar.
									)
									(4
										(HighPrint 314 10)
										;A hooded robe for when it rains in here.
									)
									(5
										(HighPrint 314 11)
										;Crystal balls, useful only to those initiated into the ways of witches.
									)
									(6
										(HighPrint 314 12)
										;A toaster oven, very rarely used (electricity not invented yet).
									)
									(7
										(HighPrint 314 13)
										;A frog.
									)
									(8
										(HighPrint 314 14)
										;Dried up Mantray (Manta jerky).
									)
								)
							)
							((or (Said '<up') (Said '/ceiling,cloud,smoke'))
								(HighPrint 314 15)
								;The ceiling is shrouded with clouds of smoke.
							)
							((or (Said '<down') (Said '/floor'))
								(HighPrint 314 16)
								;The floor is almost completely covered by a strange symbol.
							)
							((Said '/monster')
								(HighPrint 314 17)
								;You don't see any monsters here.
							)
							((Said '/creature,familiar')
								(if (not familiarAwake)
									(HighPrint 314 18)
									;You don't see any living creatures.
								else
									(HighPrint 314 19)
									;This living creature must be some sort of familiar.
									;There is an uncanny look in its eyes, and you don't want to look at it too closely.  It definitely has magic.
								)
							)
							((Said '/wall')
								(HighPrint 314 20)
								;The walls are covered with shelves full of interesting and very unusual objects.
								;The back wall behind the counter seems to be not a wall at all, but perhaps an extension of some unearthly place.
							)
							((Said '/book')
								(HighPrint 314 21)
								;The titles are totally unfamiliar to you.
							)
							((Said '/counter')
								(HighPrint 314 22)
								;Aside from the pentagram on the front, there is nothing unusual about the counter.
							)
						)
					)
					((or (Said 'grab,sneak') (Said '[use]/stealth'))
						(HighPrint 314 23)
						;Someone is watching you.
					)
					((Said 'buy,get')
						(HighPrint 314 24)
						;There is no one here to sell you anything.
					)
				)
			)
		)
	)
)

(instance zara of Prop
	(properties
		view vZara
		loop 1
	)
	
	(method (init)
		(super init:)
		(self posn: 159 82 hide:)
		(theThunder number: (SoundFX 45) init: play:)
		(self setScript: entranceScript)
	)
	
	(method (handleEvent event)
		(if
			(or
				(MouseClaimed self event shiftDown)
				(Said 'look/zara,female,girl,keeper,shopkeeper')
			)
			(HighPrint 314 25)
			;Zara has a mysterious, unearthly look to her.  You have the feeling that she is not a person to trifle with.
		)
		(switch (event type?)
			(saidEvent
				(if (Said 'chat')
					(HighPrint 314 26)
					;"My name is Zara.  This is my shop."
				)
				(if (Said '/bye')
					(self setScript: exitScript)
				)
				(cond 
					((< (ego y?) 125)
						(cond 
							((Said 'ask>')
								(= talkRet TRUE)
								(cond 
									((Said '//name')
										(HighPrint 314 26)
										;"My name is Zara.  This is my shop."
									)
									((Said '//zara')
										(HighPrint 314 27)
										;"I am both human and Faery Folk.  I draw my Power from both."
									)
									((Said '//faerie,folk')
										(HighPrint 314 28)
										;"We are a people of Power and Magic, and live in the forests far beyond the mountains to the west."
									)
									((Said '//monster')
										(HighPrint 314 29)
										;"Monsters stay out of town, and I stay in.  They are of no concern to me."
									)
									((Said '//creature,damiano,familiar')
										(HighPrint 314 30)
										;"Damiano is my familiar and my companion.  We share our lives and our magic."
									)
									((Said '//initiate')
										(HighPrint 314 31)
										;"When you have mastered nine spells, have the power to cast them all,
										;and proved yourself worthy by accomplishing a great deed, then you must undergo initiation to become a full Wizard."
										(HighPrint 314 32)
										;"There is a place in the distant south where you must journey, but first you must become a hero here."
									)
									((Said '//wizard')
										(HighPrint 314 33)
										;"A Wizard is one who both shapes Magic and is shaped by it.  Erasmus and I are the only Wizards in this valley."
									)
									((Said '//magic,pentacle,rune')
										(HighPrint 314 34)
										;"There is much Magic in this world for those who know how to use it.
										;There is Magic in this little town, and a good deal of Magic in this valley."
										(HighPrint 314 35)
										;"What I sell here are merely tools to help those who have power.
										;You may purchase restorative potions and scrolls from which you may learn spells if you have the potential."
									)
									((Said '//hamlet')
										(HighPrint 314 36)
										;"There is an Aura protecting this town from danger.
										;Within most of the town's walls, there can be no act of violence or cruel Magic.
										;Even so, it is prudent to avoid dark places."
									)
									((Said '//aura')
										(HighPrint 314 37)
										;"An Aura is a Spell of protection surrounding something.
										;The town is still protected by the Aura cast by the great Spellcaster, Erana."
									)
									((Said '//valley')
										(HighPrint 314 38)
										;"There is much Power in this valley, and it attracts those who use Magic.
										;I am here in Spielburg, Erasmus has his house on Zauberberg, and even Baba Yaga
										;has her hut cooped up somewhere around here."
									)
									((Said '//baba')
										(HighPrint 314 39)
										;"She is a powerful and wicked hag.  You would be wise to avoid her.
										;She cursed the Baron von Spielburg when he tried to drive her away."
									)
									((Said '//curse')
										(HighPrint 314 40)
										;"This is Baba Yaga's curse, cast upon the Baron years ago:"
										(HighPrint 314 41)
										;"Upon von Spielburg and all his clan, this Curse I now demand:
										;What I will shall come full measure, so shall ye lose all that ye treasure."
										(HighPrint 314 42)
										;"There is always a way to break a curse; possibly Erasmus knows more about this one."
									)
									((Said '//hut')
										(HighPrint 314 43)
										;"Baba Yaga's hut is Magical.  It stands on chicken legs, and you must know the rhyme to enter."
									)
									((Said '//rhyme')
										(HighPrint 314 44)
										;"I have no interest in entering Baba Yaga's hut and therefore do not know the rhyme."
									)
									((Said '//baron')
										(HighPrint 314 45)
										;"He was once the great leader of this land until he was foolish enough to anger Baba Yaga."
									)
									((Said '//erana,peace,caster')
										(HighPrint 314 46)
										;"Erana was a powerful Spellcaster who lived long ago.
										;She brought peace to this valley.  Even now her spell protects this town from violence or foul Magic."
										(HighPrint 314 47)
										;"Her final resting place is due north of town, and it is a place of both safety and healing.
										;It is known as `Erana's Peace'"
									)
									((Said '//erasmus')
										(HighPrint 314 48)
										;"Erasmus is a Wizard and a Spellcaster who knows much about this area.  He lives in a tower eastward of town.
										;He can be very helpful if approached properly, but he has a strange sense of humor."
									)
									((Said '//zauberberg')
										(HighPrint 314 49)
										;"It is a steep mountain to the northeast."
									)
									(
										(or
											(Said '//dart,flame,fire')
											(Said '//spell,scroll<fireball,dart,flame,fire')
										)
										(HighPrint 314 50)
										;"That is the Spell that lets you cast a Magical Flame upon your enemy. 
										; The Spell Scroll will cost you 60 silver."
									)
									((or (Said '//zap') (Said '//spell<zap'))
										(HighPrint 314 51)
										;"That is the Spell that lets you place magical energy upon a weapon.
										;The energy will be released as magical damage when you hit your enemy."
									)
									((or (Said '//open') (Said '//spell,scroll<open'))
										(HighPrint 314 52)
										;"It is a Spell that allows you at first to unfasten simple locks and knots.
										;As you gain in skill, then you may open doors with it.  This Spell Scroll will cost you 30 silver."
									)
									((or (Said '//fetch') (Said '//spell,scroll<fetch'))
										(HighPrint 314 53)
										;"The Fetch Spell allows you to lift or lower a lightweight object from a distance.
										;The Spell Scroll will cost you 40 silver."
									)
									((Said '//spell,scroll')
										(HighPrint 314 54)
										;"We sell several Magical Spells on study scrolls.
										;You may purchase Flame Dart for 60 silver, Fetch for 40 silver, or the Open spell for 30 silver."
										(HighPrint 314 55)
										;"The Scrolls we sell are Magical.  You have but to read the Spell, and you will learn it."
										(HighPrint 314 56)
										;"I also know that you can find a wonderful Spell if you learn the secret of Erana's Peace."
									)
									((Said '//secret')
										(HighPrint 314 57)
										;"Secrets are made to be kept."
									)
									((Said '//item,stuff,buy')
										(HighPrint 314 58)
										;"We can sell you spell learning scrolls and enchanted potions."
									)
									(
										(or
											(Said '//heal,healing')
											(Said '//potion<heal,healing')
										)
										(HighPrint 314 59)
										;"The Healing Potion will cost you 50 silver.  It is made by the Healer outside of town."
									)
									(
										(or
											(Said '//vigor,stamina')
											(Said '//potion<vigor,stamina')
										)
										(HighPrint 314 60)
										;"Vigor Potions restore your Stamina after you have exerted yourself.  They cost 25 silver each."
									)
									(
										(or
											(Said '//mana,magic,power')
											(Said '//potion<mana,magic,power')
										)
										(HighPrint 314 61)
										;"Power is the essence of magic, that which the wizard shapes and is shaped by."
										(HighPrint 314 62)
										;"The Power Potion restores your magical energy, and costs 75 silver."
									)
									((Said '//potion')
										(HighPrint 314 63)
										;"We have Healing, Vigor, and Power Potions."
									)
									((Said '//quest,hero,adventure,art[<arcane]')
										(HighPrint 314 64)
										;"Master the Arts Arcane, use those skills to vanquish the evil curse, and you will become a true Hero!"
									)
									((Said '//accoutrement,equipment,jerky,frog,electricity,oven,crystal,ball,facelift,necromancy,chaff,robe')
										(HighPrint 314 65)
										;Just look at the shelves.
									)
									((Said '//*')
										(= talkRet FALSE)
										(switch zaraBored
											(0
												(HighPrint 314 66)
												;"We are entities of Magic and Power.  Matters such as that are of little importance."
											)
											(1
												(HighPrint 314 67)
												;"We choose only to answer questions which interest us.  Do not persist with uninteresting questions."
											)
											(2
												(HighPrint 314 68)
												;"We shall not waste our time further."
												(self setScript: exitScript)
											)
										)
										(++ zaraBored)
									)
								)
								(if talkRet
									(SolvePuzzle f314TalkToZara 1)
								)
							)
							((Said 'buy,get>')
								(cond 
									(
										(or (Said '/fetch') (Said '/spell,scroll<fetch'))
										(if (BuySomething iPaper FETCH fetchPrice)
											(SolvePuzzle f314LearnFetch 2 MAGIC_USER)
										)
									)
									(
										(or
											(Said '/dart,flame,fire')
											(Said '/spell,scroll<fireball,dart,flame,fire')
										)
										(if (BuySomething iPaper FLAMEDART flameDartPrice)
											(SolvePuzzle f314LearnFlameDart 2 MAGIC_USER)
										)
									)
									(
										(or (Said '/open') (Said '/spell,scroll<open'))
										(if (BuySomething iPaper OPEN openPrice)
											(SolvePuzzle f314LearnOpen 2 MAGIC_USER)
										)
									)
									((Said '/scroll,spell')
										(HighPrint 314 69)
										;"You may choose from the Fetch, Open, or Flame Dart Spells."
									)
									((Said '/potion<heal,healing')
										(BuySomething iHealingPotion FALSE healingPrice)
									)
									((Said '/potion<vigor,stamina')
										(BuySomething iStaminaPotion FALSE vigorPrice)
									)
									((Said '/potion<mana,magic,power')
										(BuySomething iManaPotion FALSE manaPrice)
									)
									((Said '/potion')
										(HighPrint 314 70)
										;"You may choose from the Healing, Vigor, or Power Potions."
									)
									(else
										(event claimed: TRUE)
										(HighPrint 314 71)
										;"I do not sell such things to such as you."
									)
								)
							)
						)
					)
					((Said 'ask,buy')
						(HighPrint 314 72)
						;Zara ignores you. 
						; You would do better if you showed some respect by walking up to the counter before talking to her.
					)
				)
			)
		)
	)
)

(instance entranceScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				([smokeCloud 0] setCycle: EndLoop self)
			)
			(1
				([smokeCloud 1] setCycle: EndLoop self)
			)
			(2
				([smokeCloud 2] setCycle: EndLoop self)
			)
			(3
				([smokeCloud 3] setCycle: EndLoop self)
			)
			(4
				((= lightning (Prop new:))
					view: rMagicShop
					posn: 159 90
					loop: 4
					cel: 0
					init:
					setCycle: EndLoop self
				)
			)
			(5
				(lightning stopUpd:)
				((= zaraWarp (Prop new:))
					view: rMagicShop
					posn: 161 90
					loop: 5
					cel: 0
					init:
					ignoreActors:
					setCycle: CycleTo 3 1 self
				)
				(theTeleport number: (SoundFX 28) init: play:)
			)
			(6
				(zara loop: 1 cel: 0 show:)
				((= zaraLowerTorso (View new:))
					view: vZara
					posn: 159 92
					loop: 0
					cel: 0
					init:
					ignoreActors:
					stopUpd:
				)
				(zaraWarp setCycle: CycleTo 5 1 self)
			)
			(7 (zara setCycle: EndLoop self))
			(8
				(= zaraHere 1)
				(zaraWarp stopUpd:)
				(HandsOn)
				(if (not (Btst fBeenIn314))
					(self cue:)
				else
					(HighPrint 314 73)
					;"So, you return to my shop.  Ask of me what you will, but keep to the point."
				)
			)
			(9
				(if (not demoScripts) ;EO: What's with the demo check?
					(HighPrint 314 74)
					;"I am Zara, and my companion is Damiano."
					(HighPrint 314 75)
					;"The items in this shop are designed for those skilled in the use of magic.
					;We have very little for ones such as you who have not been initiated, but what we have could prove useful."
				)
			)
		)
	)
)

(instance exitScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theTeleport play:)
				(client loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(1
				(zaraWarp setCycle: CycleTo 3 1 self)
			)
			(2
				(client hide:)
				(zaraLowerTorso dispose:)
				(zaraWarp setCycle: CycleTo 5 1 self)
				(theThunder play:)
			)
			(3
				(HandsOn)
				(lightning setCycle: BegLoop self)
				(client dispose:)
			)
		)
	)
)

(instance familiarScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(familiar cel: 1 posn: 110 54)
				(= cycles 3)
			)
			(1
				(familiar cel: 2 posn: 110 53)
				(= cycles 3)
			)
			(2
				(familiar setCycle: EndLoop self)
			)
			(3
				(= local11 1)
				(if (< (ego y?) 125)
					(self cue:)
				else
					(familiar stopUpd:)
				)
			)
			(4
				(familiar loop: 7 cel: 0 setCycle: EndLoop self)
			)
			(5
				(familiar stopUpd:)
				(if (not zaraHere)
					(zara init:)
				)
			)
			(6
				(familiar loop: 7 setCycle: BegLoop self)
			)
			(7 (familiar stopUpd:))
		)
	)
)

(instance onFrog of RFeature
	(properties
		nsTop 84
		nsLeft 202
		nsBottom 102
		nsRight 218
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onFrog event shiftDown)
				(HighPrint 314 76)
				;It's a frog.
			)
		)
	)
)

(instance onPentagram of RFeature
	(properties
		nsTop 99
		nsLeft 148
		nsBottom 115
		nsRight 167
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onPentagram event shiftDown)
				(HighPrint 314 77)
				;A pentagram, used in incantations to conjure spirits.
			)
		)
	)
)

(instance onFloor of RFeature
	(properties
		nsTop 124
		nsLeft 130
		nsBottom 181
		nsRight 195
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onFloor event shiftDown)
				(HighPrint 314 78)
				;Impressive design, but merely decorative.
			)
		)
	)
)

(instance onToaster of RFeature
	(properties
		nsTop 88
		nsLeft 102
		nsBottom 99
		nsRight 115
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onToaster event shiftDown)
				(HighPrint 314 12)
				;A toaster oven, very rarely used (electricity not invented yet).
			)
		)
	)
)

(instance onManta of RFeature
	(properties
		nsTop 72
		nsLeft 230
		nsBottom 90
		nsRight 252
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onManta event shiftDown)
				(HighPrint 314 14)
				;Dried up Mantray (Manta jerky).
			)
		)
	)
)

(instance onCrystalBalls of RFeature
	(properties
		nsTop 50
		nsLeft 67
		nsBottom 67
		nsRight 91
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onCrystalBalls event shiftDown)
				(HighPrint 314 11)
				;Crystal balls, useful only to those initiated into the ways of witches.
			)
		)
	)
)

(instance onRobe of RFeature
	(properties
		nsTop 70
		nsLeft 102
		nsBottom 81
		nsRight 119
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onRobe event shiftDown)
				(HighPrint 314 10)
				;A hooded robe for when it rains in here.
			)
		)
	)
)

(instance onTinsel1 of RFeature
	(properties
		nsTop 107
		nsLeft 209
		nsBottom 122
		nsRight 219
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onTinsel1 event shiftDown)
					(MouseClaimed onTinsel2 event shiftDown)
				)
				(HighPrint 314 9)
				;Tinsel and chaff, very good for avoiding radar.
			)
		)
	)
)

(instance onTinsel2 of RFeature
	(properties
		nsTop 118
		nsLeft 230
		nsBottom 139
		nsRight 250
	)
)

(instance onBottles of RFeature
	(properties
		nsTop 92
		nsLeft 229
		nsBottom 117
		nsRight 250
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBottles event shiftDown)
				(HighPrint 314 8)
				;Bottles of mephitic potions.
			)
		)
	)
)

(instance onBooks1 of RFeature
	(properties
		nsTop 26
		nsLeft 67
		nsBottom 48
		nsRight 92
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onBooks1 event shiftDown)
					(MouseClaimed onBooks2 event shiftDown)
					(MouseClaimed onBooks3 event shiftDown)
				)
				(HighPrint 314 7)
				;Books dealing with sorcery, necromancy and other occult subjects.
			)
		)
	)
)

(instance onBooks2 of RFeature
	(properties
		nsTop 36
		nsLeft 202
		nsBottom 49
		nsRight 219
	)
)

(instance onBooks3 of RFeature
	(properties
		nsTop 29
		nsLeft 230
		nsBottom 46
		nsRight 251
	)
)

(instance onParphernalia of RFeature
	(properties
		nsTop 123
		nsLeft 66
		nsBottom 137
		nsRight 92
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onParphernalia event shiftDown)
				(HighPrint 314 79)
				;Paraphernalia and apparatus.
			)
		)
	)
)

(instance onAccoutrements of RFeature
	(properties
		nsTop 51
		nsLeft 230
		nsBottom 68
		nsRight 251
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onAccoutrements event shiftDown)
				(HighPrint 314 80)
				;Accoutrements and equipment.
			)
		)
	)
)

(instance onBat of RFeature
	(properties
		nsTop 32
		nsLeft 102
		nsBottom 67
		nsRight 117
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBat event shiftDown)
				(HighPrint 314 81)
				;Darned if I know!
			)
		)
	)
)

(instance onHead of RFeature
	(properties
		nsTop 94
		nsLeft 66
		nsBottom 113
		nsRight 92
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onHead event shiftDown)
				(HighPrint 314 6)
			)
		)
	)
)

(instance onOdds1 of RFeature
	(properties
		nsTop 72
		nsLeft 67
		nsBottom 88
		nsRight 92
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onOdds1 event shiftDown)
					(MouseClaimed onOdds2 event shiftDown)
					(MouseClaimed onOdds3 event shiftDown)
				)
				(switch (Random 1 5)
					(1
						(HighPrint 314 82)
						;Odds and ends.
					)
					(2
						(HighPrint 314 83)
						;Ends and odds.
					)
					(3
						(HighPrint 314 84)
						;Things and stuff.
					)
					(4
						(HighPrint 314 85)
						;Jeff's jimcracks.
					)
					(5
						(HighPrint 314 86)
						;Magical junk and mystical garbage.
					)
				)
			)
		)
	)
)

(instance onOdds2 of RFeature
	(properties
		nsTop 109
		nsLeft 102
		nsBottom 121
		nsRight 111
	)
)

(instance onOdds3 of RFeature
	(properties
		nsTop 53
		nsLeft 201
		nsBottom 86
		nsRight 219
	)
)
