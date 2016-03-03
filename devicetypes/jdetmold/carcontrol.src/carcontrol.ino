/* A Spark function to parse the commands */
int CarControl(String command);

// We name pins
//int OpenValve = D2; 
//int CloseValve = D3; 
//int ValveInputOpen = A3; 
//int ValveInputClosed = A4;

//int ValveStateClosed = 0;
//int ValveStateOpen = 0;
int CarCurrentState = 0;

// This routine runs only once upon reset
void setup() 
{
  //Register Spark function
  Spark.function("CarAction", CarControl);    
  Spark.variable("CarState", CarCurrentState);


  // Initialize pins as an output
//  pinMode(OpenValve, OUTPUT);
//  pinMode(CloseValve, OUTPUT);
//  pinMode(ValveInputOpen, INPUT);
//  pinMode(ValveInputClosed, INPUT);

  pinMode(D7, OUTPUT);


}

// This routine loops forever 
void loop()
{

}



int CarControl(String command)
{
  if (command == "1") { 
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
	
    return 1;
  } else if (command == "2"){ 
  digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    return 1;
  } else if (command == "3"){
  digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
	return 1;
  } else if (command == "4"){
  digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
	digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
	return 1;
  }else if (command == "5"){
  digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
    digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
	digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
	digitalWrite(D7, HIGH);
	delay(1000);
	digitalWrite(D7, LOW);
	delay(1000);
	return 1;
  }
}