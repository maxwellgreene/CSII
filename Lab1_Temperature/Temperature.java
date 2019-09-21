/* Java class, each instance of which represents a temperature.
** Both the Celsius and Fahrenheit scales are supported.
**
** For CMPS 144L Fall 2019 Lab #1
** Authors: R. McCloskey and < INSERT STUDENTS' NAMES >
** Date: August 2019
*/

public class Temperature {

   // class constants
   // ---------------
   
   // These constants reflect the fact that, with respect to a change in
   // temperature, for every nine degrees of change on the Fahrenheit scale 
   // there is a change of five degrees on the Celsius scale.
   private static final double FAH_TO_CEL_RATIO = 9.0 / 5.0;  
   private static final double CEL_TO_FAH_RATIO = 1.0 / FAH_TO_CEL_RATIO;

   // The Fahrenheit and Celsius temperatures at which ice melts.
   private double FAH_MELTING_POINT = 32.0;
   private double CEL_MELTING_POINT = 0.0;


   // instance variable
   // -----------------

   // A measure of this Temperature on the Celsius scale.
   private double celsiusDeg;  


   // constructors
   // ------------

   /* Initializes this Temperature object so that it is set to the given
   ** number of degrees, either on the Celsius scale or the Fahrenheit
   ** scale, respectively, according to whether the second parameter is
   ** true or false, respectively.
   */
   public Temperature(double degrees, boolean inCelsius) {
      if(inCelsius)
      {
         celsiusDeg = degrees;
      }else{
         celsiusDeg = fahToCel(degrees);
      }
   }

   /* Initializes this object so that it is set to the given number
   ** of degrees, on the Celsius scale.
   */
   public Temperature(double cDeg) { this(cDeg, true); }


   /* Initializes this object so that it is set to zero degrees
   ** on the Celsius scale.
   */
   public Temperature() { this(0); }


   // observers
   // ---------

   /* Returns a measure of this Temperature, on the Celsius scale.
   */
   public double degreesCelsius() { return celsiusDeg; }

   /* Returns a measure of this Temperature, on the Celsius scale.
   */
   public double degreesFahrenheit() { 
      return celToFah(celsiusDeg);
   }


   /* Returns the difference between this Temperature and the given one,
   ** as measured in degrees Celsius.
   */
   public double difference(Temperature temp) {
      return celsiusDeg ;
   }

   /* Returns the difference between this Temperature and the given one,
   ** as measured in degrees Celsius or Fahrenheit, respectively, according
   ** to whether the second parameter is true or false, respectively.
   */
   public double difference(Temperature temp, boolean inCelsius) {
      return 0.0;  // STUB!!
   }


   /* Returns a string that describes a measure of this Temperature,
   ** either on the Celsius scale or the Fahrenheit scale, respectively,
   ** according to whether the parameter is true or false, respectively.
   ** E.g., "62.0F", "-12.45C".
   */
   public String toString(boolean inCelsius) {
      if (inCelsius) 
        { return degreesCelsius() + "C"; }
      else 
        { return degreesFahrenheit() + "F"; }
   }

   /* Returns a string that describes a measure of this Temperature,
   ** on the Celsius scale.  E.g., "35.4C", "-12.45C".
   */
   public String toString() { return toString(true); }


   // mutators
   // --------

   /* Sets this temperature so that its measure is the specified value,
   ** either on the Celsius scale or the Fahrenheit scale, respectively,
   ** according to whether the second parameter is true or false, respectively.
   */
   public void setTo(double degrees, boolean inCelsius) {
      if (inCelsius)
         { celsiusDeg = degrees; }
      else
         { celsiusDeg = fahToCel(degrees); }
   }

   /* Sets this temperature so that its measure is the specified value,
   ** on the Celsius scale
   */
   public void setTo(double degreesCelsius) 
      { setTo(degreesCelsius, true); }


   /* Changes this temperature's measure, on the Celsius scale, by the
   ** number of degrees specified.
   */
   public void changeBy(double deltaCelsius) {
      // STUB!
   }

   /* Changes this temperature's measure by the number of degrees specified,
   ** where the 2nd argument indicates whether that number of degrees is
   ** measured on the Celsius (true) or Fahrenheit (false) scale.
   */
   public void changeBy(double deltaDegrees, boolean inCelsius) {
      // STUB!
   }


   // private methods
   // ---------------

   /* Given a temperature reading on the Fahrenheit scale, returns
   ** the corresponding temperature reading on the Celsius scale.
   ** For example, 68 degrees Fahrenheit translates into 20 degrees Celsius.
   */
   private double fahToCel(double degFah) {
      return CEL_MELTING_POINT + fahDeltaToCelDelta(degFah - FAH_MELTING_POINT);
   }

   /* Given a temperature reading on the Celsius scale, returns
   ** the corresponding temperature reading on the Fahrenheit scale.
   ** For example, 20 degrees Celsius translates into 68 degrees Fahrenheit.
   */
   private double celToFah(double degCel) {
      return FAH_MELTING_POINT + celDeltaToFahDelta(degCel - CEL_MELTING_POINT);
   }

   /* Given a value describing a change in temperature on the Fahrenheit
   ** scale, returns the corresponding change in temperature on the 
   ** Celsius scale.
   ** For example, a change of +18F translates into +10C.
   */
   private double fahDeltaToCelDelta(double fahDelta) {
      return fahDelta * CEL_TO_FAH_RATIO;
   }

   /* Given a value describing a change in temperature on the Celsius
   ** scale, returns the corresponding change in temperature on the 
   ** Fahrenheit scale.
   ** For example, a change of +10C translates into +18F.
   */
   private double celDeltaToFahDelta(double celDelta) {
      return celDelta * FAH_TO_CEL_RATIO;
   }

}