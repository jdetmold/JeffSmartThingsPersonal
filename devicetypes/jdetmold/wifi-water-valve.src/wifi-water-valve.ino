/* A Spark function to parse the commands */
int ValveControl(String command);

// We name ValveOpen & ValveClose pins
int ValveOpen = D2; 
int ValveClose = D3; 

// This routine runs only once upon reset
void setup() 
{
  //Register Spark function
  Spark.function("valvestate", ValveControl);    
  // Initialize pins as an output
  pinMode(ValveOpen, OUTPUT);
  pinMode(ValveClose, OUTPUT);
}

// This routine loops forever 
void loop()
{
  // Nothing to do here
}

int ValveControl(String command)
{
  if (command == "1") {   
    digitalWrite(ValveClose, HIGH);		// Start closing the valve
	delay(10000);
    digitalWrite(ValveClose, LOW);		// Finish closing the valve turn off relay
    return 1;

  } else {               
    digitalWrite(ValveOpen, HIGH);    // Start opening the Valve
	delay(10000);
    digitalWrite(ValveOpen, LOW);    // Finish opening the Valve
    return 0;
  }
}