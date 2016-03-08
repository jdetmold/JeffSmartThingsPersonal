/* A Spark function to parse the commands */
int ValveControl(String command);

// We name OpenValve & CloseValve pins
int OpenValve = D2; 
int CloseValve = D3; 
int ValveInputOpen = D4; 
int ValveInputClosed = D5;

int ValveStateClosed = 0;
int ValveStateOpen = 0;
int ValveCurrentState = 3;

// This routine runs only once upon reset
void setup() 
{
  //Register Spark function
  Particle.function("ValveAction", ValveControl);    
  Particle.variable("ValveState", ValveCurrentState);


  // Initialize pins as an output
  pinMode(OpenValve, OUTPUT);
  pinMode(CloseValve, OUTPUT);
  pinMode(ValveInputOpen, INPUT_PULLDOWN);
  pinMode(ValveInputClosed, INPUT_PULLDOWN);

  pinMode(D7, OUTPUT);

    digitalWrite(CloseValve, HIGH);
    digitalWrite(OpenValve, HIGH);

}

// This routine loops forever 
void loop()
{


ValveStateClosed = digitalRead(ValveInputClosed);
ValveStateOpen = digitalRead(ValveInputOpen);

//1 = closed
//0 = open
//2 = between

if (ValveStateClosed == HIGH && ValveStateOpen == LOW) { // Check if valce is closed
digitalWrite(D7, HIGH);
delay(5000);
ValveCurrentState = 1; // set valve as currently closed
} else if (ValveStateClosed == LOW && ValveStateOpen == HIGH) { // Check if valve is open
digitalWrite(D7, LOW);
delay(5000);
ValveCurrentState = 0; // set valve as currently open
}else 
ValveCurrentState = 3;
}



int ValveControl(String command)
{
  if (command == "1") {   //close valve
    digitalWrite(CloseValve, LOW);
    delay(5000);
    digitalWrite(CloseValve, HIGH);
    return 1;

  } else {                          // open valve
    digitalWrite(OpenValve, LOW);
    delay(5000);
    digitalWrite(OpenValve, HIGH);
    return 1;
  }
}