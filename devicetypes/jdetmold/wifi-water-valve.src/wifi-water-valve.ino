/* A Spark function to parse the commands */
int ValveControl(String command);

// We name OpenValve & CloseValve pins
int OpenValve = D2; 
int CloseValve = D3; 
int ValveInputOpen = D4; 
int ValveInputClosed = D5;

String ValveStateOpen;
int ValveStateClosed = 0;
int ValveCurrentState = 1;

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


delay(1000);


ValveStateClosed = digitalRead(ValveInputClosed);
ValveStateOpen = digitalRead(ValveInputOpen);

if (ValveStateClosed == HIGH) 
{
digitalWrite(D7, HIGH);
} else {
    
}


delay(1000);
}



int ValveControl(String command)
{
  if (command == "1") {   
    digitalWrite(D7, LOW);
    digitalWrite(CloseValve, HIGH);		// Start closing the valve
	delay(1000);
    digitalWrite(CloseValve, LOW);		// Finish closing the valve turn off relay
    return 1;

  } else {               
    digitalWrite(D7, LOW);
    digitalWrite(OpenValve, HIGH);    // Start opening the Valve
	delay(1000);
    digitalWrite(OpenValve, LOW);    // Finish opening the Valve
    return 1;
  }
}