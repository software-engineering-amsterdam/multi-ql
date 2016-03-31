form taxOfficeExample {
  "Naam"
    name: text
  "Telefoonnummer"
    telnr: text
  "Burgerservicenummer/sofinummer"
    bsn: text
  "Geboortedatum (dd/mm/jj)"
    gebdtm: text
  "Nummer belastingsconsultant"
    consultnr: text
  "Heeft u van ons bericht ontvangen om aangifte te doen"
    aangifte: boolean


  if (aangifte) {
      "Inkomsten uit loondienst:"
        messageLoondienst: text = ""

      "Werkgever(s)"
        werkgever: text

      "Arbeidskorting totaal"
        arbeidskorting: money

      "Loonheffing totaal"
        loonheffing: money

      "Loon e.d. totaal"
        loon: money

      "Totaal levensloopverlofkorting"
        levensloopverlofkorting: money

      "Fooien"
        fooien: money

      "Geeft samenvatting van berekening weer"
        summary: boolean

      if (summary){
          "Belastbaar inkomen"
            belastbaar: money = loon - arbeidskorting - loonheffing - levensloopverlofkorting
          "Belastbaar inkomen"
            belastbaar2: money = 700000.0

          if(belastbaar2 > 0.0 && belastbaar2 < 19922.0) {
          "Te betalen belasting"
            schaal1: money = belastbaar2 * 0.3655
          }

          if(belastbaar2 >= 19922.0 && belastbaar2 < 66421.0) {
          "Te betalen belasting"
            schaal3: money = (belastbaar2 - 19922.0) * 0.4040
                              + 7281.0
          }

          if(belastbaar2 >= 66421.0) {
          "Te betalen belasting"
            schaal4: money = (belastbaar2 - 66421.0) * 0.52
                              + 18785.0
                              + 7281.0
          }
    }
  }
  else {
      "Als u geen bericht heeft ontvangen hoeft u geen belastings aangifte te doen"
        messageBye: text = ""
  }
}