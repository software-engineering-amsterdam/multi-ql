form survey { 
  "How would you rate your customer support experience?"
    customerGrade: int
  "Is this percentage representation correct?"
  	calcGrade: int  = ((customerGrade / 5) *100)
}