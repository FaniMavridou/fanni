Ελάχιστα επεξεργασμενο βιαστικά από Άλεξ στις 27/10/2020 

ΦΑΣΗ 1: Ζητάμε FilePath, InputType(1),name για τη δημιουργία engine
	
	-Δημιουργεί Document, το οποιο στο constructor του κανει load τα περιεχομενα του File και τα καταθετει στο lineblocks List.
	-Οριζουμε this.lineblocks , this.alias, this.filePath.
	-***Path p = Paths.get(this.filePath);*** <-- Φανταζομαι εντολη για να παιρνει το path και το δινει
	 στο this.simpleInputFileName (το αποιο χρησιμοποιειται στα exports) μονο για το System.out.print , 
	 οποτε ισως να μη χρειαστουν σε εμας αυτες οι δυο τελευταιες γραμμες
	

ΦΑΣΗ 2: Αναλογως το type (Raw, Annotated) καλουμε την αντιστοιχη registerInput και δημιουργουμε ενα RuleSet

	-Παιρνει ως παραμετρο List<List<String>> οπου ουσιαστικα. Περναει καθε List<String> και απο εκει που
	 ηταν ολοι οι κανονες RuleUndefined τους γεμιζει(αναλογως τι εχει δωσει ο χρηστης).
	-Επιστρεφει RuleSet με:
	 	omitRule = RuleUndefined;                omitRule = Χ;
		h1Rule = RuleUndefined;			 h1Rule = Χ;
		h2Rule = RuleUndefined;            ===>  h2Rule = Χ;
		boldRule = RuleUndefined;		 boldRule = Χ;
		italicsRule =  RuleUndefined;	 	 italicsRule =  Χ;

		οπου Χ ειναι οτιδηποτε κανονας δωθηκε.
	-ΥΣτερα χρησιμοποιειται ΜΟΝΟ στο loadFileAndCharacterizeBlocks() και οτι ακολουθει σε αυτο μονο.
	 Συνεχιζει εδω: characterizeLineblocks(document, Ruleset) --> (Πχ) CharacterizeRawFile(document, Ruleset).
	 και μετεπειτα διαβαζει ολα τα lineblocks και στο καθενα εφαρμοζει τις εξης μεθοδους:
         #setStyle(RuleSet) : Βαση του ruleset του αλλαζει το StyleEnum
	 #setFormat(RuleSet): Βαση του ruleset του αλλαζει το FormatEnum

	(3)(Υπαρχει προβλημα στη δημιουργια του RuleSet καθως αμα στο ιδιο List<List<String>> δωσουμε πχ δυο omit,
	   τοτε μονο το τελευταιο εμφανισιμο omit θα εχει ισχυ, καθως ο κανονας του θα εχει μπει πανω στον κανονα
	   των προηγουμενων.)
	
	============================================= Τρόπος χείρισης (3) =============================================
	==													     ==
	==  Αρα προτεινω να αλλαξουμε το createRuleSet() ετσι ωστε να μπορεις να εχεις και τα δυο ,                 ==
	==      και επισης ισως να γινει #RuleSet= List( <Heading> , <Format> , <ConcreteRule>, <potisions/prefix>). ==
	==													     ==
	==  Επισης στο characterizeLineblocks αντι για RuleSet ως 2η παραμετρο, να δινουμε List<RuleSet> και 	     ==
	==      οταν τρεχουν τα setStyle/setFormat για καθε lineBlock να κοιτανε και καθε κανονα. 		     ==
	==      Κατασκευαζουμε δηλαδη μια μεθοδο που θα τρεχει πολλες inputRuleSet που θα του δινει ο χρηστης (ολες  ==
	==      μαζι εξαρχης) και μαζι με το (3) θα διατρεχει για ολα τα lineblock ολοκληρη τη λιστα RuleSet.        ==
	==													     ==
	===============================================================================================================
	

ΦΑΣΗ 3: Επιλεγει pdf η markdown και εκτελειται το αναλογο export fuction.

	-Αντικειμενο (Markdown/Pdf)Exporter(this.document, outputPath)
	

ΦΑΣΗ 4: Δειχνει outputPath του αρχειου και εμφανιζει απο κατω πινακα ή εξτρα txt που εχει τα στοιχεια που
	θα θελε ιστο τελος. Τα συμμαζευουμε σε μια μεθοδο.




*********************************** Πως δουλευει το RuleSet **********************************

	RU:  RuleUndefined
	RAC: RuleAllCaps
	RIP: RuleInPosition
	RSW: RuleStartWith



		omitRule(RU)   h1Rule(RU)   h2Rule(RU)   boldRule(RU)  italicsRule(RU)
		    ^              ^            ^             ^             ^
		    | 		   |            |             |             |
	#RuleSet----|--------------|------------|-------------|-------------|

	 Δινει πχ Rule <Omit><Starts With><Γιατι κανω αυτο το project? = prefix>
	 ->Δημιουργει εναν RSW(lineblock, prefix)
	 Οριζει τον κανονα omitRule του Ruleset ως RSW(..., Αρχή φράσης...)
	 Άρα:
	
		omitRule(RSW)   h1Rule(RU)   h2Rule(RU)   boldRule(RU)  italicsRule(RU)
		    ^              ^            ^             ^             ^
		    | 		   |            |             |             |
	#RuleSet----|--------------|------------|-------------|-------------|

	και αμα βαλει μετα κανονα Rule <Hi><Position><1> :

		omitRule(RSW)   h1Rule(RIP)   h2Rule(RU)   boldRule(RU)  italicsRule(RU)
		    ^              ^            ^             ^             ^
		    | 		   |            |             |             |
	#RuleSet----|--------------|------------|-------------|-------------|

	Αυτο φερει ομως προβλημα οταν φερουμε 2ο κανονα Omit γιατι θα γραψει πανω στον αλλον, αρα
	για κανονα <Omit><Position><4> θα γινει:

		omitRule(RIP)   h1Rule(RIP)   h2Rule(RU)   boldRule(RU)  italicsRule(RU)
		    ^              ^            ^             ^             ^
		    | 		   |            |             |             |
	#RuleSet----|--------------|------------|-------------|-------------|

	Δηλαδη γραφει πανω στον προηγουμενο κανονα για το καθε ομιτ πχ
	
	Γι αυτο και για απλουστευση, θα παιρνουμε μονο μια τιμη για καθε κανονα. (για αρχη)



	==================================================================

	.isValid()
	RU:  RuleUndefined  = παντα false
	RAC: RuleAllCaps    = κοιταει στο lineblock που τσεκαρεται αμα ειναι ολα κεφαλαια
	RIP: RuleInPosition = (?) εχουμε αριθμημενα τα lineblocks βαση της λιστας Αρα τα συγκρινει με τις παραμετρους του
	RSW: RuleStartWith  = κοιταει αν το lineblock αρχιζει με το prefix που του δινεται

	************************** Τελος *********************************