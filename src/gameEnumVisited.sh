;;; Sierra Script 1.0 - (do not remove this comment)

; Event Flags
; flags are assigned for every room after ego leaves it.
; if these aren't set, then ego has never been in the room before.
(enum
	VISITED_ERANASPEACE 		;0
	VISITED_FOREST_11 			;1
	VISITED_FOREST_12 			;2
	VISITED_OGRE_ROOM 			;3
	VISITED_BEAR_CAVE 			;4
	VISITED_KOBOLD_CAVE 		;5
	VISITED_SPITTING_SPIREA 	;6
	VISITED_FOREST_17 			;7
	VISITED_FOREST_18 			;8
	VISITED_FOREST_19 			;9
	VISITED_ROOM_20 			;10 ;unused (Room 20 does not exist)
	VISITED_BABAYAGA_INTERIOR 	;11
	VISITED_BABAYAGA_EXTERIOR 	;12
	VISITED_FOREST_23 			;13
	VISITED_FOREST_24 			;14
	VISITED_FOREST_25 			;15
	VISITED_FOREST_26 			;16
	VISITED_FOREST_27 			;17
	VISITED_ERASMUS_OUTLOOK 	;18
	VISITED_ERASMUS_OUTSIDE 	;19
	VISITED_ERASMUS_INSIDE 		;20
	VISITED_ERASMUS_TOWER 		;21
	VISITED_WIZARDS_GAME 		;22 ;unused (Room 32 is the Wizard's Game)
	VISITED_FOREST_33 			;23
	VISITED_FOREST_34 			;24
	VISITED_FOREST_35 			;25
	VISITED_FOREST_36 			;26
	VISITED_CASTLE_GATE 		;27
	VISITED_CASTLE_BARRACKS 	;28
	VISITED_CASTLE_COURTYARD 	;29 ;unused
	VISITED_CASTLE_STABLES 		;30 ;unused
	VISITED_CASTLE_GUARDS 		;31
	VISITED_FOREST_42 			;32
	VISITED_FOREST_43 			;33
	VISITED_FOREST_44 			;34
	VISITED_GOBLIN_AMBUSH 		;35
	VISITED_ROOM_46 			;36 ;unused (deleted Goblin Cave scene)
	VISITED_ROOM_47 			;37 ;unused (deleted Goblin Cave scene)
	VISITED_ROOM_48 			;38 ;unused (deleted Goblin Cave scene)
	VISITED_ROOM_49 			;39 ;unused (deleted Goblin Cave scene)
	VISITED_ROOM_50 			;40 ;unused (deleted Goblin Cave scene)
	VISITED_FOREST_51 			;41
	VISITED_FOREST_52 			;42
	VISITED_FARM 				;43
	VISITED_HEALERHUT_OUTSIDE 	;44
	VISITED_HEALERHUT_INSIDE 	;45
	VISITED_FOREST_56 			;46
	VISITED_FOREST_57 			;47
	VISITED_FROST_CAVE 			;48
	VISITED_ROOM_59 			;49 ;unused (Room 59 does not exist)
	VISITED_MEEPS 				;50
	VISITED_FOREST_61 			;51
	VISITED_FOREST_62 			;52
	VISITED_FOREST_63 			;53
	VISITED_GRAVEYARD_DAYTIME 	;54
	VISITED_TOWNOVERLOOK 		;55
	VISITED_PATH_66 			;56
	VISITED_PATH_67 			;57
	VISITED_PATH_68 			;58
	VISITED_FOREST_69 			;59
	VISITED_FAERYRING 			;60 ;unused
	VISITED_FOREST_71 			;61
	VISITED_FOREST_72 			;62
	VISITED_TARGETRANGE 		;63 ;unused
	VISITED_FOREST_74 			;64
	VISITED_FOREST_75 			;65
	VISITED_DRYAD 				;66
	VISITED_STAG_77 			;67
	VISITED_STAG_78 			;68
	VISITED_FOREST_79 			;69
	VISITED_FOREST_80 			;70
	VISITED_FOREST_81 			;71 ;log crossroads
	VISITED_FLYINGFALLS 		;72
	VISITED_HENRYINSIDE 		;73
	VISITED_ANTWERP 			;74
	VISITED_FOREST_85 			;75
	VISITED_FOREST_86 			;76
	VISITED_LAKE 				;77
	VISITED_TROLLCAVE 			;78
	VISITED_TROLLCAVE2 			;79
	VISITED_ROOM_90 			;80 ;unused
	VISITED_BRIGAND_AMBUSH 		;81
	VISITED_FOREST_92 			;82
	VISITED_BRIGAND_GATE 		;83
	VISITED_BRIGAND_COURTYARD 	;84 ;wrong description... means something else.
	VISITED_BRIGAND_DININGHALL 	;85 ;unused
	VISITED_BRIGAND_JESTER 		;86
	VISITED_BRIGAND_LEADER 		;87
	VISITED_TOWNOUTLOOK 		;88
	VISITED_INN 				;89
	VISITED_OUTSIDE_MAGICSHOP 	;90
	VISITED_ADVENTURER_GUILD 	;91
	VISITED_LOL_HOUSE 			;92
	VISITED_MAGICSHOP_INSIDE 	;93
	VISITED_VEGGIE_STAND 		;94
	VISITED_SHERIFF_HOUSE 		;95
	VISITED_STORE 				;96
	VISITED_TAVERN_OUTSIDE 		;97
	VISITED_TAVERN_INSIDE 		;98
	VISITED_THIEVES_GUILD 		;99
	VISITED_ALLEY_DAY 			;100
	VISITED_ALLEY_NIGHT 		;101
)
