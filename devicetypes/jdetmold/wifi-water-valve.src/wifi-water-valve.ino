/* A Spark function to parse the commands */
int ValveControl(String command);

// We name OpenValve & CloseValve pins
int OpenValve = D2; 
int CloseValve = D3; 
int ValveInputOpen = D4; 
int ValveInputClosed = D5;

String ValveStateOpen;
String ValveStateClosed;
String ValveCurrentState;


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



}

// This routine loops forever 
void loop()
{

ValveStateClosed = digitalRead(ValveInputClosed);
ValveStateOpen = digitalRead(ValveInputOpen);

if ((ValveStateClosed == "HIGH") && (ValveStateOpen == "LOW")) 
{ 
ValveCurrentState = "CLOSED";
} else if ((ValveStateClosed == "LOW") && (ValveStateOpen == "HIGH")) 
{
ValveCurrentState = "OPEN";
} else {
ValveCurrentState = "ERROR";
}

delay(100);

}



int ValveControl(String command)
{
  if (command == "1") {   
    digitalWrite(CloseValve, HIGH);		// Start closing the valve
	delay(10000);
    digitalWrite(CloseValve, LOW);		// Finish closing the valve turn off relay
    return 1;

  } else {               
    digitalWrite(OpenValve, HIGH);    // Start opening the Valve
	delay(10000);
    digitalWrite(OpenValve, LOW);    // Finish opening the Valve
    return 0;
  }
}