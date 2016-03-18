double analogvalue = 0;
double analogvoltage = 0;
double maxvoltagein = 3.113;
double kpa = 0;
double depth = 0;
double inches = 0;
double inchesout= 0;
double currentdepthinches = 0;
double offset = 0.85; //offset for calibration

void setup() {
    pinMode(A0, INPUT);
    Particle.variable("sumpdepth", currentdepthinches);
}

void loop() {

analogvalue = analogRead(A0); // get analog value from sensor
analogvoltage = ((double)analogvalue * 0.0008); // change input to voltage
//to get pressure from voltage Vout = VS * (0.09 * P + 0.04) == (Vin-(MaxV*0.04))/(MaxV*0.09) = P
kpa = ((double)(analogvoltage-(maxvoltagein*0.04))/(maxvoltagein*0.09)); // gets presure from voltage
//convert kPa to mm of water // acceleration of gravity x depth (m) = kPa // depth mm = (kPA/9.81)*1000
depth = ((double)(kpa/9.81)*1000.00);
//convert mm to inches // mm * 0.03937 = inches
inches = ((double)depth * 0.03937);
inches = ((double)inches/offset); //apply offset to calibrate sensor
//inchesout = round(((double)inches)*10)/10.0; //round to 1 decimal place
inchesout = ((double)((int)(inches * 100))) / 100.00;

currentdepthinches = inchesout;
delay(900);

}