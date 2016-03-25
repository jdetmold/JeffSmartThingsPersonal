/* A Spark function to parse the commands */
int CarControl(String command);

// We name the pins
int Unlock = D0;
int Lock = D1;
int Trunk = D2;
int Start = D3;
int Panic = D4;
int CarCurrentState = 0;

// This routine runs only once upon reset
void setup() 
{
  //Register Spark function
  Spark.function("CarAction", CarControl);    
  Spark.variable("CarState", CarCurrentState);


  // Initialize pins as an output
  pinMode(D7, OUTPUT);
  pinMode(Unlock, OUTPUT);
  pinMode(Lock, OUTPUT);
  pinMode(Trunk, OUTPUT);
  pinMode(Start, OUTPUT);
  pinMode(Panic, OUTPUT);
    
  digitalWrite(Unlock, HIGH);
  digitalWrite(Lock, HIGH);
  digitalWrite(Trunk, HIGH);
  digitalWrite(Start, HIGH);
  digitalWrite(Panic, HIGH);

}

// This routine loops forever 
void loop()
{

}


 // 1 = Unlock		switchUnLock	cmdUnLock
 // 2 = Lock		switchLock		cmdLock
 // 3 = Trunk		switchTrunk		cmdTrunk
 // 4 = Start		switchStart		cmdStart
 // 5 = Panic		switchPanic		cmdPanic
int CarControl(String command)
{
  if (command == "1") { //Unlock
    digitalWrite(Unlock, LOW);
    delay(500);
    digitalWrite(Unlock, HIGH);
	return 1;
    
  } else if (command == "2"){ //Lock
    digitalWrite(Lock, LOW);
    delay(500);
    digitalWrite(Lock, HIGH);
    delay(500);

    digitalWrite(Lock, LOW); //lock twice to arm
    delay(500);
    digitalWrite(Lock, HIGH);
    return 1;
    
  } else if (command == "3"){ //Trunk
    digitalWrite(Trunk, LOW);
    delay(500);
    digitalWrite(Trunk, HIGH);
	return 1;
	
  } else if (command == "4"){ //Start
    digitalWrite(Lock, LOW); //lock first
    delay(500);
    digitalWrite(Lock, HIGH);

    delay(500);    
    digitalWrite(Lock, LOW); //lock again
    delay(500);
    digitalWrite(Lock, HIGH);

    delay(500);    
    digitalWrite(Start, LOW); //start
    delay(500);
    digitalWrite(Start, HIGH);
	return 1;
	
  }else if (command == "5"){ //panic
    digitalWrite(Panic, LOW);
    delay(500);
    digitalWrite(Panic, HIGH);
	return 1;
	
  }
}