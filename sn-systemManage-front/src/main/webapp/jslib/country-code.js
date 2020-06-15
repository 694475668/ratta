var cCodeList = [
    { name: " 中国", code: "+86" },
	{ name: "  中国台湾", code: "+886" },
	{ name: "中国香港", code: "+852" },
	{ name: "中国澳门", code: "+853" },
	{ name: " Brazil", code: "+55" },
	{ name: " India", code: "+91" },
	{ name: " Indonesia", code: "+62" },
	{ name: " Malaysia", code: "+60" },
	{ name: "Andorra", code: "+376" },
	{ name: " Afghanistan", code: "+93" },
	{ name: "  Antigua and Barbuda", code: "+1" },
	{ name: "  Anguilla", code: "+1" },
	{ name: "Albania", code: "+355" },
	{ name: "Armenia", code: "+374" },
	{ name: "Angola", code: "+244" },
	{ name: " Argentina", code: "+54" },
	{ name: "  American Samoa", code: "+1" },
	{ name: " Austria", code: "+43" },
	{ name: " Australia", code: "+61" },
	{ name: "Aruba", code: "+297" },
	{ name: "Azerbaijan", code: "+994" },
	{ name: "Algeria", code: "+213" },
	{ name: "Bosnia and Herzegovina", code: "+387" },
	{ name: "  Barbados", code: "+1" },
	{ name: "Bangladesh", code: "+880" },
	{ name: " Belgium", code: "+32" },
	{ name: "Burkina Faso", code: "+226" },
	{ name: "Bulgaria", code: "+359" },
	{ name: "Bahrain", code: "+973" },
	{ name: "Burundi", code: "+257" },
	{ name: "Benin", code: "+229" },
	{ name: "  Bermuda", code: "+1" },
	{ name: "Brunei", code: "+673" },
	{ name: "Bolivia", code: "+591" },
	{ name: "Bonaire, Sint Eustatius and Saba", code: "+599" },
	{ name: " Brazil", code: "+55" },
	{ name: "  Bahamas", code: "+1" },
	{ name: "Bhutan", code: "+975" },
	{ name: "Botswana", code: "+267" },
	{ name: "Belarus", code: "+375" },
	{ name: "Belize", code: "+501" },
	{ name: "British Indian Ocean Territory", code: "+246" },
	{ name: "  British Virgin Islands", code: "+1" },
	{ name: "  Canada", code: "+1" },
	{ name: " Cocos Islands", code: "+61" },
	{ name: "Central African Republic", code: "+236" },
	{ name: "Congo", code: "+242" },
	{ name: "Côte d'Ivoire", code: "+225" },
	{ name: "Cook Islands", code: "+682" },
	{ name: " Chile", code: "+56" },
	{ name: "Cameroon", code: "+237" },
	{ name: " China", code: "+86" },
	{ name: " Colombia", code: "+57" },
	{ name: "Costa Rica", code: "+506" },
	{ name: " Cuba", code: "+53" },
	{ name: "Cape Verde", code: "+238" },
	{ name: "Curaçao", code: "+599" },
	{ name: " Christmas Island", code: "+61" },
	{ name: "Cyprus", code: "+357" },
	{ name: "Czech Republic", code: "+420" },
	{ name: "Croatia", code: "+385" },
	{ name: "Cambodia", code: "+855" },
	{ name: "Comoros", code: "+269" },
	{ name: "  Cayman Islands", code: "+1" },
	{ name: "Chad", code: "+235" },
	{ name: "Djibouti", code: "+253" },
	{ name: " Denmark", code: "+45" },
	{ name: "  Dominica", code: "+1" },
	{ name: "  Dominican Republic", code: "+1" },
	{ name: "Ecuador", code: "+593" },
	{ name: "Estonia", code: "+372" },
	{ name: " Egypt", code: "+20" },
	{ name: "Eritrea", code: "+291" },
	{ name: "Ethiopia", code: "+251" },
	{ name: "Equatorial Guinea", code: "+240" },
	{ name: "El Salvador", code: "+503" },
	{ name: "Finland", code: "+358" },
	{ name: "Fiji", code: "+679" },
	{ name: "Falkland Islands", code: "+500" },
	{ name: "Faroe Islands", code: "+298" },
	{ name: " France", code: "+33" },
	{ name: "French Guiana", code: "+594" },
	{ name: "French Polynesia", code: "+689" },
	{ name: " Germany", code: "+49" },
	{ name: "Gabon", code: "+241" },
	{ name: "  Grenada", code: "+1" },
	{ name: "Georgia", code: "+995" },
	{ name: " Guernsey", code: "+44" },
	{ name: "Ghana", code: "+233" },
	{ name: "Gibraltar", code: "+350" },
	{ name: "Greenland", code: "+299" },
	{ name: "Gambia", code: "+220" },
	{ name: "Guinea", code: "+224" },
	{ name: "Guadeloupe", code: "+590" },
	{ name: " Greece", code: "+30" },
	{ name: "Guatemala", code: "+502" },
	{ name: "  Guam", code: "+1" },
	{ name: "Guinea-Bissau", code: "+245" },
	{ name: "Guyana", code: "+592" },
	{ name: "Hong Kong", code: "+852" },
	{ name: "Honduras", code: "+504" },
	{ name: "Haiti", code: "+509" },
	{ name: " Hungary", code: "+36" },
	{ name: " Indonesia", code: "+62" },
	{ name: "Ireland", code: "+353" },
	{ name: "Israel", code: "+972" },
	{ name: " Isle Of Man", code: "+44" },
	{ name: " India", code: "+91" },
	{ name: "Iraq", code: "+964" },
	{ name: "Iran", code: "+98" },
	{ name: "Iceland", code: "+354" },
	{ name: " Italy", code: "+39" },
	{ name: " Jersey", code: "+44" },
	{ name: "  Jamaica", code: "+1" },
	{ name: "Jordan", code: "+962" },
	{ name: " Japan", code: "+81" },
	{ name: "Kenya", code: "+254" },
	{ name: "Kyrgyzstan", code: "+996" },
	{ name: "Kiribati", code: "+686" },
	{ name: "Kuwait", code: "+965" },
	{ name: "  Kazakhstan", code: "+7" },
	{ name: "Laos", code: "+856" },
	{ name: "Lebanon", code: "+961" },
	{ name: "Liechtenstein", code: "+423" },
	{ name: "Liberia", code: "+231" },
	{ name: "Lesotho", code: "+266" },
	{ name: "Lithuania", code: "+370" },
	{ name: "Luxembourg", code: "+352" },
	{ name: "Latvia", code: "+371" },
	{ name: "Libya", code: "+218" },
	{ name: "Micronesia", code: "+691" },
	{ name: "Morocco", code: "+212" },
	{ name: "Monaco", code: "+377" },
	{ name: "Moldova", code: "+373" },
	{ name: "Montenegro", code: "+382" },
	{ name: "Madagascar", code: "+261" },
	{ name: "Marshall Islands", code: "+692" },
	{ name: "Macedonia", code: "+389" },
	{ name: "Mali", code: "+223" },
	{ name: "Myanmar", code: "+95" },
	{ name: "Mongolia", code: "+976" },
	{ name: "Macao", code: "+853" },
	{ name: "Martinique", code: "+596" },
	{ name: "Mauritania", code: "+222" },
	{ name: "  Montserrat", code: "+1" },
	{ name: "Malta", code: "+356" },
	{ name: "Mauritius", code: "+230" },
	{ name: "Maldives", code: "+960" },
	{ name: "Malawi", code: "+265" },
	{ name: " Mexico", code: "+52" },
	{ name: " Malaysia", code: "+60" },
	{ name: "Mozambique", code: "+258" },
	{ name: "Mayotte", code: "+262" },
	{ name: "North Korea", code: "+850" },
	{ name: "  Northern Mariana Islands", code: "+1" },
	{ name: "Namibia", code: "+264" },
	{ name: "New Caledonia", code: "+687" },
	{ name: "Niger", code: "+227" },
	{ name: "Norfolk Island", code: "+672" },
	{ name: "Nigeria", code: "+234" },
	{ name: "Nicaragua", code: "+505" },
	{ name: " Netherlands", code: "+31" },
	{ name: " Norway", code: "+47" },
	{ name: "Nepal", code: "+977" },
	{ name: "Nauru", code: "+674" },
	{ name: "Niue", code: "+683" },
	{ name: " New Zealand", code: "+64" },
	{ name: "Oman", code: "+968" },
	{ name: "Panama", code: "+507" },
	{ name: " Peru", code: "+51" },
	{ name: "Papua New Guinea", code: "+675" },
	{ name: " Philippines", code: "+63" },
	{ name: " Pakistan", code: "+92" },
	{ name: " Poland", code: "+48" },
	{ name: "  Puerto Rico", code: "+1" },
	{ name: "Palestine", code: "+970" },
	{ name: "Portugal", code: "+351" },
	{ name: "Palau", code: "+680" },
	{ name: "Paraguay", code: "+595" },
	{ name: "Qatar", code: "+974" },
	{ name: "Reunion", code: "+262" },
	{ name: " Romania", code: "+40" },
	{ name: "  Russia", code: "+7" },
	{ name: "Rwanda", code: "+250" },
	{ name: "Saint Barthélemy", code: "+590" },
	{ name: " Switzerland", code: "+41" },
	{ name: " Spain", code: "+34" },
	{ name: "  Saint Kitts And Nevis", code: "+1" },
	{ name: " South Korea", code: "+82" },
	{ name: "  Saint Lucia", code: "+1" },
	{ name: " Sri Lanka", code: "+94" },
	{ name: "Saint Martin", code: "+590" },
	{ name: "Saint Pierre And Miquelon", code: "+508" },
	{ name: "Serbia", code: "+381" },
	{ name: "Saudi Arabia", code: "+966" },
	{ name: "Solomon Islands", code: "+677" },
	{ name: "Seychelles", code: "+248" },
	{ name: "Sudan", code: "+249" },
	{ name: " Sweden", code: "+46" },
	{ name: " Singapore", code: "+65" },
	{ name: "Saint Helena", code: "+290" },
	{ name: "Slovenia", code: "+386" },
	{ name: " Svalbard And Jan Mayen", code: "+47" },
	{ name: "Slovakia", code: "+421" },
	{ name: "Sierra Leone", code: "+232" },
	{ name: "San Marino", code: "+378" },
	{ name: "Senegal", code: "+221" },
	{ name: "Somalia", code: "+252" },
	{ name: "Suriname", code: "+597" },
	{ name: "Sao Tome And Principe", code: "+239" },
	{ name: "  Sint Maarten (Dutch part)", code: "+1" },
	{ name: "Syria", code: "+963" },
	{ name: "Swaziland", code: "+268" },
	{ name: "  Saint Vincent And The Grenadines", code: "+1" },
	{ name: "Samoa", code: "+685" },
	{ name: " South Africa", code: "+27" },
	{ name: "The Democratic Republic Of Congo", code: "+243" },
	{ name: "  Turks And Caicos Islands", code: "+1" },
	{ name: "Togo", code: "+228" },
	{ name: " Thailand", code: "+66" },
	{ name: "Tajikistan", code: "+992" },
	{ name: "Tokelau", code: "+690" },
	{ name: "Timor-Leste", code: "+670" },
	{ name: "Turkmenistan", code: "+993" },
	{ name: "Tunisia", code: "+216" },
	{ name: "Tonga", code: "+676" },
	{ name: " Turkey", code: "+90" },
	{ name: "  Trinidad and Tobago", code: "+1" },
	{ name: "Tuvalu", code: "+688" },
	{ name: "Taiwan", code: "+886" },
	{ name: "Tanzania", code: "+255" },
	{ name: "United Arab Emirates", code: "+971" },
	{ name: " United Kingdom", code: "+44" },
	{ name: "Ukraine", code: "+380" },
	{ name: "Uganda", code: "+256" },
	{ name: "  United States", code: "+1" },
	{ name: "Uruguay", code: "+598" },
	{ name: "Uzbekistan", code: "+998" },
	{ name: "  U.S. Virgin Islands", code: "+1" },
	{ name: "Vatican", code: "+379" },
	{ name: " Venezuela", code: "+58" },
	{ name: " Vietnam", code: "+84" },
	{ name: "Vanuatu", code: "+678" },
	{ name: "Western Sahara", code: "+212" },
	{ name: "Wallis And Futuna", code: "+681" },
	{ name: "Yemen", code: "+967" },
	{ name: "Zambia", code: "+260" },
	{ name: "Zimbabwe", code: "+263" }
];