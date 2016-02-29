/* A Spark function to parse the commands */
int ValveControl(String command);

// We name OpenValve & CloseValve pins
int OpenValve = D2; 
int CloseValve = D3; 
int ValveInputOpen = A3; 
int ValveInputClosed = A4;

int ValveStateClosed = 0;
int ValveStateOpen = 0;
int ValveCurrentState = 3;

// This routine runs only once upon reset
void setup() 
{
  //Register Spark function
  Spark.function("ValveAction", ValveControl);    
  Spark.variable("ValveState", ValveCurrentState);


  // Initialize pins as an output
  pinMode(OpenValve, OUTPUT);
  pinMode(CloseValve, OUTPUT);
  pinMode(ValveInputOpen, INPUT);
  pinMode(ValveInputClosed, INPUT);

  pinMode(D7, OUTPUT);


}

// This routine loops forever 
void loop()
{


ValveStateClosed = analogRead(ValveInputClosed);
ValveStateOpen = analogRead(ValveInputOpen);

//1 = closed
//0 = open
//2 = between
if (ValveStateClosed > 3000 && ValveStateOpen < 3000) { // Check if valce is closed
digitalWrite(D7, HIGH);
delay(5000);
ValveCurrentState = 1; // set valve as currently closed
} else if (ValveStateClosed < 3000 && ValveStateOpen > 3000) { // Check if valve is open
digitalWrite(D7, LOW);
delay(5000);
ValveCurrentState = 0; // set valve as currently open
}else 
ValveCurrentState = 3;
}



int ValveControl(String command)
{
  if (command == "1") {   //close valve
    digitalWrite(OpenValve, LOW); //to be removed - turns off led
    digitalWrite(D7, LOW);
    digitalWrite(CloseValve, HIGH);		// Start closing the valve
    delay(1000);
//    digitalWrite(CloseValve, LOW);		// Finish closing the valve turn off relay
    return 1;

  } else {                          // open valve
    digitalWrite(CloseValve, LOW); //to be removed - turns off led
    digitalWrite(D7, LOW);
    digitalWrite(OpenValve, HIGH);    // Start opening the Valve
    delay(1000);
//    digitalWrite(OpenValve, LOW);    // Finish opening the Valve
    return 1;
  }
}